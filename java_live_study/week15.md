# 15주차. [람다식](https://github.com/whiteship/live-study/issues/15)
- 날짜 : 21.03.05
- 목표 : 자바의 람다식에 대해 학습

<hr>

## 람다란 ?
코드 블록. 코드를 변수처럼 사용할 수 있다. 기존에는 코드를 작성하기 위해서 함수와 클래스를 생성해야만 했는데, 자바8부터는 클래스와 클래스 내에 메서드를 생성하지 않고도 코드블록을 작성할 수 있다.
- Before
    ```java
    public static void main(String[] args) {
        Runnable sampleRunnable = new Sample();
        sample.run();

        class Sample implements Runnable {
            @Override
            public void run() {
                System.out.println("Hello");
            }
        }
    }
    ```
- After
    ```java
    public static void main(String[] args) {
        Runnable sampleRunnable = () -> System.out.println("Hello");
        sampleRunnable.run();
    }
    ```

<br>

## 람다식 사용법
- 실행문이 1줄일 경우 `{}` 생략 가능
- 매개변수가 1개이거나 타입이 같을 경우, 타입 생략 가능
    ```java
    public class Application {
        public static main(String[] args) {
            ExmapleLB exampleLB = (num1, num2) -> System.out.println(num1 + "&" +  num2);
            exampleLB.method();
        }
    }
    ```
- 매개변수가 1개일 경우 `()` 생략 가능
    ```java
    public class Application {
        public static main(String[] args) {
            ExmapleLB exampleLB = num1 -> System.out.println(num1);
            exampleLB.method();
        }
    }
    ```
- 매개변수가 없을 경우는 `()`만 작성
    ```java
    public class Application {
        public static main(String[] args) {
            ExmapleLB exampleLB = () -> System.out.println("No Arguments");
            exampleLB.method();
        }
    }
    ```
- 반환값이 있는 경우
    ```java
    public class Application {
        public static main(String[] args) {
            ExmapleLB exampleLB = (num1, num2) -> {
                return num1 + num2;
            }
            System.out.println("answer is " + exampleLB.method(2, 3));
        }
    }
    ```
- @FuntionalInterface 어노테이션을 붙이면 컴파일 시 에러를 잡아줌 

<br>

## 함수형 인터페이스
- 추상 메서드를 하나만 갖는 인터페이스를 자바8부터 ***함수형 인터페이스***라고 함
- 함수형 인터페이스만을 람다식으로 변경할 수 있음
```java
public static void main(String[] args) {
    Runnable r = () -> System.out.println("Hello World");
    r.run();            // -> Hello World
}

@FunctionalInterface
public interface Runnable {
    void run();
}
```
- 이 밖에 메서드의 호출 인자나 반환값으로 람다식을 사용할 수도 있음
- 자바8부터는 여러 함수형 인터페이스 API를 제공함 

    |함수형 인터페이스|추상 메서드|기능|
    |---|---|---|
    `Runnable`|`void run()`|실행 가능한 인터페이스
    `Supplier<T>`|`T get()`|제공 가능한 인터페이스
    `Consumer<T>`|`void accept(T t)`|consume 가능한 인터페이스
    `Function<T, R>`|`R apply(T t)`|입력을 받아 출력하는 인터페이스
    `Predicate<T>`|`Boolean test(T t)`|입력을 받아 True/False를 반환하는 인터페이스
    `UnaryOperator<T>`|`T apply(T t)`|단항 연산을 하는 인터페이스

- 자바8 이전에 인터페이스는 *정적상수, 추상 인스턴스 메서드*만 멤버로 가질 수 있어음
- 자바8 이후에 인터페이스는 정적상수, 추상 인스턴스 메서드 이외에 ***default 메소드(몸체가 구현된 인스턴스 메서드), static 메소드(몸체가 구현된 정적 메서드)***도 가질 수 있음
- 함수형 인터페이스에서는 추상 메서드는 단 하나만 갖되 정적 상수, default 메소드, static 메소드는 여러 개 가질 수 있음

<br>

## Variable Capture
- 람다식 외부에 있는 변수를 참조할 수 있음 
    ```java
    public class Application {

        private int num1 = 12;

        public static main(String[] args) {
            ExmapleLB exampleLB = () -> System.out.println(num1));
        }
    }
    ```
    - 이처럼 파라미터로 넘겨진 변수가 아닌 외부에서 정의도니 변수를 자유 변수라고 함
    - 자유 변수를 참조하는 행위를 **람다 캡쳐링**이라고 함 
- 람다 캡쳐링의 조건
    - 지역변수는 `final`로 선언되야함
    - `final`로 선언되지 않은 지역변수는 `final`처럼 동작해야하고, 값의 재할당이 일어나면 안됨 
- 변수가 `final`이어야 하는 이유 -> JVM의 메모리 구조 떄문!
    - 람다에서 지역 변수에 접근하는 방식은 직접적으로 접근하지 않고 변수를 자신의 스레드 스택에 복사하기 때문
    - 따라서 값이 변하면 데이터 정합성이 꺠짐 

<br>

## 메서드 레퍼런스와 생성자 레퍼런스
### 메서드 레퍼런스
- `Arrays.stream(ages).forEach(age -> System.out.println(age))` <br> 
-> `Arrays.stream(ages).forEach(System.out::println)`으로 바꾸어 쓸 수 있음 

|메서드 레퍼런스 유형|람다식 인자|ex|
|---|---|---|
`클래스::정적메서드`|정적 메서드의 인자가 됨|`num -> Math.sqrt(num)`<br>-> `Math::sqrt`
`인스턴스::인스턴스메서드`|인스턴스 메서드의 인자가 됨|`a -> System.out.println(a)`<br>-> `System.out::println`
`클래스::인스턴스메서드`|첫 번째 인자는 인스턴스, 나머지 인자는 메서드의 인자가 됨|`(a, b) -> a.compareTo(b)`<br>-> `Integer::compareTo`

### 생성자 레퍼런스
메서드 레퍼런스와 같은 표현방식을 생성자를 만들 때에도 활용할 수 있다.
```java
public class Employee{
    String name;
    Integer age;

    public Employee(String name, Integer age){
        this.name=name;
        this.age=age;
    }
}

// Employee를 생성하는 Factory 인터페이스
// 추상메서드가 1개이므로 함수형 인터페이스이다.
public Interface EmployeeFactory {
    public abstract Employee getEmployee(String name, Integer age);
}

public static void main(String[] args) {
    EmployeeFactory empFactory = Employee::new; // Employee 객체를 생성하는 EmployeeFactory를 생성자레퍼런스 형태로 생성.
    Employee emp= empFactory.getEmployee("John Hammond", 25);
}
```
**코드출처 : https://www.javabrahman.com/java-8/constructor-references-java-8-simplified-tutorial/*

<br>
<hr>

### 참고 자료 
- [스프링 입문을 위한 자바 객체 지향의 원리와 이해](https://wikibook.co.kr/java-oop-for-spring/)
- [Java- Lambda Expression(람다식)이란?](https://galid1.tistory.com/509)
- [자바 람다식 (Java Lambda Expression) - 기초편](https://m.blog.naver.com/2feelus/220695347170)
- [FuntionalInterface Annotation Type in Java](https://stackoverflow.com/questions/29954781/funtionalinterface-annotation-type-in-java)