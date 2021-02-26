# 14주차. [제네릭](https://github.com/whiteship/live-study/issues/14)
- 날짜 : 21.02.27
- 목표 : 자바의 제네릭에 대해 학습

<hr>

## 제네릭스 (Generics)란?
- 객체의 타입을 미리 명시해줌으로써 형변환을 하지 않아도 되게 하는 것
- 참조형 타입을 의미하는 기호로 `T`를 사용함. 어떠한 참조형 타입도 가능하다는 뜻임 
    - `T`외에도 element를 위미하는 `E`나 key를 의미하는 `K`, value를 의미하는 `V`도 사용됨
    - 모두 기호만 다르게 사용했을 뿐, **임의의 참조형 타입**을 뜻함
- 제네릭스의 장점
    - 타입 안정성을 제공함
    - 타입 체크와 형변환을 생략할 수 있어 코드가 간결해짐

### 바운디드 타입
- 제네릭 클래스의 객체를 메소드의 매개변수로 받을 때 객체의 타입 변수를 제한하는 것
- 어떤 타입과 그 타입의 서브 클래스만을 허용하도록 메소드를 작성할 수 있음 
- 예시 : `public void test(ArrayList<T extends Number> list)`
    - Number의 서브 클래스만 타입으로 가질 수 있음을 나타냄 

### 와일드 카드
- 와일드 카드는 물음표 `?`를 사용하여 알 수 없는 타입을 의미할 때 사용함 
- 와일드 카드를 활용한 바운디드 타입 종류
    - **<?>** : 제한 없음
    - **<? extends T>** : 상한 제한 (upper bound), T와 그를 상속받은 하위 객체만 매개변수로 가능 
    - **<? super T>** : 하한 제한 (lower bound), T와 그 상위 객체만 매개변수로 가능 
- 예시 : `public void test(ArrayList<? extends Number> list)`
    - Number 클래스와 해당 클래스를 상속받은 클래스들을 객체로 제한함

## 제네릭 메소드 만들기
- 제네릭 메소드들은 하나의 메소드 선언으로 만들어지며 다른 타입들을 인자로 받아 호출할 수 있음 
- 예시
    ```java
    class People<T,M>{
        
        private T name;
        private M age;
        
        People(T name, M age){
            this.name = name;
            this.age = age;
        }

        public T getName() {
            return name;
        }
        public void setName(T name) {
            this.name = name;
        }

        public M getAge() {
            return age;
        }
        public void setAge(M age) {
            this.age = age;
        }
        
        //Generic Mothod
        public static<T,V> boolean compare(People<T,V>p1, People<T,V>p2) {
            boolean nameCompare = p1.getName().equals(p2.getName());
            boolean ageCompare = p1.getAge().equals(p2.getAge());
            return nameCompare && ageCompare;
        }
    }
    ```
    - 메소드 파라미터에 `<T>`를 선언했다면 반환형에 `<T>`를 선언하여 컴파일러에게 제네릭 메서드라는 것을 알려줘야 함


## Type Erasure
- 컴파일러는 컴파일 시점에 제네릭에 대해 타입 이레이저라고 부르는 프로세스를 적용함
- 모든 타입 파라미터를 제거하고 나서 그 자리를 제한하고 있는 타입으로 변경하거나 타입 파라미터의 제한 타입이 지정되지 않았을 경우 Object로 대체함
    - 컴파일 후 바이트 코드에서 새로운 타입이 생기지 않도록 보장하는 일반 클래스들과 인터페이스, 메소드들만 포함함 
- 예시 
    ```java
    public List<T> genericMethod(List<T> list) {   
        // -> 컴파일 후 public List<Object> genericMethod(List<Object> list)로 변경됨
        return list.stream().collect(Collectors.toList()); 
    }
    ```

<br>
<hr>

### 참고 자료 
- [자바의 정석](http://www.yes24.com/Product/Goods/24259565)
- [제네릭스(Generics) - 3. 와일드 카드 <?>](https://siyoon210.tistory.com/16)
- [Java 제네릭 기본](https://sthwin.tistory.com/22)