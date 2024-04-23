public class P {
    private int count = 0;

    public synchronized void func() {
        System.out.println("Running thread:" + Thread.currentThread().getId());
        for (int i = 0; i < 1000; i++) {
            count++;
        }
    }

    public synchronized int getCount() {
        return count;
    }
}

public class Q {
    private int count = 0;

    public synchronized void func() {
        System.out.println("Running thread:" + Thread.currentThread().getId());
        for (int i = 0; i < 1000; i++) {
            count++;
        }
    }

    public synchronized int getCount() {
        return count;
    }
}

public class A {
    private P p = new P();
    private Q q = new Q();

    public void fun() {
        synchronized (p) {
            p.func();
            int x = p.getCount();
            System.out.println(x);
        }
        synchronized (q) {
            q.func();
            int y = q.getCount();
            System.out.println(y);
        }
    }

    public static void main(String[] args) {
        A a = new A();
        Thread t1 = new Thread(
            () -> {
            a.fun();
        });
        Thread t2 = new Thread(() -> {
            a.fun();
        });

        t1.start();
        t2.start();
    }
}