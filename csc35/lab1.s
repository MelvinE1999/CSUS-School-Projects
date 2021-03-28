#lab1.s
#Melvin Evans
#
#1.Assemble: as -o lab1.o lab1.s
#2.Link    : ld -o a.out lab1.0 csc35.o
#3.Execute : a.out

.data                                                  #Start the data section
Message:                                               #Message is an address
	.ascii "Aloha, everyone!\n\0"                  #create a buffer of ASCII

Quote:
	.ascii "You killing me Smalls!!\n\0"

Name:
	.ascii "My name is Melvin Evans\n\0"

TextA:
	.ascii "I will graduate in \0"

TextB: 
	.ascii " from Sacramento State!\n\0"

.text                                                  #Start the text section
.global _start                                          #Makes the _start label public

_start:                                                #Unix starts here
	mov $Name, %rax
	call PrintCString
 
	mov $Message, %rax                             #Put the address into rax
	call PrintCString                              #Execute the csc35.o subrountine 
	
	mov $TextA, %rax
	call PrintCString
	mov $2021, %rax
	call PrintInt
	mov $TextB, %rax
	call PrintCString

	mov $Quote, %rax
	call PrintCString

	call EndProgram                                #Execute the csc35.o subrountine
