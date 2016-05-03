/*this is another server Program which accepts the requests sent by the Client through the microkernel. It processes the request in the same processor as the client process.It creates multiple threads so that it can handle multiple requests concurently. Appropriate functions are called according to the request and errors are handled through exception handling.There are multiple Servers for different purposes*/
package serverpkg;			//To create a server package to be included in other files
import java.util.*;
public class Server1 extends Thread
{
        public String request;
        public int ProcessorNo;
        private StringTokenizer token;
	private int st;
        public void run()		//creates a new thread and starts running it 
        {
                upcall();
        }
        private void upcall()
        {
                token=new StringTokenizer(request," ");	//StringTokenizer breaks the whole string into its tokens
		st = token.countTokens();		//Counts the number of words/tokens in a string
                String function=token.nextToken();	//gets the tokens of the string one by one
                if(function.equals("prime"))		//Compares the Token in the string  and assigns it to the particular method
                {
                         prime();
                }
                else if(function.equals("fibo"))
                {
                        fibo();				//compares the string and calls the correct method
                }
        }
        private void prime(){				//method to calculate prime numbers till a mentioned number
                int i,j;
		if(st == 2){				//Checks for the correct number of parameter
               		int flag;
                        try{				//exceptions are handled efficiently
																	
                                i = Integer.parseInt(token.nextToken());	//converts the string to integer
				System.out.println("Processor number\tProcess name\tOutput");
				if(i>=2)
					System.out.println(ProcessorNo+"\t\t\t"+request+"\t\t"+"2" );	
				for(j=3;j<=i;j++)
   				{	flag=1;
      					for(int c=2;c<=j/2;c++)			//calculates if a number is prime from 2 to the required number
      					{					//by traversing till number/2 and prints those numbers
         					if(j%c == 0){
							flag=0;
            						break;
						}
      					}
      					if (flag==1)
      					{	
         					System.out.println(ProcessorNo+"\t\t\t"+request+"\t\t"+j );
						//Processor on which the request of the Client process is run(which is same as the client)
      					}
      						
   				}         
                        }
                        catch(NumberFormatException e)
                        {
                                System.out.println(e);	//prints exception if present
                        }
                }
		else 
			System.out.println("Enter correct number of arguments");
        }
	private void fibo(){					
                int i,j;
                if(st == 2){
                        try{
				i = Integer.parseInt(token.nextToken());			//Converts string into integer
                               	int f1 = 0,f2 = 1;
				System.out.println("Processor number\tProcess name\tOutput");
				System.out.println(ProcessorNo+"\t\t\t"+request+"\t\t"+"0");
				System.out.println(ProcessorNo+"\t\t\t"+request+"\t\t"+"1");
				for(j=2;j<i;j++)
				{								//Calculates 'n' number of fibonacci numbers and
					int f3 = f1+f2;						//prints them
					System.out.println(ProcessorNo+"\t\t\t"+request+"\t\t"+f3);
					//Processor on which the request of the Client process is run(which is same as the client)
					f1 = f2;
					f2 = f3;
				}
                        }
                        catch(NumberFormatException e)
                        {
                                System.out.println(e);						//exceptions are printed
                        }
                }
                else
                        System.out.println("Enter correct number of arguments");

       }

}

