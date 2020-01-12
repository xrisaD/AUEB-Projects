# Date:2 November 2018
# Description: Ergasia 1:GCD Calculator and tester

#registers:
#a0:argument
#v0:for li instructions
#t0:a
#t1:b
#t2:y 
#t3:s
	 .text
         .globl __start
__start:
	#int a, b, y, s;
	lw $t0,A #The word is loaded into register $t0 from the specified address(A).
	lw $t1,B #The word is loaded into register $t1 from the specified address(B).
	lw $t2,Y #The word is loaded into register $t2 from the specified address(Y).
	lw $t3,S #The word is loaded into register $t3 from the specified address(S).
	
	#print ("Δώσε έναν ακέραιο: ")
	la $a0,input_mes	#print_string 
	li $v0,4		#input message for register a 
	syscall
	
	li $v0,5        #read_int a 
	syscall
	move $t0,$v0    #t0=v0,remove a to a register that will not use for another reason
	
	#print (" Δώσε έναν ακέραιο: ")
	la $a0,input_mes	#print_string 
	li $v0,4		#input message for register b
	syscall
 	
	li $v0,5		#read_int b 
	syscall
	move $t1,$v0    	#t1=v0,remove b to a register that will not use after
	
	#print (" Ποιος είναι ο ΜΚΔ των "+ a + " και " + b + ";");
	la $a0,ans 		#print_string 
	li $v0,4		#print question and wait for answer
	syscall
	
	la $a0,of 		#print_string "of"
	li $v0,4 		#it helps us to print the answer autonomously late
	syscall 
	
	move $a0,$t0 		#print_int a
	li $v0,1
	syscall
	
	la $a0,kai 		#print_string "and"
	li $v0,4
	syscall
	
	move $a0,$t1 		#print_int b
	li $v0,1
	syscall
	
	la $a0,ques		#print_string "?"(question mark)
	li $v0,4
	syscall
	
	#find GSD
	#y = a % b;
	rem $t2,$t0,$t1
	
	#while (y != 0) {
	#	a = b;
	#	b = y;
	#	y = a % b;
	#}
LOOP1:
	beqz $t2,END_CAL 	#if y=0 break the loop and go to end_cal else while y!=0 repeat next 3 lines
	move $t0,$t1		#a=b
	move $t1,$t2		#b=y
	rem $t2,$t0,$t1		#y = a % b;
	j LOOP1	
END_CAL:
	
	#s = Διάβασε_Ακέραιο();
	li $v0,5			#read_int
	syscall				#read GCD
	move $t3,$v0
	
	#while (s != b) {
	#	print ("Λάθος! Δοκιμάστε ξανά. ");
	#	print ("Ποιος είναι ο ΜΚΔ;");
	#	s = Διάβασε_Ακέραιο();
	#}
LOOP2:
	beq $t3,$t1,THE_END #if s==b break the loop,the user find the correct answer
	
	la $a0,error 		    # print error
	li $v0,4 		    #ask again
	syscall
	
	la $a0,ans 		    #print_string 
	li $v0,4   		    #ask for the correct answer 
	syscall	
	
	la $a0,ques 		#print_string "?"(question mark)
	li $v0,4   		
	syscall
	
	li $v0,5		    #read_int
	syscall			    #read GCD
	move $t3,$v0
	
	j LOOP2

THE_END:
	#System.out.println (" Συγχαρητήρια! ");
	la $a0,congr		#print_string congratulations
	li $v0,4
	syscall
	
	la $a0,CRLF		    #ln
	li $v0,4		    #change line
	syscall

	li $v0,10		    #The end.
	syscall
	
          .data
A: 	  .word 0
B:	  .word 0
Y:        .word 0
S:        .word 0
input_mes:.asciiz "Give me an integer: "
ans: 	  .asciiz "Who is the GCD "
of: 	  .asciiz "of "
kai:      .asciiz " and "
ques:     .asciiz "?"
error:    .asciiz "Wrong!"
congr:    .asciiz "Congratulations!"
CRLF:     .asciiz "\n"

