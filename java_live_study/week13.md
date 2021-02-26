# 13주차. [I/O](https://github.com/whiteship/live-study/issues/13)
- 날짜 : 21.02.19
- 목표 : 자바의 Input과 Ontput에 대해 학습하세요.

<hr>

## 스트림 (Stream)이란? 
- 스트림이란?
    - 어느 한 쪽에서 다른 쪽으로 데이터를 전달하려면 두 대상을 연결하고 데이터를 전송할 수 있는 무언가가 필요한데 이를 스트림(stream)이라함 
    - 데이터를 운반하는데 사용되는 연결 통로
    - 스트림은 연속적인 데이터의 흐름을 물에 비유해서 붙여진 이름으로 여러 가지 면에서 유사한 점을 갖음
        - 물이 한 쪽 방향으로만 흐르는 것과 같이 스트림은 단반향통신만 가능함 
        - 하나의 스트림으로 입력과 출력을 동시에 처리할 수 없음
        - 만약 입력과 출력을 동시에 수행하려면 입력을 위한 입력스트림, 출력을 위한 출력스트림으로 2개의 스트림이 필요함
    - 스트림은 먼저 보낸 데이터를 먼저 받게 되어 있으며 중간에 건너뜀 없이 연속적으로 데이터를 주고받음

### InputStream과 OutputStream
- 바이트 단위로 데이터를 전송하며 입출력 대상에 따라 다음과 같은 입출력스트림이 있음
    - 어떠한 대상을 입출력하는지에 따라 해당 스트림을 선택해서 사용해야 함 

        | 입력 스트림 | 출력 스트림 | 입출력 대상의 종류 | 
        | -------- | --------- | ------------- |
        | FileInputStream | FileOutputStream | 파일 | 
        | ByteArrayInputStream | ByteArrayOutputStream | 메모리(byte배열) | 
        | PipedInputStream | PipedOutputStream | 프로세스(프로세스간의 통신) | 
        | AudioInputStream | AudioOutputStream | 오디오 장치 | 

- 자바에서는 java.io 패키지를 통해서 많은 종류의 입출력 관련 클래스들을 제공하고 있고, 입출력을 처리할 수 있는 표준화된 방법을 제공하여 입출력의 대상이 달라져도 동일한 방법으로 적용이 가능함

    | InputStream | OutputStream | 
    | ----------- | ------------ | 
    | abstract int read() | abstract void write(int b) | 
    | int read(byte[] b) | void write(byte[] b) | 
    | int read(byte[] b, int off, int len) | void write(byte[] b, int off, int len) | 

    - InputStream의 `read()`와 OutputStream의 `write()`는 추상메서드임
    - 그 외에 두 `read`, `write`메서드는 추상메서드를 이용해서 구현한 것이므로 역시 `read()`, `write(int b)`의 구현체가 먼저 필요함 

## 보조스트림이란?
- 보조 스트림이란 실제 데이터를 주고 받는 스트림은 아니고, 스트림의 기능을 향상시키거나 새로운 기능을 추가할 수 있음 
- 예시 
    ```java
    FileInputStream fis = new FileInputStream("test.txt");
    BufferedInputStream bis = new BufferedInputStream(fis);
    bis.read()
    ```
    - 보조스트림인 `BufferedInputStream`으로부터 데이터를 읽음
- 보조스트림의 종류 
    
    | 입력 | 출력 | 설명 | 
    | ------ | ------ | ---- | 
    | FilerInputStream | OutputStream | 필터를 이용한 입출력 |
    | BufferedInputStream | BufferedInputStream | 버퍼를 이용한 입출력 |
    | DataInputStream | DataInputStream | primitive 타입으로 데이터를 처리함 |
    | SequenceInputStream | SequenceInputStream | 두 개의 스트림을 하나로 연결 |
    | ObjectInputStream | ObjectInputStream | 데이터를 객체단위로 읽어옴 |

## 버퍼란? 
- 입출력에 사용되는 버퍼란 보통 CPU와 보조기억장치 사이에서 사용되는 **임시 저장 공간**을 의미함
    - CPU는 1초에 100개의 데이터를 처리<br>
    보조 기억 장치는 1초에 3개의 데이터를 처리<br>
    > 이처럼 속도 차가 큰 두 대상이 입출력을 수행할 때 효율성을 위해 사용하는 임시 저장 공간

### BufferedInputInputStream과 BufferedOutputStream
- 스트림의 입출력의 효율을 높이기 위해 버퍼를 사용하는 보조 스트림
- 한 바이트씩 입출력하는 것 보다 버퍼를 이용해서 한 번에 여러 바이트를 입출력하는 것이 빠르기 때문에 대부분의 입출력 작업에 사용됨
    - BufferedInputStream의 `read()` 메서드를 호출하면 입력 소스로부터 버퍼 크기 만큼씩 데이터를 읽어다가 자신의 내부 버퍼에 저장함
        - 프로그램은 외부의 입력 소스가 아닌, 내부 버퍼로부터 데이터를 읽어오는 것이므로 효율이 더 높아짐
        - 버퍼에 저장된 데이터를 모두 다 읽으면 그 다음 데이터를 읽기 위해 read 메서드가 호출되면서 반복됨
    - BufferedOutputStream 역시 마찬가지로 출력이 버퍼에 저장되고 버퍼가 가득 차면, 버퍼의 내용을 출력 소스에 출력함 
        - 마지막에 출력 소스에 쓰여지지 못하고 남겨진 데이터까지 모두 쓰이도록 마지막에 `close()`나 `flush()`를 호출해야 함
- 예시 
    ```java
    class BufferedOutputStream {
        public static void main(String args[]) {
            try {
                FileOutputStream fos = new FileOutputStream("test.txt");
                BufferedOutputStream bos = new BufferedOutputStream(fos, 5);
                for(int i=1; i<=9; i++) {
                    bos.write(i);
                }

                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    ```
    - 결과 예시 : `12345`
    - 버퍼의 크기를 5로 제한했기 떄문에 9까지 읽지 않고 5에서 끊김. `bos.close()`를 해주어야 끝까지 출력이 됨
        
## 바이트 기반 스트림과 문자 기반 스트림
- 위에 까지의 스트림들은 모두 바이트 기반의 스트림이고, 문자데이터를 입출력할 때는 문자 기반 스트림을 사용해야 함
    - InputStream -> **Reader**, OutputStream -> **Writer**
    - 비교

    | InputStream | Reader |
    | ------- | ------- | 
    | **abstract** int read()<br>int read(**byte[] b**)<br>int read(**byte[] b**, int off, int len) | int read()<br>int read(**char[]** cbuf)<br> **abstract** int read(**char[] cbuf**, inf off, int len)| 
    
    | OutputStream | Writer | 
    | ------- | ------- |
    | **abstract** void write(int b)<br>void read(**byte[] b**)<br>void read(**byte[] b**, int off, int len) | void write()<br>void write(**char[]** cbuf)<br>**abstract** int read(**char[] cbuf**, inf off, int len)<br>void write(String str)<br>void write(Strin str, int off, int len)| 

    - byte배열 대신 char배열을 사용한다는 것과 추상메서드가 달라짐을 주목할 것 

## 표준 스트림 (System.in, System.out, System.err)
- 표준입출력은 console을 통한 데이터 입력과 데이터 출력을 의미함
- 자바에서는 표준 입출력을 위한 3가지 입출력 스트림을 제공함
    - System.in : console로부터 데이터를 입력받음
    - System.out : console로 데이터를 출력함
    - System.err : console로 데이터를 출력함 
        ```java
        public final class System {
            public final static InputStream in = nullInputStream();
            public final static PrintStream out = nullInputStream();
            public final static PrintStream err = nullInputStream();
        }
        ```

<br>
<hr>

### 참고 자료 
- [자바의 정석](http://www.yes24.com/Product/Goods/24259565)
- [[개념정리] 버퍼(BUFFER)란? 버퍼 개념](https://dololak.tistory.com/84)