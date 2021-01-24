package thread;

public class ThreadEx1 extends Thread {
    @Override
    public void run() {
        System.out.println("=== Thread 상속 받은 쓰레드임");
        for (int i = 0; i < 5; i++) {
            String threadName = getName();
            System.out.println(threadName);
        }
    }
}
