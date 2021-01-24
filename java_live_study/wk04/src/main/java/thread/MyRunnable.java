package thread;

public class ThreadEx2 implements Runnable {
    @Override
    public void run() {
        System.out.println("=== Runnable 인터페이스 쓰레드임!!");
        for (int i = 0; i < 5; i++) {
            Thread thread = Thread.currentThread();
            System.out.println(thread.getName());
        }
    }
}
