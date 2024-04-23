// Java code for thread creation by implementing  the Runnable Interface 
class MultithreadingDemo implements Runnable 
{ 
    public void run() 
    { 
         
            System.out.println ("Thread " + 
                                Thread.currentThread().getId() +  " is running"); 
  
        }

    public static void main(String[] args) 
    { 
            MultithreadingDemo m1 = new MultithreadingDemo(); 
            Thread t1= new Thread (m1);       
            // If you are not extending thread class your class object would not be a thread object . So you need to explicitly create a thread class object. We are passing the object of the class that implements runnable, so that class run() method will execute
            t1.start();         
        } 
} 

