/*This is the micro Kernel programs which takes the request which input sends, checks for the validity of the request and sends it to the server.Also, depending on the functionality the server reuests, the microkernel directs to the particular server*/
package microKernelpkg;		//To create the package microKernel
import java.util.*;
import serverpkg.*;		//import the package Serverpkg whose functionalities are used in this program
public class microKernel extends Thread
{
	public String request;
	public int ProcessorNo;
	public void run(){		//creates a new thread and starts running it
		downcall();
	} 	
	private void downcall()		
	{
		StringTokenizer token = new StringTokenizer(request," ");//StringTokenizer class uses to split the entire string into its tokens
		String function = token.nextToken();			 //gets the tokens of the String one by one
		if((function.equals("add"))||(function.equals("mul")))
		{
			Server s = new Server();			 //Call server for the  functions which are implemented by that server
			s.request = request;				 //Request Strings and Processor number are initialised
			s.ProcessorNo = ProcessorNo;
			s.start();					  //calls the Server file depending on the requirements"
		}	
		else if((function.equals("prime"))||(function.equals("fibo")))
		{
			Server1 s1 = new Server1();
                	s1.request = request;
                	s1.ProcessorNo = ProcessorNo;			  //calls the Server1 file depending on the reugirements
			s1.start();	
		}
		else 
			System.out.println("Enter correct function");	  //Entered funtion shoukd be able to sleep
	}
}
