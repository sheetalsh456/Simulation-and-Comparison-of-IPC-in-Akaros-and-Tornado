//Implementation of UCQ(Unbounded Concurrent Queue), used for Inter Process Communication in Akaros.
/*Algorithm
    UCQ's are used to deliver messages from the kernel to processes. They contain linked, memory mapped pages of arrays
    holding event messages. In this code, I have demonstrated multiple pages, with multiple locations in them, where the
    event messages can be kept. When the kernel wants to send event messages to the UCQ, they are kept in the first free
    location available. I have considered 5 locations to be available in every dynamically allocated page. The number can
    vary. Once all the locations in a page have been occupied, a new page is created and memory is dynamically allocated
    to it. The kernel can then continue sending event messages to these new locations. When a process wants to receive
    event messages, we first check if the process number is a valid one, and then we also check if there is atleast
    one event message available in the UCQ. If there isn't, then we wait for the kernel to send an event message. But
    if there is, then the first available event message for the particular process is received by the process, and that
    location is made empty. So the next time the kernel wants to send a message, it is loaded into that previously emptied
    location. Infinite no.of pages can be dynamically created, depending on the no.of event messages that the kernel wants
    to send. This is how UCQ's are implemented. */
#include<bits/stdc++.h>
using namespace std;
int event_messages=0,add;
int* ptr;
int num=-1,producer_number=0,pro,maxi=1,k=0,cnt=1,pro1;

//function for the kernel to send event messages
void kernel_send(int k1)
{
    //flag variable to check if a free location is found among the existing pages
	int flag=-1;
	for(int i=0;i<maxi;i++)
	{
		for(int j=0;j<5;j++)
		{
            //checking for the first free location
			if(*(ptr + i*5 + j) == 0)
			{
				*(ptr + i*5 + j)=pro1;
				printf("Event message %d kept at page %d, location %d\n",producer_number,i,j);
				flag=0;
				//incrementing the total no.of messages by 1
				event_messages++;
				break;
			}
		}
		if(flag==0)
		{
            k=k1;
            break;
        }
	}
	//if all the pages allocated till now already contain event messages in them, we need to create and allocate a new page
	if(flag==-1)
	{
        maxi++;
        k1++;
        //dynamically allocating memory for a new page
        ptr = (int *)malloc(cnt * 5 * sizeof(int));
        for(int h=0;h<(cnt-1);h++)
        {
            for(int l=0;l<5;l++)
            {
                *(ptr+h*5+l)=1;
            }
        }
        cnt++;
        //calling the function again, so that the event message can be kept in a free spot of the newly allocated page
        kernel_send(k1);
	}
}

//function for the process to receive event messages
void process_receive()
{
    int flag=-1;
	for(int i=0;i<maxi;i++)
	{
		for(int j=0;j<5;j++)
		{
            //checking for the first event message
			if(*(ptr + i*5 + j)==pro)
			{
				*(ptr + i*5 + j)=0;
				printf("Process %d received event message at page %d, location %d\n",pro,i,j);
				event_messages--;
				flag=0;
				break;
			}
		}
		if(flag==0)
            break;
    }
    if(flag==-1)
        printf("No event message for the particular process. Waiting for kernel to send one....\n");
}
int main()
{
	int processes,choice,f=0;
	ptr = (int *)malloc(cnt * 5 * sizeof(int));
	cnt++;
	printf("Enter no.of processes\n");
	scanf("%d",&processes);
	while(f==0)
	{
		printf("Menu\n1.Send an event message\n2.Receive an event message\n3.Exit\n");
		scanf("%d",&choice);
		if(choice==1)
		{
            printf("Enter the process number, to which the event message is to be delivered\n");
            scanf("%d",&pro1);
            while(pro1>processes)
				{
					printf("There are only %d processes. Enter a valid process number\n",processes);
					scanf("%d",&pro1);
				}
			producer_number++;
			kernel_send(k);
		}
		else if(choice==2)
		{
            //checking if the UCQ contains any event messages or not
			if(event_messages==0)
				printf("No event message present in the buffer. Waiting for kernel to produce......\n");
			else
			{
				printf("Enter process number\n");
				scanf("%d",&pro);
				//checking if the entered process number exists
				while(pro>processes)
				{
					printf("There are only %d processes. Enter a valid process number\n",processes);
					scanf("%d",&pro);
				}
				process_receive();
            }
        }
        else
            f=1;

        //to deallocate the pages, from which the processes have received all the event messages
        add=0;
        for(int j=0;j<5;j++)
        {
            if(*(ptr+k*5+j)==0)
                add++;
        }
        if(add==5)
        {
            for(int j=0;j<5;j++)
            {
                //to free the pages which have been emptied of all its event messages
                free(ptr+k*5+j);
            }
        }
    }
	return 0;
}

