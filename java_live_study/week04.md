# 4주차. [제어문](https://github.com/whiteship/live-study/issues/4)
- 날짜 : 2020.12.07
- 목표 : 자바가 제공하는 제어문 학습하기

<hr>
<br>

## 제어문이란?
- 프로그램이 원하는 결과를 얻기 위해서 프로그램의 순차적인 흐름을 제어해야할 경우 상용하는 명령문으로 조건문과 반복문이 있음 

### 조건문
- 조건문은 조건식과 문장을 포함하는 불럭{}으로 구성되어 조건식의 연산 결과에 따라 프로그램의 실행흐름을 변경할 수 있으며, if문, if/else문, if/else if/else문, switch문이 있음
- if/else if/else문 (if문, if/else문까지..)
    ```java
    if (조건식1) {
        // 조건식1가 true일 때 수행할 명령문
    } else if (조건식2) {
        // 조건식2가 true일 때 수행할 명령문
    } else {
        // 위의 조건식이 모두 만족하지 않을 때 수행할 명령문
    }
    ```

- switch문
    ```java
    switch (조건식) {
        // 조건식의 값에 따라 해당 case 실행 
        case 값1: 
            // 해당 case에 실행할 명령문
            break;
        case 값2:
            // 해당 case에 실행할 명령문
            break;
        case 값3:
            // 해당 case에 실행할 명령문
            break;
        default:
            // 위의 case에 모두 만족하지 않을 때 수행할 명령문 
            break;
    }
    ```
    - 각 case절 및 default절에서 break 키워드는 필수임 
    - default문은 필수가 아니며, 위치도 고정이 아님

### 반복문
- 프로그램 내에서 똑같은 명령을 일정 횟수만큼 반복하여 수행하도록 제어하는 명령문으로, while문, do/while문, for문, Enhanced for문이 있음
- while문
    ```java
    while(조건식) {
        // 조건식이 참인 동안 반복적으로 수행할 명령문 
    }
    ```

- do/while문
    ```java
    do {
        // 조건식이 참인 동안 반복적으로 수행할 명령문 
    } while(조건식);
    ```
    - 조건식의 결과와 상관없이 무조건 처음 한 번은 루프를 실행한 후 조건식을 검사함

- for문
    ```java
    for (초기식; 조건식; 증감식) {
        // 조건식이 참인 동안 반복적으로 수행할 명령문 
    }
    ```
    - 예시 코드
        ```java
        String[] cars = {"Volvo", "BMW", "Ford", "Oudi"}
        for (String car : cars) {
            System.out.println(car);
        }
        ```

<br>
<hr>

## JUnit 5란?
- 작성한 코드에 대하여 의도한 대로 구현되는지를 검증할 수 있는 단위 테스트를 제공하는 자바 프레임워크
- `src/test/java`의 하위 디렉토리에서 `@Test` 어노테이션을 통해 단위 테스트 가능 
- 사용법 : [maven depedency 추가](https://mvnrepository.com/artifact/junit/junit) 
- 기본 어노테이션 
    - `@BeforeAll` : 해당 메서드는 `static`이어야 하며 다른 테스트 메서드보다 먼저 실행됨, `@BeforeClass`와 동일
    - `@BeforeEach` : 각 메서드가 실행 되기 전에 해당 어노테이션이 달린 메서드가 실행됨, `@Before`와 동일
    - `@AfterAll` : 해당 메서드는 `static`이어야 하며 다른 테스트 메소드가 모두 실행된 후에 실행됨, `@AfterClass`와 동일
    - `@AfterEach` : 각 메서드가 실행된 후에 해당 어노테이션이 달린 메서드가 실행됨, `@After`와 동일
    - `@Disable` : 테스트 클래스 또는 메소드를 빌드 시 비활성함. `@Ignore`와 동일
    - 예시
        ```java
        public class TestAnnotation {
            @BeforeAll
            static void setup() {
                system.out.println("@BeforeAll annotation excuted")
            }

            @AfterAll
            static void done() {
                system.out.println("@AfterAll annotation excuted")
            }

            @BeforeEeach
            public void init() {
                system.out.println("@BeforeEeach annotation excuted")
            }

            @AfterEach
            public void tearDown() {
                system.out.println("@AfterEeach annotation excuted")
            }

            @Test
            public void test() {
                system.out.println("=== main test excuted!!")
            }
        }
        ```
        ```bash
        > @BeforeAll annotation excuted
        > @BeforeEeach annotation excuted
        > === main test excuted!!
        > @AfterEeach annotation excuted
        > @AfterAll annotation excuted
        ```
- Assertion & Assupmtion
    - `assertTrue()`, `assertAll()`을 통해 테스트 결과값을 확인할 수 있음
    - `assumeTrue()`, `assumeFalse()`, `assumingThat()`을 통해 해당 조건이 충족되는 경우에만 테스트가 실행되도록 조절 가능 
        - 실패 시 `TestAbortedExcetpion` 발생하고 테스트 수행은 되지 않음
        ```java
        public class TestAnnotation {

            @Test
            public void assertionExample() {
                int[] nums = {0,1,2,3,4};
                assertAll("nums", 
                    () -> assertEquals(nums[0], 0),
                    () -> assertEquals(nums[2], 2),
                    () -> assertEquals(nums[4], 4),
                );
            }

            @Test
            public void assumptionExample() {
                assumeTrue(5>1);
                assert(nums[0], is(0));

                assumingThat(
                    nums[0].equals(0),
                    () -> assert(nums[3], is[3])
                );
            }
        }
        ```

<br>

## live-study 대시 보드를 만드는 코드 구현 (과제1)
```diff
- 깃헙 이슈 1번부터 18번까지 댓글을 순회하며 댓글을 남긴 사용자를 체크 할 것
- 참여율을 계산하세요. 총 18회에 중에 몇 %를 참여했는지 소숫점 두자리가지 보여줄 것
- 깃헙 API를 익명으로 호출하는데 제한이 있기 때문에 본인의 깃헙 프로젝트에 이슈를 만들고 테스트를 하시면 더 자주 테스트할 수 있습니다.
```
- [링크](https://github.com/jjone36/self-study/tree/main/java_live_study/wk04/src/main/java/dashboard)

<br>

## 컬렉션 프레임 워크
### 컬렉션 프레임 워크란?
- '데이터 군을 저장하는 클래스들을 표준화한 설계 
- 컬렉션을 크게 3가지 타입이 존재한다고 인식하고 각 컬렉션을 다루는데 필요한 기능을 가진 3개의 인터페이스(List, Set, Map)를 가짐  
    <img src="https://media.vlpt.us/post-images/litien/eed517f0-0457-11ea-9409-85540b7409f6/-2019-11-11-4.48.20.png" width=60%>
    - [이미지 출처](https://velog.io/@litien/Collections-%EC%97%90-%EB%8C%80%ED%95%98%EC%97%AC-u5k2u4dl4i)
- List와 Set, Queue 인터페이스는 Collection 인터페이스를 상속받지만 구조적인 차이로 Map은 다른 형태로 컬렉션을 다루기 떄문에 같은 상속 계층도에 포함되지 않음
    - List 컬렉션 클래스
            - List 인터페이스는 중복을 허용하면서 저장 순서가 유지되는 컬렉션을 구현하는데 사용됨
            - ArrayList, LinkedList, Vector, Stack 등이 있음 

### (과제2) LinkedList 구현
```diff
- 정수를 저장하는 ListNode 클래스를 구현하세요.
- ListNode add(ListNode head, ListNode nodeToAdd, int position)를 구현하세요.
- ListNode remove(ListNode head, int positionToRemove)를 구현하세요.
- boolean contains(ListNode head, ListNode nodeTocheck)를 구현하세요.
```
- 자료 구조  
    - 저장된 데이터가 비순차적으로 분포되며 이러한 데이터들 사이를 링크(link)로 연결하여 구성함 
    - 단일 연결 리스트(Singly Linked List) : 다음(next) 요소를 가르키는 참조만을 가질 경우     
   
    <img src="http://www.tcpschool.com/lectures/img_java_singly_linked_list.png" width=60%>
    - [이미지출처](http://www.tcpschool.com/java/java_collectionFramework_list)

    - 이중 연결 리스트(Doubly Linked List) : 이전(previous)와 다음(next) 요소를 가르키는 참조 둘 다 가질 경우 
   
    <img src="http://www.tcpschool.com/lectures/img_java_doubly_linked_list.png" width=60%>
    - [이미지출처](http://www.tcpschool.com/java/java_collectionFramework_list)

- [코드로 구현](https://github.com/jjone36/self-study/tree/main/java_live_study/wk04/src/main/java/datastructure/ListNode.java)

### (과제3) Stack 구현
```diff
- int 배열을 사용해서 정수를 저장하는 Stack을 구현하세요.
- void push(int data)를 구현하세요.
- int pop()을 구현하세요.
```
- 자료 구조
    - 스택(Stack)은 마지막에 저장한 데이터를 가장 먼저 꺼내게 되는 LIFO구조를 따름 

        <img src="https://media.vlpt.us/images/sbinha/post/17a3cf61-fb95-4970-b66c-92a71b99846b/Screenshot%202020-04-20%2019.07.55.png" width=50%>

    - [이미지출처](https://velog.io/@sbinha/%EC%8A%A4%ED%83%9D-%ED%81%90)
- [코드로 구현](https://github.com/jjone36/self-study/tree/main/java_live_study/wk04/src/main/java/datastructure/Stack.java)

### (과제4) ListNode를 사용해서 ListNodeStack 구현 
```diff
- ListNode head를 가지고 있는 ListNodeStack 클래스를 구현하세요.
- void push(int data)를 구현하세요.
- int pop()을 구현하세요.
```
- [코드로 구현](https://github.com/jjone36/self-study/tree/main/java_live_study/wk04/src/main/java/datastructure/ListNodeStack.java)

### (과제5) Queue 구현 
```diff
- 배열을 사용해서 한번
- ListNode를 사용해서 한번.
```
- 자료 구조
    - 큐(Queue)는 처음에 저장한 데이터를 가장 먼저 꺼내게 되는 FIFO구조를 따름 

        <img src="https://media.vlpt.us/images/sbinha/post/dbc199b3-6959-464e-872d-39c503fa0b1b/Screenshot%202020-04-20%2019.19.59.png" width=50%>
        
    - [이미지출처](https://velog.io/@sbinha/%EC%8A%A4%ED%83%9D-%ED%81%90)

- 코드로 구현 : [배열 사용](https://github.com/jjone36/self-study/tree/main/java_live_study/wk04/src/main/java/datastructure/ArrayQueue.java), [ListNode 사용](https://github.com/jjone36/self-study/tree/main/java_live_study/wk04/src/main/java/datastructure/ListNodeQueue.java)

<br>
<hr>

### 참고 자료 
- [자바의 정석](http://www.yes24.com/Product/Goods/24259565)
- [TCP school- List 컬렉션 클래스](http://www.tcpschool.com/java/java_collectionFramework_list)
- [TCP school- 4.제어문](http://www.tcpschool.com/java/)
- [JUnit5 사용법 - 기본](https://gmlwjd9405.github.io/2019/11/26/junit5-guide-basic.html)
- [Implementing a Linked List in Java using Class](https://www.geeksforgeeks.org/implementing-a-linked-list-in-java-using-class/) 