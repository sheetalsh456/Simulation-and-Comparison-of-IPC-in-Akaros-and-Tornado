/*this is a server Program which accepts the requests sent by the Client through the microkernel. It processes the request in the same processor as the client process.It creates multiple threads so that it can handle multiple requests concurently. Appropriate functions are called according to the request and errors are handled through exception handling.*/
package serverpkg;		//To create a server package to be included in other files
import java.util.*;
public class Server extends Thread
{
	public String request;
	public int ProcessorNo;
	private StringTokenizer token; 
	private int st;
	public void run()	//creates a new thread and starts running it 
	{
		upcall();
	}	
	private void upcall()
	{
		System.out.println("The Process "+request+" running on "+ProcessorNo+"(Client request being processed)");
						//Processor on which the request of the Client process is run(which is same as the client)

		token=new StringTokenizer(request," ");		//StringTokenizer breaks the whole string into its tokens
		st = token.countTokens();			//Counts the number of words/tokens in a string
		String function=token.nextToken();		//gets the token of the string one by one
		if(function.equals("add"))			//Compares the Token in the string  and assigns it to the particular method
		{
			if(st >=2)				//Checks for the correct number of parameters
			 add();
			else 
			System.out.println("Enter correct number of arguments");
		}       
		else if(function.equals("mul"))
		{
			if(st>=2)				//Compares the string and calls the correct method and also checks for
 			mul();					//correct number of parameters
			else 
			System.out.println("Enter correct number of arguments");
		}
	}
	private void add(){					//Adds numbers and exceptions are handled efficiently
		int i = 0;
		try{
		while(token.hasMoreTokens())			//Checks if there are any more tokens
		{
			
			i = i + Integer.parseInt(token.nextToken()); //Converts String into integer and adds it to get the sum
			
		}	
		  System.out.println("Sum = "+i);
		}
		catch(NumberFormatException e)
		{
				System.out.println(e);
		}
	}
	private void mul(){
                int i = 1;					//Multiplies numbers and exceptions are handled efficiently
		try{
                while(token.hasMoreTokens())			//Checks if there are any more tokens
                {
                        i = i * Integer.parseInt(token.nextToken());//converts a string inti integer to compute the product
		}
		System.out.println("Product = "+i);
		}
		catch(NumberFormatException e)
		{
			System.out.println(e);
		}
                            
        }
}
