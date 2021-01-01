# 5주차. [클래스](https://github.com/whiteship/live-study/issues/5)
- 날짜 : 20.12.19
- 목표 : 자바의 Class에 대해 학습하기

<hr>
<br>

## 클래스 정의하는 방법
- 객체(Object)와 클래스(Class)란?   (출처 - [객체 지향의 사실과 오해](http://www.yes24.com/Product/Goods/18249021))
> **객체**란 식별 가능한 개체 또는 사물이다. 객체는 자동차처럼 만질 수 있는 구체적인 사물일 수도 있고, 시간처럼 추상적인 개념일 수도 있다. 객체는 구별 가능한 *식별자*, 특징적인 *행동*, 변경 가능한 *상태*를 가진다. 소프트웨어 안에서 객체는 저장된 상태와 실행 가능한 코드를 통해 구현된다.      *(p47)*

> 객체란 특정한 개념을 적용할 수 있는 구체적인 사물을 의미한다. 개념이 객체에 적용됐을 때 객체를 개념의 인스턴스라고 한다.      *(p84)*

> **타입**은 개념과 동일하다. 즉, 우리가 인식하고 있는 다양한 사물이나 객체에 적용할 수 있는 아이디어나 관념을 의미한다. 어떤 객체에 타입을 적용할 수 있을 때 그 객체를 타입의 인스턴스라고 한다.      *(p89)*

> 객체를 분류하는 기준은 타입이며, 타입을 나누는 기준은 객체가 수행하는 행동이다. 그리고 타입을 구현할 수 있는 한 가지 방법이 클래스이다.      *-(105)*

> 클래스는 타입을 구현하기 위해 프로그래밍 언어에서 제공하는 구현 메커니즘이다.      *(p105)*

- 자바에서 클래스를 정의하는 방법
    - 객체는 속성(상태, 멤버변수)와 기능(행동, 메소드)을 가지며 이는 클래스로 다음과 같이 구현함
        ```java
        Class Car {
            // 멤머변수
            String color;
            int door;

            // 메서드
            int getDoor() {
                return door;
            }
        }
        ```
    - 외부 클래스가 해당 클래스에 접근할 수 있는 범위는 **접근 지정자**를 통해 지정하여 캡슐화가 가능함
        - `public` : 접근 제한 없음
        - `protected` : 같은 패키지 내에서, 그리고 다른 패키지의 자손 클래스에서 접근 가능
        - `default` : 같은 패키지 내에서만 접근 가능
        - `private` : 같은 클래스 내에서만 접근 가능
        > 접근 범위는 `public` > `protected` > `default` > `private` 순임

<br>

## 객체 만드는 방법 (new 키워드 이해하기)
- 클래스로부터 객체를 만들어내는 과정을 클래스의 인스턴스화라하며 어떠한 클래스로부터 만들어진 객체를 클래스의 *인스턴스*라고 함
- **객체 생성자 메서드**(또는 생성자)란 반환값 없이 클래스의 같은 이름을 가진 메서드로 객체를 생성함 
- 자바에서는 인자가 없는 기본 생성자는 자동으로 만들어주어 클래스 정의 시 생략이 가능함. 하지만 인자가 하나라도 필요할 경우 클래스 정의에서 생성자를 직접 정의해주어야함 
- `new` 키워드와 생성자를 통해 다음과 같이 인스턴스를 만들 수 있음<br>
    `클래스이름 변수명 = new 클래스이름()`
- 이때 JVM에서 수행되는 과정은..<br>
    예를 들어 `Person p1 = new Person()`에서
    1. 연산자 `new` 에 의해서 heap메모리에 Person 클래스의 인스턴스가 생성됨
    2. 생성자 메소드 person()이 호출되어 수행되고
    3. 연산자 `new`의 결과로, 생성된 인스턴스의 주소가 반횐되어 참조변수 p1에 저장됨

<br>

### *(추가) 싱글턴 패턴*
- 싱글턴 패턴이란 인스턴스를 하나만 만들어 사용하기 위한 디자인 패턴으로 인스턴스를 하나만 만들어 해당 인스턴스를 계속 재사용함 
- `new` 키워드를 사용할 수 없고 생성자에 `private` 제어자를 지정함
- 유일한 단일 객체를 반환하는 정적 메서드가 필요함
- 유일한 단이리 객체를 참조할 정적 참조 변수가 필요함
- 예시
    ```java
    public class Printer {
        // 정적 참조 변수 (외부에 제공할 자기 자신의 인스턴스)
        private static Printer printer = null;

        // private 생성자
        private printer() { }

        // 객체 반환 정적 메서드
        public static Printer getPrinter() {
            if (printer == null) {
                printer = new Printer();
            }
            return printer;
        }
    }
    ```
    ```java
    public class Application {
        public static void main(String[] args) {
            Printer printer = printer.getPringer();
        }
    }
    ```
- 참고 : [싱글턴 패턴이란](https://gmlwjd9405.github.io/2018/07/06/singleton-pattern.html)

<br>

## 메소드 정의하는 방법
- 메서드는 객체에게 주어진 책임을 수행하는 행동의 집합임
- 하나의 메서드는 한 가지 기능만 수행하도록 설계하는 것이 바람직함 
- 자바에서 메서드를 작성하는 방법 <br> 
    `리턴타입 메서드이름(인풋 인자) { // 호출시 수행할 명령문 }`
    ```java
    int add(int a, int b) {
        return a + b;
    }
    ```
    - 이때 JVM에서 수행되는 과정은..<br>
        1. 메소드 add가 호출되면 수행에 필요한 만큼의 메모리를 stack영역에 할당함
        2. 메서드 수행이 마치고나면 사용했던 메모리를 반환하고 스택에서 제거됨

<br>

## 생성자 정의하는 방법
- 생성자의 이름은 클래스의 이름과 같아야 하며 반환값이 없음
- 생성자에는 기본 생성자와 명시적 생성자가 있음
    - 기본 생성자(default constructor) : 클래스 내부에 선언된 생성자가 없는 경우, 컴파일러가 자동으로 생성해줌
    - 명시적 생성자 : 매개변수가 있는 생성자
        ```java
        Class Car {
            // 멤머변수
            String color;
            int door;

            // 생성자 
            Car() { }
            Car(String color, int door) {
                this.color = color;
                this.door = door;
            }
        }
        ```

<br>

## this 키워드 이해하기
- `this` 키워드는 인스턴스 자신을 가리키는 참조 변수, 즉 인스턴스의 주소가 저장됨
- 클래스 내부의 필드 이름과 메소드를 통해 넘어온 외부 변수명이 같을 경우, 클래스 내부 필드 변수를 구분하기 위해 사용함
- 사용 예시 : 
    1. 참조 변수 명료화 
        ```java
        public class Foo
        {
            private String name;

            public void setName(String name) {
                this.name = name;
            }
        }
        ```
        - 지역 변수와 속성(객체 변수, 정적 변수)의 이름이 같은 경우 지역 변수를 우선함 
        - 객체 변수와 이름이 같은 지역 변수가 있는 경우 객체 변수를 사용하려면 `this` 키워드로 구분함 (예: `this.name`)
        - 정적 변수와 이름이 같은 지역 변수가 있는 경우 정적 변수를 사용하려면 클래스명을 접두사로 구분함 (예: `Foo.name`)
    2. 인자로 자기자신을 넘겨줄 때
        ```java
        public class Foo
        {
            private String name;

            public void setName(String name) {
                this.name = name;
            }
        }
        ```
    3. 한 생성자에서 다른 생성자를 호출 할 때. 반드시 첫 줄에서만 호출이 가능함
        ```java
        public class Foo {
            private String name;

            public Foo() {
                this("foo1");
            }
        }
        ```
- 참고 : [(stackoverflow) When should I use "this" in a class?](https://stackoverflow.com/questions/2411270/when-should-i-use-this-in-a-class)


<br>

## (Optional) 자료구조- binary tree, BFS, DFS
### Binary Tree
```diff
- int 값을 가지고 있는 이진 트리를 나타내는 Node라는 클래스를 정의하세요.
- int value, Node left, right를 가지고 있어야 합니다.
```
- 자료 구조  
    - 자식 노드를 2개 이하로 가질 수 있는 tree 구조
    <img src="https://upload.wikimedia.org/wikipedia/commons/f/f7/Binary_tree.svg" width=60%>
    - [이미지출처](https://en.wikipedia.org/wiki/Binary_tree)
- [코드로 구현](https://github.com/jjone36/self-study/blob/main/java_live_study/wk04/src/main/java/dataStructure/Node.java)

### Breadth-First Search, Depth-First Search
```diff
- BinrayTree라는 클래스를 정의하고 주어진 노드를 기준으로 출력하는 bfs(Node node)와 dfs(Node node) 메소드를 구현하세요.
- DFS는 왼쪽, 루트, 오른쪽 순으로 순회하세요.
```
- 자료 구조
    - BFS, 너비 우선 탐색
        - 두 노드 사이의 최단 경로 탐색 시 사용 가능
    - DFS, 깊이 우선 탐색
        - 모든 노드를 방문 하고자 하는 경우 사용 가능
        - 단순 검색 속도 자체는 BFS보다 느림
    <img src="https://miro.medium.com/max/1088/1*INwehwNaWrUmOvq_a5wMWg.gif" width=60%>
    - [이미지출처](https://medium.com/@tim_ng/bfs-and-dfs-52d3cb642a0e)
- [코드로 구현](https://github.com/jjone36/self-study/blob/main/java_live_study/wk04/src/main/java/dataStructure/BinaryTree.java)

<br>
<hr>

### 참고 자료 
- [자바의 정석](http://www.yes24.com/Product/Goods/24259565)
- [객체 지향의 사실과 오해](http://www.yes24.com/Product/Goods/18249021)
- [스프링 입문을 위한 자바 객체 지향의 원리와 이해](https://wikibook.co.kr/java-oop-for-spring/)
- [(stackoverflow) When should I use "this" in a class?](https://stackoverflow.com/questions/2411270/when-should-i-use-this-in-a-class)
- [위키백과](https://en.wikipedia.org/wiki)
- [깊이 우선 탐색(DFS)이란](https://gmlwjd9405.github.io/2018/08/14/algorithm-dfs.html)
- [너비 우선 탐색(BFS)이란](https://gmlwjd9405.github.io/2018/08/15/algorithm-bfs.html)