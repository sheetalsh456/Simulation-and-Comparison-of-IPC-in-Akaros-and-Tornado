/*This is a client Program which sends multiple requests to the server which goes through the microkernel and each client runs on a unique processor, the processor number is generated randomly. */
import microKernelpkg.*;                	//imports the microkernel package creadted in the microKernel file                      
import java.util.*;
public class Client
{
	public static void main(String args[])
	{
		Random r = new Random();	
		System.out.println("Do you have a request?\nYes-1\nNo-0 ");//Ask the user to enter 1/0 for yes/no to a request
                Scanner sc = new Scanner(System.in);
                String i = sc.nextLine();			   //take input 1/0 
		while(i.equals("1"))				   //run the loop till user doesn't enter 0
		{
		
			microKernel mk = new microKernel();
			System.out.println("Enter the function to be performed and the parameters \nadd-add two numbers(>=1)\nmul-multiply two numbers(>=1)\nprime-find the prime numbers untill the given number(=1)\nfibo-Find the 'n' fibonacci numbers(=1)");	
	                  					   //Enter the function and the parameter list
								   //add - add numbers, parameters >=1 				
								   //mul - multiply numbers, parameters >=1
							           //prime - find prime numbers till a particular prime,parameter =1	
            						       	   //fibo - Find 'n' fibonacci numbers, parameter =1
		
			mk.request = sc.nextLine();		   //accept function and parameters
			mk.ProcessorNo = r.nextInt(10)+1;	   //generate a random number as the processor number
			System.out.println("The process "+ mk.request +" is running in Process "+mk.ProcessorNo+"(Client Process)");
								   //Prints the Processor in which the Client is running

			mk.start();				   //link to the microKernel file to produce multiple threads in it
			System.out.println("Do you have a request?\nYes-1\nNo-0 ");
                        i = sc.nextLine();			   //take input 1/0
		}
	}
}

