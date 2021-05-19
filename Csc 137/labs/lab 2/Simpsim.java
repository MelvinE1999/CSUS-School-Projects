import java.io.*;
import java.util.Arrays;


public class Simpsim {

    private static boolean noPrinting;
    private static int clockCycle = 0; // has a maximum of 10,000
    private static int programPointer = 0;
    private static Stack<String> stackMemory = new Stack<>();
    private static String[] programMemory = new String[4096];
    private static int port0Value = 99999; //This state is my "null" state.
    private static boolean invalidOpCodeChecker;


    public static void main(String[] args) {
        if(args.length == 0){
            System.out.println("To run this program use this format:");
            System.out.println("command line prompt > java Simpsim.java " +
                               "[options] <object file> [integer in value]");
        }else {
            noPrinting = false;
            int currentArgIndex = 0;
            if (args[0].equals("-s")) {
                noPrinting = true;
                currentArgIndex++;
            }
            String fileName = args[currentArgIndex++];
            if (args.length - 1 == currentArgIndex) {
                port0Value = Integer.valueOf(args[currentArgIndex]);
            }
            pullInstructionsFromFile(fileName);
           try {
               executeInstructions();
           }catch (NullPointerException notEnoughValuesOnStack){
               System.out.println("There is not enough values on the " +
                       "stack by the time opcode#" + (programPointer + 1) +
                       " in your memory is ran or no command at that line." +
                       "\nPlease check your input file and try again.");
           }
        }
    }


    public static void pullInstructionsFromFile(String fileName) {
        try {
            File parameterFile = new File(fileName);
            BufferedReader fileReader = new BufferedReader(
                    new FileReader(parameterFile));
            int indexOfProgramMemory = 0;
            String fileCommands;
            while ((fileCommands = fileReader.readLine()) != null) {
                if (fileCommands.equals("v2.0 raw"))
                    continue;
                else if( fileCommands.equals(""))
                    continue;
                else {
                    programMemory[indexOfProgramMemory] = fileCommands;
                    indexOfProgramMemory++;
                }
            }
        }catch(IOException invalidFile){
            System.out.println("Either your file is not in the same directory" +
                    " or it just can not be found. Double check and run again."
                    );
            halt();
        }
    }

    public static void executeInstructions(){
        while(!programMemory[programPointer].equals("0F00")){
            invalidOpCodeChecker = false;
            if(clockCycle >= 10000)
                programRunningOver10000ClockCycles();

            switch (programMemory[programPointer].charAt(0)){
                case '1':
                    pushToCertainIndex();
                    break;
                case '2':
                    pushFromMemoryLocation();
                    break;
                case '3':
                    popToMemoryLocation();
                    break;
                case '4':
                    jumpProgramCounterToCertainLocation();
                    break;
                case '5':
                    jumpIfTopEquals0();
                    break;
                case '6':
                    jumpIfTopNotEquals0();
                    break;
                case 'D':
                    inputFromSpecifiedPort();
                    break;
                case 'E':
                    outputFromSpecifiedPort();
                    break;
                default:
                    invalidOpCodeChecker = true;
                    break;
            }

            //separated due to the ones with number have a lot of variety
            switch (programMemory[programPointer]){
                case "0000":
                    break;
                case "F000":
                    addStack();
                    break;
                case "F001":
                    subtractStack();
                    break;
                case "F002":
                    multiplyStack();
                    break;
                case "F003":
                    modulusStack();
                    break;
                case "F004":
                    bitwiseShiftLeft();
                    break;
                case "F005":
                    bitwiseShiftRight();
                    break;
                case "F006":
                    unaryNegation();
                    break;
                case "F00A":
                    binaryAnd();
                    break;
                case "F00B":
                    binaryOr();
                    break;
                case "F00C":
                    binaryExclusiveOr();
                    break;
                case "F00D":
                    bitwiseNegation();
                    break;
                case "F010":
                    equal();
                    break;
                case "F011":
                    notEqual();
                    break;
                case "F012":
                    greaterThanOrEqualTo();
                    break;
                case "F013":
                    lessThanOrEqualTo();
                    break;
                case "F014":
                    greaterThan();
                    break;
                case "F015":
                    lessThan();
                    break;
                default:
                    if(invalidOpCodeChecker){
                        System.out.println("OpCode# " + (programPointer + 1) +
                                " is invalid.\nPlease check and try again.");
                        halt();
                    }
                    break;
            }
            programPointer++;
            clockCycle++;
        }
        if(noPrinting)
            System.out.println("Max depth reached = " +
                    stackMemory.getMaxDepth());
        halt();
    }

    public static void halt(){
        System.exit(0);
    }

    public static void pushToCertainIndex(){
        String numberToBePushed = grabNumberAfterOpCode();
        String numberBinary = convertHexToBinary(numberToBePushed);
        String numberPaddedBinary = pad12BitTo16Bit(numberBinary);
        if(numberPaddedBinary.charAt(0) == '1'){
            while (numberToBePushed.length() < 4) {
                numberToBePushed = 'f' + numberToBePushed;
            }
        }else{
            while (numberToBePushed.length() < 4) {
                numberToBePushed = '0' + numberToBePushed;
            }
        }
        stackMemory.push(numberToBePushed);
    }

    public static void pushFromMemoryLocation(){
        int locationToGrabData = Integer.parseInt(grabNumberAfterOpCode(), 16);
        stackMemory.push(programMemory[locationToGrabData]);
    }

    public static void popToMemoryLocation(){
        int locationToSendData = Integer.parseInt(grabNumberAfterOpCode(), 16);
        if(stackMemory.getSize() != 0) {
            programMemory[locationToSendData] = stackMemory.pop();
        }
    }

    public static void jumpProgramCounterToCertainLocation(){
        int locationToPointProgramCounterTo =
                Integer.parseInt(grabNumberAfterOpCode(), 16);
        if(programMemory[locationToPointProgramCounterTo - 1] != null) {
            programPointer = locationToPointProgramCounterTo;
            programPointer--;
        }
    }

    public static void jumpIfTopNotEquals0(){
        String valueToCompare = stackMemory.peek();
        if(valueToCompare != "0000"){
            jumpProgramCounterToCertainLocation();
        }

    }

    public static void jumpIfTopEquals0(){
        String valueToCompare = stackMemory.peek();
        if(valueToCompare.equals("0000")){
            jumpProgramCounterToCertainLocation();
        }
    }

    public static void inputFromSpecifiedPort(){
        int portToGrabFrom = Integer.parseInt(grabNumberAfterOpCode(), 16);
        if(portToGrabFrom == 0){
            stackMemory.push(checkFor16BitRepresentationOfHex(
                    Integer.toHexString(port0Value)));
        }
    }

    public static void outputFromSpecifiedPort(){
        port0Value = convertHexStringToInteger(stackMemory.pop());
        if(noPrinting != true)
            System.out.println(port0Value);
    }

    public static void addStack(){
        int topNumber = convertHexStringToInteger(stackMemory.pop());
        int nextNumber = convertHexStringToInteger(stackMemory.pop());
        stackMemory.push(checkFor16BitRepresentationOfHex(
                Integer.toHexString(nextNumber + topNumber)));
    }

    public static void subtractStack(){
        int topNumber = convertHexStringToInteger(stackMemory.pop());
        int nextNumber = convertHexStringToInteger(stackMemory.pop());
        stackMemory.push(checkFor16BitRepresentationOfHex(
                Integer.toHexString(nextNumber - topNumber)));
    }

    public static void multiplyStack(){
        int topNumber = convertHexStringToInteger(stackMemory.pop());
        int nextNumber = convertHexStringToInteger(stackMemory.pop());
        stackMemory.push(checkFor16BitRepresentationOfHex(
                Integer.toHexString(nextNumber * topNumber)));
    }

    public static void modulusStack(){
        int topNumber = convertHexStringToInteger(stackMemory.pop());
        int nextNumber = convertHexStringToInteger(stackMemory.pop());
        stackMemory.push(checkFor16BitRepresentationOfHex(
                Integer.toHexString(nextNumber % topNumber)));
    }

    public static void bitwiseShiftLeft() {
        int topNumber = convertHexStringToInteger(stackMemory.pop());
        int nextNumber = convertHexStringToInteger(stackMemory.pop());
        stackMemory.push(checkFor16BitRepresentationOfHex(
                Integer.toHexString(nextNumber << topNumber)));
    }

    public static void bitwiseShiftRight() {
        int topNumber = convertHexStringToInteger(stackMemory.pop());
        int nextNumber = convertHexStringToInteger(stackMemory.pop());
        stackMemory.push(checkFor16BitRepresentationOfHex(
                    Integer.toHexString(nextNumber >> topNumber)));
    }

    public static void binaryAnd() {
        int topNumber = convertHexStringToInteger(stackMemory.pop());
        int nextNumber = convertHexStringToInteger(stackMemory.pop());
        stackMemory.push(checkFor16BitRepresentationOfHex(
                Integer.toHexString(nextNumber & topNumber)));
    }

    public static void binaryOr() {
        int topNumber = convertHexStringToInteger(stackMemory.pop());
        int nextNumber = convertHexStringToInteger(stackMemory.pop());
        stackMemory.push(checkFor16BitRepresentationOfHex(
                Integer.toHexString(nextNumber | topNumber)));
    }

    public static void binaryExclusiveOr() {
        int topNumber = convertHexStringToInteger(stackMemory.pop());
        int nextNumber = convertHexStringToInteger(stackMemory.pop());
        stackMemory.push(checkFor16BitRepresentationOfHex(
                Integer.toHexString(nextNumber ^ topNumber)));
    }

    public static void equal() {
        String topNumber = stackMemory.pop();
        String nextNumber = stackMemory.pop();
        if(nextNumber == topNumber)
            stackMemory.push(nextNumber);
    }

    public static void notEqual() {
        String topNumber = stackMemory.pop();
        String nextNumber = stackMemory.pop();
        if(nextNumber != topNumber)
            stackMemory.push(nextNumber);
    }

    public static void greaterThanOrEqualTo() {
        int topNumber = convertHexStringToInteger(stackMemory.pop());
        int nextNumber = convertHexStringToInteger(stackMemory.pop());
        if(nextNumber >= topNumber)
            stackMemory.push(checkFor16BitRepresentationOfHex(
                    Integer.toHexString(nextNumber)));
    }

    public static void lessThanOrEqualTo() {
        int topNumber = convertHexStringToInteger(stackMemory.pop());
        int nextNumber = convertHexStringToInteger(stackMemory.pop());
        if(nextNumber <= topNumber)
            stackMemory.push(checkFor16BitRepresentationOfHex(
                    Integer.toHexString(nextNumber)));
    }

    public static void greaterThan() {
        int topNumber = convertHexStringToInteger(stackMemory.pop());
        int nextNumber = convertHexStringToInteger(stackMemory.pop());
        if(nextNumber > topNumber)
            stackMemory.push(checkFor16BitRepresentationOfHex(
                    Integer.toHexString(nextNumber)));
    }

    public static void lessThan() {
        int topNumber = convertHexStringToInteger(stackMemory.pop());
        int nextNumber = convertHexStringToInteger(stackMemory.pop());
        if(nextNumber < topNumber)
            stackMemory.push(checkFor16BitRepresentationOfHex(
                    Integer.toHexString(nextNumber)));
    }

    public static void unaryNegation() {
        int numberBeforeConversion =
                                   convertHexStringToInteger(stackMemory.pop());
        int numberAfterConversion = -(numberBeforeConversion);
        stackMemory.push(checkFor16BitRepresentationOfHex(
                                   Integer.toHexString(numberAfterConversion)));
    }

    public static void bitwiseNegation() {
        String numberInBinary = convertHexToBinary(stackMemory.pop());
        String binaryInverted = convertBinaryToBinaryInversion(numberInBinary);
        stackMemory.push(binaryInverted);
    }


    // needed as original parseInt doesn't translate negative numbers properly
    public static int convertHexStringToInteger(String numberToConvert){
        if(numberToConvert.charAt(0) == 'F' || numberToConvert.charAt(0) == 'f')
        {
            String numberInBinary = convertHexToBinary(numberToConvert);
            String binaryInverted =
                                convertBinaryToBinaryInversion(numberInBinary);
            return (Integer.parseInt(binaryInverted, 2) + 1) * -1;
        }
        return Integer.parseInt(numberToConvert, 16);
    }

    public static String convertBinaryToBinaryInversion(String numberToConvert){
        String numberConverted = "";
        for(int i = 0; i < numberToConvert.length(); i++){
            if(numberToConvert.charAt(i) == '1')
                numberConverted += '0';
            else
                numberConverted += '1';
        }
        return numberConverted;
    }

    public static String convertHexToBinary(String numberToConvert){
        return Integer.toString(Integer.parseInt(numberToConvert,16), 2);
    }

    public static String checkFor16BitRepresentationOfHex(String numberToCheck){
        if(numberToCheck.length() <= 4) {
            while (numberToCheck.length() < 4) {
                numberToCheck = '0' + numberToCheck;
            }
            return numberToCheck;
        }
        String hexadecimalWithoutPadding = "";
        for(int i = numberToCheck.length() - 4; i < numberToCheck.length(); i++)
        {
           hexadecimalWithoutPadding += numberToCheck.charAt(i);
        }
        return hexadecimalWithoutPadding;
    }

    // made to help all + something commands
    public static String grabNumberAfterOpCode(){
        String opCode = programMemory[programPointer];
        String numberToBePushed = "";
        for(int i = 1;i < 4; i++){
            numberToBePushed += opCode.charAt(i);
        }
        return numberToBePushed;
    }

    // without this you will get incorrectly converted hex numbers
    public static String pad12BitTo16Bit(String numberToPad){
        if(numberToPad.length() != 12 || numberToPad.charAt(0) == '0'){
            while(numberToPad.length() < 16)
                numberToPad = '0' + numberToPad;
            return numberToPad;
        }
        while(numberToPad.length() < 16)
            numberToPad = '1' + numberToPad;
        return numberToPad;
    }

    public static void programRunningOver10000ClockCycles() {
        System.out.println("Too many clock cycles. Goodbye.");
        halt();
    }

}

class Stack <Element>{
    private int currentSize;
    private static final int STARTING_MAX_CAPACITY = 5;
    private Object[] elements;
    private static final int MEMORY_SIZE = 4096;
    private int currentDepth;
    private int maxDepth;

    public Stack(){
        this.currentSize = 0;
        this.elements = new Object[STARTING_MAX_CAPACITY];
        this.currentDepth = 0;
        this.maxDepth = 0; // in this class to allow easy readability
    }

    public Element pop(){
        if(getSize() != 0) {
            this.currentSize--;
            Element element = (Element) this.elements[this.currentSize];
            this.elements[this.currentSize] = null;
            this.currentDepth--;
            updateMaxDepth();
            return element;
        }
        return null;
    }

    public void push(Element element){
        if(getSize() == this.elements.length &&
                this.elements.length != MEMORY_SIZE){
            doubleArrayCapacity();
        }
        this.elements[this.currentSize] = element;
        this.currentSize++;
        this.currentDepth++;
        updateMaxDepth();
    }

    private void doubleArrayCapacity(){
        int doubledSize = this.elements.length * 2;
        this.elements = Arrays.copyOf(this.elements, doubledSize);
    }

    public int getSize(){
        return this.currentSize;
    }

    private void updateMaxDepth(){
        if(this.currentDepth > this.maxDepth){
            this.maxDepth++;
        }
    }

    public Element peek(){
        return (Element) this.elements[this.currentSize - 1];
    }

    public int getMaxDepth(){
        return this.maxDepth;
    }

    @Override
    public String toString() {
        StringBuilder stackValues = new StringBuilder();
        stackValues.append("[");
        for(int i = 0; i < this.currentSize; i++){
            stackValues.append(this.elements[i].toString());
            if(i < this.currentSize - 1){
                stackValues.append(",");
            }
        }
        stackValues.append("]");
        return  stackValues.toString();
    }
}