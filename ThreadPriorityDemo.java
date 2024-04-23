
class ThreadPriorityDemo extends Thread {
  
    public void run()
    {    
        try{
            System.out.println("Runing..." + Thread.currentThread().getName());
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
       public static void main(String[] args)
    {
        
        ThreadPriorityDemo t1 = new ThreadPriorityDemo();
        ThreadPriorityDemo t2 = new ThreadPriorityDemo();
        ThreadPriorityDemo t3 = new ThreadPriorityDemo();
        t1.setPriority(2);
        t2.setPriority(5);
        t3.setPriority(8);       
        
        t1.start();
        t2.start();
        t3.start();
    }  
}
