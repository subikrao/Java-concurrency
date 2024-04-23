class Data {
    private String content;
    private boolean isWriting = false;
  
    public synchronized void read() {
      while (isWriting) {
        try {
          wait();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
      System.out.println("Reading: " + content);
    }
  
    public synchronized void write(String newContent) {
      isWriting = true;
      content = newContent;
      System.out.println("Writing: " + content);
      notifyAll(); // Notify waiting readers
      isWriting = false;
    }
  }
  
  public class readerwriter {
    public static void main(String[] args) throws InterruptedException {
      Data data = new Data();
  
      Thread writer = new Thread(() -> {
        for (int i = 0; i < 5; i++) {
          data.write("Data " + i);
          try {
            Thread.sleep(1000); // Simulate writing time
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
      });
  
      Thread reader1 = new Thread(() -> {
        for (int i = 0; i < 10; i++) {
          data.read();
          try {
            Thread.sleep(500); // Simulate reading time
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
      });
  
      Thread reader2 = new Thread(reader1);
  
      writer.start();
      reader1.start();
      reader2.start();
  
      writer.join();
      reader1.join();
      reader2.join();
      
    }
  }
  
  