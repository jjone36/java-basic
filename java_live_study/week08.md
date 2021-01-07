# 8주차. [인터페이스](https://github.com/whiteship/live-study/issues/8)
- 날짜 : 2020.01.07
- 목표 : 자바의 인터페이스에 대해 학습하기

<hr>

## 인터페이스란?
- 클래스들이 구현해야 하는 동작을 지정하는 추상형 클래스임. 그 자체만으로 사용되기 보다는 다른 클래스를 작성하는데 도움을 주는 목적으로 작성됨
- 인터페이스는 *able to*라는 의미를 내포함. 즉, 특정 기능을 표현하는 클래스<br> 
인터페이스의 이름은 `Runnable`, `Serializable`, `Comparable`와 같이 '~할 수 있는'의 의미를 내포할 수 있게 '~able'로 끝나는 것들이 많음 
- 인터페이스의 장점
    - 일관되고 정형적인 표준화가 가능하고, 개발 시간이 단축됨
    - 상속 관계에 있지도 않고, 같은 상위클래스를 가지고 있지 않는.. 서로 관계없는 클래스들끼리 하나의 인터페이스를 공통으로 연결해줌으로써 관계를 맺어줄 수 있음
    - 클래스 선언과 구현을 분리함으로써 실제 구현 과정에서 다형성을 가질 수 있음. 자율적이고 독립적인 프로그래밍이 가능함
- 인터페이스와 추상 클래스의 차이점 
    - 일단, 자바에서 클래스는 2가지 종류가 있음 : 일반 클래스와 추상 클래서.. 즉, 인터페이스는 클래스가 아님
    - 인터페이스는 추상 메소드만을 가질 수 있으며 일반 변수를 가질 수 없음 <br>
    추상클래스는 추상 메소드뿐 아니라 일반 메소드, 일반 변수를 가짐 (인터페이스의 역할에 구현체도 얹은 개념)
    - 인터페이스와 추상 클래스 둘 다 인스턴스화 할 수 없고, 상속으로 받아와야함 <br>
    이때 사용하는 키워드가 인터페이스는 `implements`, 추상클래스는 `extends`임<br>
    (키워드에서도 알 수 있듯이, 인터페이스는 엄밀히 구분하면 상속의 개념이 아님)
    - 클래스가 여러 개의 클래스를 한 번에 상속받지 못하지만, 인터페이스를 사용하면 여러 개를 붙일 수 있음
    - 결론적으로 인터페이스는 *설계도*의 역할이며, 추상 클래스는 *상속*의 목적으로 사용됨

## 인터페이스 정의하는 방법
- 일반 클래스와 달리 `class`가 아닌 `interface`로 정의함 
    ```java
    interface MyInterface {
        public static final int num = 0;
        String name = ""        // public static final String name과 같음

        public abstract move(int num);
        String getName();       // public static final String getName()과 같음
    }
    ```
    - 접근제어자로 `public` 또는 `default`를 사용함
    - 모든 멤버변수는 `public static final`이어야 하며 생략 가능함
    - 모든 메서드는 `public abstract`이어야 하며 생략 가능함

## 인터페이스 구현하는 방법
- 그 자체로 인스턴스를 생성할 수 없으며, 자신에 정의된 추상메서드의 몸통을 만들어주는 클래스를 작성해줘야함
    ```java
    class Example implements MyInterface {
        
        public int move(int num) {
            return 2*num;
        }

        public String getName() {
            return "I'm Interface";
        }
    }
    ```
- 만약 인터페이스의 메서드 중 일부만 구현한다면 추상클래스를 사용해야함
    ```java
    abstract class Example2 implements MyInterface {
        public int move(int num) {
            return 2*num;
        }
    }
    ```

## 인터페이스 레퍼런스를 통해 구현체를 사용하는 방법
- 위에서 언급한 바와 같이 그 자체로 인스턴스를 생성할 수 없기 때문에 `new` 키워드를 통해 인스터스화할 수 없음
- 인터페이스를 상속받은 클래스가 인터페이스 타입을 가지면서 해당 클래스를 인스터스를 생성하는 방식으로 사용함
    ```java
    class Example implements MyInterface {
        // 구현체...
    }
    ```
    ```java
    class Example2 {
        public void main() {
            // MyInterface myInterface = new MyInterface();   -> 불가능!
            MyInterface example = new Example();
            example.move();
        }
    }
    ```

## 인터페이스 상속
- 인터페이스 또한 다른 인터페이스를 상위로 상속 받을 수 있음
    ```java
    interface MyInterface extends Runnable, Serializable {
    }
    ```
    - 이때 해당 인터페이스를 상속 받은 구현 클래스는 그 상위 인터페이스의 추상 메소드까지 구현해야 함

## 인터페이스의 기본 메소드 (Default Method), 자바 8
- 기본 구현을 가지는 메서드. 앞에 `default`를 붙이며, 추상 메서드와 달리 일반 메서드처럼 구현체 `{}`가 있어야함. 이는 구현하는 클래스에서 재정의할 수 있음
- 접근 제어자는 `public`이며 생략 가능
- 예시 : 
    ```java
    interface MyInterface {
        void move(int num);

        // dafault 메서드
        default void moveForward() {
            System.out.println("This is default method");
        } 
    }
    ```
    ```java
    class Example implements MyInterface {

        @Override
        public void moveForward() {
            System.out.println("default method called");
        }
    }
    ```
- 하위 호환성을 지원함
    - 인터페이스를 다른 여러 클래스에서 연결하여 사용하고 있는 상태에서 해당 인터페이스에 변경 사항이 생겼을 때 하위 구현체들에 영향을 주게 됨. 에러 발생
    - 이를 보완하기 위해 deault method를 사용하면, 이미 구현체를 가지므로 하위 구현체에서는 그대로 사용하거나, 오버라이딩하여도 충돌이 생기지 않음

## 인터페이스의 static 메소드, 자바 8
- 앞에 `static` 예약어를 붙이며, 인스턴스 생성과 상관없이 인터페이스 타입으로 호출함. 즉 인터페이스를 직접 참조하여 사용
- 접근 제어자는 `public`이며 생략 가능
- 예시 : 
    ```java
    interface MyInterface {
        void move(int num);

        // static 메서드
        static void moveBackward() {
            System.out.println("This is static method");
        } 
    }
    ```
    ```java
    class Example implements myInterface {

        @Override
        public void test() {
            MyInterface myInterface = new MyInterface();
            myInterface.moveBackward();
        }
    }
    ```
- default 메소드과 달리 하위 구현체에서 재정의하여 사용할 수 없음

## 인터페이스의 private 메소드, 자바 9
- 해당 인터페이스의 내부에서만 접근 가능하도록 하여 인터페이스 내에서의 코드 재사용성과 캡슐화를 가는ㅇ하게 함 
- 해당 메서드는 상속되지 않으며 외부에서 접근할 수 없음
- 예시 : 
    ```java
    interface MyInterface {
        void move(int num);

        // private 메서드
        private void stop() {
            System.out.println("This is private method");
        } 
    }
    ```

<br>
<hr>

### 참고 자료 
- [자바의 정석](http://www.yes24.com/Product/Goods/24259565)
- [스프링 입문을 위한 자바 객체 지향의 원리와 이해](https://wikibook.co.kr/java-oop-for-spring/)
- [자바 인터페이스(Interface), 디폴트 메서드와 static 메서드](https://velog.io/@ednadev/%EC%9E%90%EB%B0%94-%EC%9D%B8%ED%84%B0%ED%8E%98%EC%9D%B4%EC%8A%A4Interface-%EB%94%94%ED%8F%B4%ED%8A%B8-%EB%A9%94%EC%84%9C%EB%93%9C%EC%99%80-static-%EB%A9%94%EC%84%9C%EB%93%9C)
- [Private Methods in Java 9 Interface](https://www.geeksforgeeks.org/private-methods-java-9-interfaces/)