#lab1.s
#Melvin Evans
#
#1.Assemble  : as -o lab2.o lab2.s
#2.Line      : ld -o a.out lab2.o csc35.o
#3.Execute   : a.out

.data
.text
Line1:
	.ascii "Drinking Butter Beer: 20 points\n\0"
Line2: 
	.ascii "Students that knew what happened in 1947: 10 points\n\0"
Line3: 
	.ascii "Not knowing your Harry Potter House: -100 points\n\0"
Line4: 
	.ascii "Students who did nothing  over the weekend: -1 point\n\n\0"

prompt1:
	.ascii "How many students drank Butter Beer?\n\0"
prompt2:
	.ascii "How many students that knew what happened in 1947?\n\0"
prompt3:
	.ascii "How many students don't know their Harry Potter house?\n\0"
prompt4:
	.ascii "How many students who did nothing over the weekeend?\n\0"
prompt5:
	.ascii "Gryffindor gained \0"
prompt6:
	.ascii " points\n\0"
.global _start

_start:
	mov $Line1, %rax	#prints the chart
	call PrintCString
	mov $Line2, %rax
	call PrintCString
	mov $Line3, %rax
	call PrintCString
	mov $Line4, %rax
	call PrintCString

	mov $20, %r14           #holds the variables of points from the chart
	mov $10, %r13
	mov $-100, %r12
	mov $-1, %r11
				#Everything underneath does the prompting and fills in the value into a counter
	mov $prompt1, %rax	
	call PrintCString
	call ScanInt
	Imul %r14,%rax
	mov %rax,%r10
	add %r10,%r15

	mov $prompt2, %rax
	call PrintCString
	call ScanInt
	Imul %r13,%rax
	mov %rax,%r9
	add %r9, %r15

	mov $prompt3, %rax
	call PrintCString
	call ScanInt
	Imul %r12,%rax
	mov %rax, %r8
	add %r8, %r15

	mov $prompt4, %rax
	call PrintCString
	call ScanInt
	Imul %r11, %rax
	mov %rax, %rcx
	add %rcx, %r15

	mov $prompt5, %rax
	call PrintCString
	mov %r15, %rax
	call PrintInt
	mov $prompt6, %rax
	call PrintCString

	Call EndProgram
