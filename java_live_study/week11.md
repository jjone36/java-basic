# 11주차. [Enum](https://github.com/whiteship/live-study/issues/11)
- 날짜 : 21.01.29
- 목표 : 자바의 열거형에 대해 학습

<hr>

## enum 정의하는 방법
- Enum이란 Enumeration의 앞 글자로 **열거**의 의미를 가짐
- 자바에서 `final`로 문자열이나 숫자 등 기본 자료형 값을 고정하여 상수(constant)로 쓸 수 있음. 이때 클래스가 상수만을 가지고 있다면 클래스로 선언하지 않고 Enum으로 명시함
- 정의하는 방법
    ```java
    public enum Season {
        SPRING, SUMMER, AUTUMM, WINTER
    }
    ```
    - 마지막에 세미콜론`;`을 붙이지 않음. 상수들을 대문자로 씀 
    - Enum 또한 생성자가 존재하지만 default 생성자는  `private`로 되어 있으며 `public`으로 변경하면 컴파일 에러가 발생함
    - 다른 클래스에서 Enum을 사용할 시 Season.SPRING와 같은 형태로 상수처럼 사용하면 됨 
- Enum 클래스에서 선언한 상수들은 클래스가 로드될 때 하나의 인스턴스로 생성되어 싱글톤 형태로 사용됨

## enum이 제공하는 메서드 (values()와 valueOf())
- Enum 클래스는 모두 [java.lang.Enum](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/lang/Enum.html)를 상속받은 클래스임
- java.lang.Enum은 다음과 같은  기본적인 메서드를 제공함
    - `values()` : 열거된 모든 원소를 배열에 담아 순서대로 반환함
        ```java
        for(Season s: Season.values()) {
            System.out.println(s);
        }
        ```
    - `valueOf()` : 매개변수로 주어진 값과 일치하는 원소를 찾아서 반환함 
        `assertThat(Season.valueOf("AUTUMN"), Season.AUTUMN);`
        - 열거체를 비교할 때는 값 뿐만 아니라 타입까지도 함께 체크함 

## [EnumSet](https://docs.oracle.com/javase/8/docs/api/java/util/EnumSet.html)
- EnumSet은 Enum을 사용하기 위한 특수한 Set 클래스
- 비트 플래그의 대안으로 사용할 수 있을 정도로 효율이 좋음 
- 예시
    ```java
    EnumSet<Season> all = EnumSet.allOf(Season.class);
    EnumSet<Season> none = EnumSet.noneof(Season.class);
    EnumSet<Season> ss = EnumSet.of(Season.SPRING, Season.SUMMER);
    ```

<br>
<hr>

### 참고 자료 
- [TCP school- Enum 클래스](http://www.tcpschool.com/java/java_api_enum)
- [자바 Enum 기본 및 활용](https://velog.io/@kyle/%EC%9E%90%EB%B0%94-Enum-%EA%B8%B0%EB%B3%B8-%EB%B0%8F-%ED%99%9C%EC%9A%A9)
- [63. enum 클래스의 사용과 이해](https://www.opentutorials.org/module/1226/8025)