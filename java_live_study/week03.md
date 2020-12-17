# 3주차. [연산자](https://github.com/whiteship/live-study/issues/3)
- 날짜 : 2020.11.28
- 목표 : 자바가 제공하는 다양한 연산자를 학습하기

<hr>

## 산술 연산자
- 산술 연산자는 사칙 연산을 다루는 기본적이면서 가장 많이 사용되는 연산자임
- 산술 연산자는 모두 두 개의 피연산자를 가지는 이항 연산자이며 언산을 수행하기 전에 피연산자의 타입을 모두 일치시킴
- 산술 연산자에는 사칙 연산자, 나머지 연산자, 쉬프트 연산자가 있음 
    - 사칙 연산자(`+`, `-`, `*`, `/`) 
        - int형보다 크기가 작은 자료형은 Int형으로 형변환 후 연산을 수행함
            ```java
            byte a = 10;
            byte b = 30;
            //byte c = a + b;     -> int형이 byte형보다 크기 때문에 명시적인 형변환이 필요함 
            byte d = (byte)(a + b); 

            byte e = (byte)(a * b); 
            System.out.println(c);      // 44 (연산 후 int형->byte로 형변환 과정에서 자료 손실 발생)  
            ```
        - 두 개의 피연산자 중 자료형의 표현 범위가 큰 쪽에 맞추어 형변환 후 연산을 수행함
        - 피연산자가 정수형인 경우 0으로 나눌 수 없음(실행 에러남). 부동소수점값인 0.0f, 0.0d로 나누는 것은 가능하나 `NaN`을 반환함
        - char형을 사칙 연산 시 int형으로 형변환하여 연산을 수행함
            ```java
            char a = 'a'
            char b = a;
            char c = ' ';

            int i = a + 1               // `a` + 1 -> int형인 91로 변현하여 -> 98  
            c3 = (char)(a + 1);         // 98에서 다시 char형으로 변환하여 -> b
            System.out.println(b++);    // b

            //char d = a + 1;           컴파일 에러
            char e = 'a' + 1;           // 'a'는 리터럴 값이기 때문에 에러 없음
            ```
        - int형 간의 나눗셈을 수행하면 결과가 float 혹은 double이 아닌 int를 반환함<br> 
        또한 나눗셈의 결과를 반올림하지 않고 내림으로 버림 (결과가 1.5일 경우 -> 1로 반환)
    - 나머지 연산자(`%`)
        - 왼쪽의 피연산자를 오른쪽 피연산자로 나누어 난 나머지 값을 돌려주는 연산자 
        - 피연산자에 음수가 있을 경우 왼쪽에 있는 피연산자의 부호를 따름 
            ```java
            System.out.println(10 % 8);     // 2
            System.out.println(-10 % 8);    // -2
            System.out.println(10 % -8);    // 2    
            System.out.println(-10 % -8);   // -2
            ```
    - 쉬프트 연산자(`<<`, `>>`, `>>>`)
        - 정수형 연산자에만 사용 가능함
        - 피연산자를 2진수로 표현했을 때 각 자리를 오른쪽/왼쪽으로 이동(shift)한 값으로 반환함<br>
           > `x << n`이면 x*2<sup>n</sup>와 같고, `x >> n`은 x/2<sup>n</sup>와 같음
        - 음수에 `>>`를 사용할 경우 부호를 지켜 앞자리를 `1`로 채우지만, `>>>`는 부호 상관없이 `0`로 채움
        - 쉬프트 연산자의 장점은 나눗셈(`/`)과 곱셈(`*`) 연산자보다 실행 속도가 빠름

## 비트 연산자
- 비트 연산자(`&`, `|`, `^`)
    - `&` : AND연산자. 피연산자 양 쪽 모두 1이면 1을 반환하고 그 외 0을 반환
    - `|` : OR연산자. 피연산자 중 한 쪽이 1이면 1을 반환하고 둘 다 1이 아니면 0을 반환 
    - `^` : XOR연산자. 피연산자 값이 서로 다를 때 1을 반환하고 같으면 0을 반환
- 비트 전환 연산자(`~`)
    - 정수형과 char형에만 사용될 수 있음 
    - 피연산자를 2진수로 표현했을 때, 0은 1로 1은 0으로 바꿈 
    - 음수를 2진수로 표현하는 방법
        ```java
        byte a = 10;
        System.out.println("a= " + a);          // 10
        System.out.println("~a= " + ~a);        // -11
        System.out.println("~a+1= " + ~a+1);    // -10
        ```

## 관계 연산자 (비교 연산자)
- 두 개의 변수 또는 리터럴을 비교하는 데 사용되는 연산자로 결과는 `true/false`로 주어짐
- 대소 비교와 등가 비교 연산자가 있음
    - 대소 비교 연산자(`<`, `>`, `<=`, `>=`) 
        - boolean을 제외한 모든 기본형 변수에 사용가능하나 참조형에는 사용 불가함
    - 등가 비교 연산자(`==`, `!=`) 
        - 참조형을 포함한 모든 자료형에 사용 가능 
        - 단, 기본형과 참조형 간에 형변환이 불가하기 때문에 기본형과 참조형간에 비교로는 사용 불가함 
- 비교 연산 시, 자료형의 범위가 큰 쪽으로 변환 후 연산 비교함 

## 논리 연산자
- 피연산자로 boolean형 또는 boolean형 값을 결과로 반환하는 조건식만을 허용하는 연산자
    - `||` : OR연산자. 피연산자 중 어느 한 쪽만 ture이면 true 결과를 반환
    - `&&` : AND연산자. 피연산자 양쪽 모두 ture여야 ture 결과를 반환함
- 같은 조건식이라도 피연산자의 위치에 따라 연산 속도가 달라질 수 있으므로 AND연산(`&&`)의 경우 false일 확률이 높은 연산자를 좌측에 놓아야 함
- 논리 부정 연산자(`!`)
    - boolean형에서만 사용할 수 있으며 true이면->false를, false이면->true를 반환함

## instanceof
- instanceof는 어떠한 객체가 특정 클래스의 인스턴스인지 확인하는 연산자 
- 인터페이스 구현 관계에서도 적용 가능함 
- 예시 : `Cat` 클래스가 `Animal` 클래스를 상속받는다고 할 때, 
    ```java
    Cat cat1 = new Cat();
    System.out.println(cat1 instanceof Anmial);     // true
    ```

## assignment(=) operator
- 변수에 값 또는 수식의 연산 결과를 저장하는데 사용함. 왼쪽에 반드시 변수가 위치, 오른쪽에는 리터럴이나 변수 또는 수식이 올 수 있음
- `final` 키워드를 붙이면 상수(constant)가 되고 선언과 동시에 값이 저장되며 변경이 불가함
- 연산자들 중에서 가장 낮은 연순 순위로 가장 마지막에 수행되고, 연산 진행 방향은 오른쪽 -> 왼쪽으로 진행됨 
- `i += 3;`와 같이 다른 연산자와 결합한 `op=` 형식으로도 사용 가능함

## 화살표(->) 연산자 
- 람다 표현식(lambda expression)이란 익명 객체를 생성하기 위한 표현식
- 자바는 객체 지향 언어이기에 메서드를 사용하려면 클래스를 통해 접근해야 가능했으나 JDK8부터 람다식을 통해 함수를 변수처럼 접근할 수 있고, 다른 메소드의 인자로 전달할 수도 있게 함
- 예시 : 메소드를 `(매개변수) -> { 실행문 }`의 형태로 작성하여 ***객체 생성 없이*** 사용할 수 있음
    - 기존 방식 : `.test()`메소드를 사용하기 위해서 `Calculator` 객체 생성이 필수임
    ```java
    public interface LbExample {
        public void test();
    }

    public class Calculator implements LbExample {
        @Override
         public void test() {
            System.out.println("This is lambda expression");
        }
    }

    public class Application {
        public static main(String[] args) {
            LbExample lbExample1 = new Calculator();
            lbExample1.test();
        }
    }
    ```
    - 람다 표현식 : 객체 생성 없이 메소드를 사용함 
    ```java
    public interface LbExample {
        public void test();
    }

    public class Application {
        public static main(String[] args) {
            LbExample lbExample1 = () -> System.out.println("This is lambda expression");
            lbExample1.test();
        }
    }
    ```
- 람다식은 interface의 형태를 빌려 사용해야하며 인터페이스는 1개의 추상 메소드를 가져야함. 이처럼 추상 메서드를 하나만 갖는 인터페이스를 **함수형 인터페이스**라고 하며 함수형 **인터페이스만을 람다식으로 표현** 가능함
- 람다식 사용 방법
    - 실행문이 1줄인 경우 `{}` 생략 가능함
    - 매개변수가 없을 경우 `()`만 사용 가능하고, 1개일 경우 `()` 생략 가능함 
    - 매개변수가 1개이거나 타입이 같을 경우, 타입 생략 가능 
    - 반환값이 있을 경우 
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
- `@FuntionalInterface` 어노테이션을 붙이면 컴파일 시 에러를 잡아줌

## 3항 연산자
- `(조건식) ? 식1 : 식2`의 형식으로 조건식의 결과에 따라 연산되는 식이 정해짐
    ```java
    x = 5;
    result = (x > 0)? x : -x;
    Systen.out.println(x);      // 5
    ```

## 연산자 우선 순위
- **산술 > 비교 > 논리 > 대입** 순으로 수행됨
- **단항 > 이항 > 삼항** 순으로 수행됨
- 단항 연산자와 대입 연산자를 제외한 모든 연산의 진행방향은 왼쪽에서 오른쪽으로 이루어짐 

## (optional) Java 13. switch operator

### 참고 자료 
- [자바의 정석](http://www.yes24.com/Product/Goods/24259565)
- [TCP school- 람다 표현식](http://www.tcpschool.com/java/java_lambda_concept)
- [스프링 입문을 위한 자바 객체 지향의 원리와 이해](https://wikibook.co.kr/java-oop-for-spring/)