package javalanguage.streamlearning;

import util.Person;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * 开始学习stream
 * stream：将要处理的元素看作一种流。在流的过程中，借助Stream API对流中的元素进行操作，
 * 比如：筛选、排序、聚合。可进行中间操作，每次返回一个新的流，也可以进行终端操作，产生一个新的集合或值，淡无法再使用。
 */
public class Stream1 {

    public static void main(String[] args) {
        Stream1 stream1 = new Stream1();
        stream1.init();
        stream1.streamUse();
    }

    //需要使用的员工类
    List<Person> personList = new ArrayList<Person>();

    public void init() {
        personList.add(new Person("Tom", 8900, "male", "New York"));
        personList.add(new Person("Jack", 7000, "male", "Washington"));
        personList.add(new Person("Lily", 7800, "female", "Washington"));
        personList.add(new Person("Anni", 8200, "female", "New York"));
        personList.add(new Person("Owen", 9500, "male", "New York"));
        personList.add(new Person("Alisa", 7900, "female", "New York"));
    }

    /**
     * Stream的创建
     */
    public void streamCreation() {
        //java.util.Collection.stream()========================
        List<String> list = Arrays.asList("a", "b", "c");
        //创建一个顺序流
        Stream<String> stream1 = list.stream();
        //创建一个并行流
        Stream<String> parallelStream = list.parallelStream();

        //java.util.Arrays.stream(T[] array)===================
        int[] array = {1, 2, 3, 4, 5};
        IntStream stream2 = Arrays.stream(array);

        //静态方法：
        Stream<Integer> stream3 = Stream.of(1, 2, 3, 4, 5);

        Stream<Integer> stream4 = Stream.iterate(0, (x) -> x + 3).limit(4);
        stream4.forEach(System.out::println);
        //out: 0    3   6   9

        Stream<Double> stream5 = Stream.generate(Math::random).limit(3);
        stream3.forEach(System.out::println);
        //out:  0.6796156909271994
        //      0.1914314208854283
        //      0.8116932592396652

        //顺序流和并行流的转换：
        List<Integer> list1 = Arrays.asList(1, 2, 3);
        Optional<Integer> findFirst = list1.stream().parallel().filter(x -> x > 6).findFirst();
    }

    /**
     * Stream的使用
     * Optional：一个可以为null的容器对象。如果值存在，则isPresent()=true，get()可以返回该对象
     */
    public void streamUse() {
        List<Integer> list = Arrays.asList(7, 6, 9, 3, 8, 2, 1);
        // 遍历输出符合条件的元素
        list.stream().filter(x -> x > 6).forEach(System.out::println);
        // 匹配第一个
        Optional<Integer> findFirst = list.stream().filter(x -> x > 6).findFirst();
        // 匹配任意（适用于并行流）
        Optional<Integer> findAny = list.parallelStream().filter(x -> x > 6).findAny();
        // 是否包含符合特定条件的元素
        boolean anyMatch = list.stream().anyMatch(x -> x < 6);
        System.out.println(findFirst + " " + (findFirst.isPresent() ? findFirst.get() : 0));//关于get()，要进行isPresent()的判断，不然会抛异常
        System.out.println(findAny + " " + (findAny.orElse(0)));    //上面可以直接简化成 .orElse(int)
        System.out.println(anyMatch);
    }

}
