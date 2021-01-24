# 10주차. [멀티쓰레드 프로그래밍](https://github.com/whiteship/live-study/issues/10)
- 날짜 : 21.01.22
- 목표 : 자바의 멀티쓰레드 프로그래밍에 대해 학습

<hr>

## 프로세스와 쓰레드란?
- 운영체계로 부터 자원을 할당받아 실행하는 프로그램 (어플리케이션)을 **프로세스**라 하며 <br>
    프로세스 내에서 실행되는 작업의 단위를 **쓰레드**라 함
- JVM에 의해 하나의 *프로세스*가 발생하고 main() 안의 실행 블록이 하나의 스레드임
- main() 외의 쓰레드를 추가로 생성할 수 있음 : **멀티 쓰레드** 
    - 각 쓰레드끼리는 작업 중에 정보를 주고받을 수 있음 (프로세스끼리는 정보를 주고받을 수 없음)
    
### 멀티 쓰레드
- 여러 쓰레드를 동시에 실행시키는 것을 의미함 
- 장점
    - 동시에 두 가지 이상의 작업이 가능함
    - 메모리 공유로 인해 시스템 효율 상승 
- 단점
    - 서로 다른 쓰레드끼리 충돌이 일어날 수 있음  

<br>

## Thread 클래스와 Runnable 인터페이스 
- 쓰레드를 생성하는 방식에는 Thread 클래스를 상속받는 방법과 Runnable 인터페이스를 구현하는 방법 2가지가 있음 
- 쓰레드는 프로세스 내의 명령어 블록으로 시작점과 종료점을 가짐 
- `start()` 메서드를 통해 메서드가 실행될 수 있는 상태가 됨 
- `run()` 메소드가 종료되면 쓰레드는 종료되고, 한번 종료된 쓰레드는 다시 시작할 수 없으므로 쓰레드 객체를 다시 생성해줘야함
- `interrupt()` 메서드를 호출하면 InterruptedException이 발생하여 다른 스테드를 종료시킬 수 있음

### Thread 클래스로 생성하는 방법
```java
public class MyThread extends Thread {
    @Override
    public void run() {
        System.out.println("=== Thread 상속 받은 쓰레드임");
        for (int i = 0; i < 5; i++) {
            String name = getName();
            System.out.println(name);
        }
    }
}
```
```java
public static void main(String args[]) {
    MyThread t1 = new MyThread();
    t1.setName("thread1");
    MyThread t2 = new MyThread();
    t2.setName("thread2");

    t1.start();
    t2.start();
}
```
- 결과 예시 : 쓰레드가 순서대로 실행되는 것이 아님을 알 수 있음!
    ```
    === Thread 상속 받은 쓰레드임
    thread1
    thread1
    === Thread 상속 받은 쓰레드임
    thread2
    thread2
    thread2
    thread2
    thread2
    thread1
    thread1
    thread1
    ```

### Runnable 인터페이스로 생성하는 방법
```java
public class MyRunnable implements Runnable {
    @Override
    public void run() {
        System.out.println("=== Runnable 인터페이스 쓰레드임!!");
        for (int i = 0; i < 5; i++) {
            Thread thread = Thread.currentThread();
            System.out.println(thread.getName());
        }
    }
}
```
```java
public static void main(String args[]) {
    Runnable myRunnable = new MyRunnable();
    Thread t1 = new Thread(myRunnable);
    t1.setName("thread1");

    Thread t2 = new Thread(myRunnable);
    t2.setName("thread2");

    t1.start();
    t2.start();
}
```
- 결과 예시 : Thread 객체 상속 방식과 동일함
    ```
    === Runnable 인터페이스 쓰레드임!!
    thread1
    thread1
    thread1
    === Runnable 인터페이스 쓰레드임!!
    thread2
    thread2
    thread2
    thread2
    thread2
    thread1
    thread1
    ```
    
### Thread 클래스와 Runnable 인터페이스의 차이
- Thread 클래스를 상속받으면 다른 클래스를 상속받을 수 없기 때문에 Runnable 인터페이스를 이용하여 생성할 수 있음
- Runnable 인터페이스에는 `run()` 메소드만 정의되어 있음 
- 위의 예시처럼 `run()` 메서드를 Thread 클래스를 상속받는 경우는 직접 호출할 수 있지만 Runnable 인터페이스의 경우, 해당 인터페이스를 인자로 받는, 별도의 Thread 객체를 생성 후 `start()` 메서드를 호출해야 함

### lambda를 통한 thread 생성
```java
public static void main(String args[]) {
    Thread t1 = new Thread(() -> {
        System.out.println("=== lambda 쓰레드임!!");
        for (int i = 0; i < 5; i++) {
            Thread thread = Thread.currentThread();
            System.out.println(thread.getName());
        t1.setName("thread1");
        t1.start();
        });
    }
}
```

<br>

## 쓰레드의 상태
- 쓰레드는 다음의 6가지의 상태를 가질 수 있음
    <img src="https://bitstechnotes.files.wordpress.com/2017/12/threadstatediagram.png" width=70%>
    1. NEW : 쓰레드 객체는 생성되어있지만 아직 실행할 준비가 되지 않음
    2. RUNNABLE : 쓰레드가 start() 메서드를 시작하면 쓰레드가 `RUNNABLE` 상태가 됨. 스케쥴링을 기다림
    3. RUNNING : 쓰레드 스케쥴러가 쓰레드를 실행하면 `RUNNING` 상태가 됨
    4. TIMED_WAITING : `sleep()`, `wait()`, `join()` 메서드로 대기하고 있음. 대기 시간이 끝나면 다시 `RUNNABLE` 상태로 돌아감
    5. WAITING : 다른 쓰레드가 `notify()`, `notifyAll()`를 불러주기 기다리며 대기하고 있음 
    6. BLOCKED : CPU를 점유권을 상실한 상태. 쓰레드가 `run()`으로 실행되던 중에 자원이 제대로 할당 되지 않았을 시 monitor lock에 의해 중지됨. 다시 자원이 가능해지면 Lock이 풀리고 `RUNNABLE` 상태가 됨
    7. TERMINATED : 쓰레드가 실행문을 모두 수행하고 종료됨
- [자료 출처](https://bitstechnotes.wordpress.com/2017/12/16/java-thread-state-diagram/)

<br>

## 쓰레드의 우선순위
- 쓰레드는 우선 순위라는 속성을 가지고 있으며 우선 순위의 값에 따라 쓰레드가 얻는 실행 시간이 달라짐
- 수행하는 작업의 중요도에 따라 특정 쓰레드가 더 많은 작업 시간을 갖도록 함
- `setPriority()`메서드로 우선 순위를 지정함
- `public static final int AVG_PRI = 5`와 같이 우선순위를 속성값으로 가지며 1~10 범위 내에서 숫자가 높을 수록 우선 순위가 높음
	- main() 쓰레드의 우선 순위는 초기값이 5임 

<br>

## Main 쓰레드
- `main()`의 작업을 수행하는 것도 하나의 쓰레드에서 이루어지며 이를 **Main 쓰레드**라고 함
- Main 쓰레드만 실행하는 것을 싱글 쓰레드라고 하고, 메인 쓰레드에서 쓰레드를 추가 생성해서 실행하는 것이 멀티 쓰레드임

### 데몬 쓰레드 (demon thread)
- 다른 일반 쓰레드의 작업을 돕는 보조적인 역할을 수행하는 쓰레드로 주로 Main 쓰레드의 보조 역할로 사용됨
- `setDaemon(true)`을 호출해주어 사용 가능함
- 일반 쓰레드가 종료되면 그에 종속되는 데몬 쓰레드도 강제 종료됨 
- 모니터링을 위한 쓰레드를 사용하는 경우와 같이 부가적인 작업을 수행하는 쓰레드를 선언할 때 주로 사용함 
	- 무한루프와 조건문을 이용해서 실행 후 대기하고 있다가 특정 조건이 만족되면 작업을 수행하고 다시 대기하도록 작성됨 

### 쓰레드 그룹 (Thread Group)
- 서로 관련된 쓰레드를 그룹으로 묶을 수 있음
- 자신이 속한 쓰레드 그룹이나 하위 쓰레드 그룹은 변경할 수 있지만 다른 쓰레드 그룹의 쓰레드는 변경하지 못함 
- 모든 쓰레드는 반드시 쓰레드 그룹에 포함되어야 하며, 쓰레드 그룹을 지정하지 않은 쓰레드는 기본적으로 자신을 생성한 쓰레드와 같은 쓰레드 그룹에 속함

<br>

## 동기화, Synchronized
-

<br>

## 데드락
-

<br>
<hr>

### 참고 자료 
- [Java Thread - 자바 쓰레드](https://dailyworker.github.io/java-thread/#step-23-%EC%93%B0%EB%A0%88%EB%93%9C%EC%9D%98-%EC%9A%B0%EC%84%A0%EC%88%9C%EC%9C%84)
- [자바(Java)의 기초 박살내기 - 스레드(Thread)](https://raccoonjy.tistory.com/15)
- [자바: Thread 클래스와 Runnable 인터페이스](https://www.daleseo.com/java-thread-runnable/)



