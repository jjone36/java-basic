# 9주차. [예외 처리](https://github.com/whiteship/live-study/issues/9)
- 날짜 : 21.01.16
- 목표 : 자바의 예외처리에 대해 학습하기

<hr>

## 자바에서 예외 처리 방법 (try, catch, throw, throws, finally)
- 에외 처리(exception handling)이란 프로글매 실행 시 발생할 수 있는 예기치 못한 예외 경우를 대비한 코드를 작성하는 것<br>
    예외 발생으로 인한 갑작스러너 프로그램 종류를 막고 정상적인 실행 상태를 유지도록 함 

### try-catch
- 예외 처리 구문 기본 형태
    ```java
    try {
        // 예외가 발생할 가능성이 있는 문장들
    } catch (Exception1 e1) {
        // Exception1이 발생할 경우 처리하기 위한 문장들
    } catch (Exception2 e2) {
        // Exception2이 발생할 경우 처리하기 위한 문장들
    } finally {
        // 예외 발생여부에 관계없이 항상 수행할 문장들
    }
    ```
- 하나의 메서드 내에서 여러 개의 try-catch문 사용 가능
- try 블럭 또는 catch 블럭 내에서 또 다른 try-catch문 포함 가능 
    - 만약 catch 블럭 내에서 또다른 try-catch문 사용 시 Exception에 대한 참조변수는 다른 이름을 사용해야 함 

### finally
- `finally` 블럭은 try-catch문과 함께 예외의 발생여부에 상관없이 코드를 실행시킴 
- try-catch 문의 마지막 끝에 선택적으로 덧붙여 사용할 수 있음 
- 예외가 발생하면 *try -> catch -> finally* 순으로, 발생하지 않으면 *try -> finally* 순으로 실행됨
- try 블럭에서 return문이 실행되는 경우, **finally 블럭의 문장들이 먼저 실행된 후에** 현재 실행 중인 메서드를 종료함 

### throw 
- `throw` 키워드를 통해 예외를 발생시킬 수 있음
- 예시
    ```java
    public boolean test() {
        try {
            Exception e = new Exception("에러!!!");
            throw e;
        } catch (Exception e) {
            System.out.println("에러 메시지: " + e.getmessage());
            return false;
        }
        System.out.println("이상 무!!!");
        return true;
    }
    ```
    - 고의적으로 Exception을 만들고 에러 메시지를 입력할 수 있음 

### throws
- `throws` 키워드를 사용하여 try-catch문 외에 예외를 메서드에 선언할 수 있음
    ```java
    void method() throws Exception1, Exception2 {
        // 메서드의 내용
    }
    ```
- 메서드의 선언부에 에러 케이스들을 선언함으로써 어떠한 예외들이 처리되어져야 하는지 쉽게 파악할 수 있음
- 일반적으로 RuntimeException클래스들은 적지 않고, 보통 반드시 처리해주어야 하는 예외들만 선언함 
- 예외를 전달받은 메서드가 또다시 자신을 호출한 메서드에게 전달할 수 있고, <br>
    예외 처리를 가진 메서드를 만날 때까지 연속적으로 호출스텍에 있는 메서드들을 따라 전달되다가 <br>
    main() 메서드에서도 예외 처리가 되지 않으면 main 메서드 마저 종료되어 프로그램 전체가 종료됨

## Exception과 Error의 차이는?
- 자바에서는 실행 시 발생할 수 있는 프로그램 오류를 **에러(Error)**와 **예외(Exception)**으로 구분함
    - Error : 프로그램 코드에 의해 수습될 수 없는 심각한 오류 (ex. OutOfMemoryError, StackOverflowError)
    - Exception : 프로그램 코드에의해서 수습될 수 있는 다소 미약한 오류  

## 자바가 제공하는 예외 계층 구조와 RuntimeException과 RE가 아닌 것의 차이
- 모든 에외의 최고 상위 클래스는 Exception임 
<img src="http://journals.ecs.soton.ac.uk/java/tutorial/java/exceptions/images/throwableHierarchy_trans.gif" width=70%>

- [사진 출처](http://journals.ecs.soton.ac.uk/java/tutorial/java/exceptions/throwable.html)
    - Exception 클래스는 사용자의 실수와 같이 외적인 요인에 의해 발생할 수 있는 예외들
    - RuntimeException 클래스들은 주로 프로그래머의 실수에 의해서 발생될 수 있는 예외들임 
        - 예시 : IndexOutOfBoundException(배열의 범위를 벗어남), NullPointerException(값이 null인 참조변수의 멤버를 호출함), ClassCastException(클래스 형변환 에러)
        - 되도록 RunctimeException 예외들이 발생할 가능성이 있는 코드들은 try-catch문을 사용하기 보다 코드 상에서 예외가 발생하지 않도록 하자
    - RuntimeException 그룹에 속하는 예외들은 예외 처리를 해주지 않아도 컴파일 시 문제가 되지 않지만, Exception 클래스에 속하는 그룹들은 반드시 에외 처리를 해줘어야 컴파일 에러가 발생하지 않음

## 커스텀한 예외 만드는 방법
- 기존에 정의된 예외 클래스 외의 필요에 따라 커스텀한 예외 클래스를 정의할 수 있음
    ```java
    class MyException extends Exception {
        private infal int ERR_CODE;

        MyException(String msg, int code) {
            super(msg);
            ERR_CODE = code;
        }

        MyException(String msg) {
            this(msg, 100);
        }

        public int getCode() {
            return ERR_CODE;
        }
    }
    ```
    - 보통 Exception 클래스에서 상송받지만 필요에 따라 알맞은 예외 클래스를 사용 가능함

### 참고 자료 
- [자바의 정석](http://www.yes24.com/Product/Goods/24259565)