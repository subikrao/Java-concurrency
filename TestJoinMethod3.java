//getName(), and getId() method:
// public String getName()
// public void setName(String name)
// public long getId()

class TestJoinMethod3 extends Thread{  
  public void run(){  
   System.out.println("running...");  
  }  
 public static void main(String args[]){  
  TestJoinMethod3 t1=new TestJoinMethod3();  
  TestJoinMethod3 t2=new TestJoinMethod3();  
  System.out.println("Name of t1:"+t1.getName());  
  System.out.println("Name of t2:"+t2.getName());  
  System.out.println("id of t1:"+t1.getId());  
  
  t1.start();  
  t2.start();  
  
  t1.setName("Sonoo Jaiswal");  
  System.out.println("After changing name of t1:"+t1.getName());  
 }  
}  
