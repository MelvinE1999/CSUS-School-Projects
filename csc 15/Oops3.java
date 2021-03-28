public class Oops3 {
    public static void main() {
        double y = 867.5309;
        double x = 10.01;
        printer(double x, double y);
        double y = 867.5309;
        printer(double x, double y);
    }

    public static void printer(double x, double y) {
        int z = 5;
        System.out.println("x = " + x + " and y = " + y);
        System.out.println("The value from main is: " + y);
        System.out.println("z = " + z);
    }
}