#Melvin Evans
#Lab 3 
.data
Prompt1:
	.ascii "How many daily units does the patient take?\n\0"
Message1:
	.ascii "The patient's ICF is: \0"
Prompt2:
	.ascii "\nWhat is the patient's blood sugar level?\n\0"
Message2:
	.ascii "The patient needs \0"
Message3:
	.ascii " units.\n\0"
.text

.global _start

_start:
	mov $Prompt1, %rax
	call PrintCString
	call ScanInt
	mov %rax, %r8

	mov $1800, %rax
	CWD
	Div %r8
	mov %rax, %r9
	mov $Message1, %rax
	call PrintCString
	mov %r9, %rax
	call PrintInt

	mov $Prompt2, %rax
	call PrintCString
	call ScanInt
	mov %rax, % r11

	mov $150, %rax
	SUB %rax, %r11
	mov $30, %r13
	mov %r11, %rax
	CWD
	Div %r13
	mov %rax, %r12

	mov $Message2, %rax
	call PrintCString
	mov %r12, %rax
	call PrintInt
	mov $Message3, %rax
	call PrintCString

	call EndProgram

