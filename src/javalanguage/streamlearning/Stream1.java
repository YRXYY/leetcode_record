package javalanguage.streamlearning;

import util.Person;

import javax.swing.text.html.Option;
import java.util.*;
import java.util.stream.Collectors;
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
     * foreach   find    match
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

    public void streamFilterUse() {
        List<Integer> list = Arrays.asList(6, 7, 3, 8, 1, 2, 9);
        Stream<Integer> stream = list.stream();
        stream.filter(x -> x > 7).forEach(System.out::println);

        List<String> filterList = personList.stream()
                .filter(x -> x.getSalary() > 8000)
                .map(Person::getName)
                .collect(Collectors.toList());
        System.out.println(filterList);
    }

    /**
     * stream的max、min、count
     */
    public void streamUse2() {
        //max
        List<String> list1 = Arrays.asList("adnm", "admmt", "pot", "xbangd", "weoujgsd");
        //获取String集合中最长的元素
        Optional<String> max = list1.stream().max(Comparator.comparing(String::length));
        System.out.println(max.get());

        List<Integer> list2 = Arrays.asList(7, 6, 8, 4, 1, 11, 6);
        //自然排序
        Optional<Integer> maxInteger = list2.stream().max(Integer::compareTo);
        //自定义排序
        Optional<Integer> maxInteger2 = list2.stream().max(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });
        System.out.println(maxInteger.get());
        System.out.println(maxInteger2.get());


        //获取员工工资最高的人
        Optional<Person> maxSalary = personList.stream().max(Comparator.comparingInt(Person::getSalary));
        System.out.println(maxSalary.get());

        //计算list2集合中大于6的元素的个数
        long count = list2.stream().filter(x -> x > 6).count();
        System.out.println(count);
    }

    /**
     * 映射： map  flatMap
     * 可以将一个流的元素按照一定的映射规则映射到另一个流中
     */
    public void StreamUse3() {
        //将英文字符串的元素全部改为大写
        String[] strArr = {"abcd", "bcdd", "defde", "fTr"};
        List<String> strList = Arrays.stream(strArr).map(String::toUpperCase).collect(Collectors.toList());

        //整数数组 每个元素+3
        List<Integer> intList = Arrays.asList(1, 3, 5, 7, 9, 11);
        List<Integer> intListNew = intList.stream().map(x -> x + 3).collect(Collectors.toList());

        System.out.println(strList);
        System.out.println(intList);

        //所有员工工资增加1000
        List<Person> personListNew = personList.stream().map(person -> {
            Person personNew = new Person(person.getName(), 0, "male", null);
            personNew.setSalary(person.getSalary() + 10000);
            return personNew;
        }).collect(Collectors.toList());
        System.out.println(personList.get(0).getSalary());
        System.out.println(personListNew.get(0).getSalary());


    }


    /**
     * 将两个字符串组合并成一个新的字符数组
     */
    public void streamUse4() {
        List<String> list = Arrays.asList("m,k,l,a", "1,3,5,7");
        List<String> listNew = list.stream().flatMap(s -> {
            //将每个元素转成一个stream
            String[] split = s.split(",");
            return Arrays.stream(split);
        }).collect(Collectors.toList());
        System.out.println(list);
        System.out.println(listNew);
    }


    /**
     * reduce规约
     * 尚有点迷，没看源码
     */
    public void streamUse5() {
        List<Integer> list = Arrays.asList(1, 3, 2, 4, 12, 3);
        //求和方式1
        Optional<Integer> sum = list.stream().reduce((x, y) -> x + y);
        //方式2
        Optional<Integer> sum2 = list.stream().reduce(Integer::sum);
        //方式3
        Integer sum3 = list.stream().reduce(0, Integer::sum);

        //求乘积
        Optional<Integer> product = list.stream().reduce((x, y) -> x * y);

        //get max  way 1
        Optional<Integer> max1 = list.stream().reduce((x, y) -> x > y ? x : y);
        //way 2
        Integer max2 = list.stream().reduce(1, Integer::max);


        //====================操作对象===================
        //get sum of salary
        Optional<Integer> sumSalary = personList.stream().map(Person::getSalary).reduce(Integer::sum);
        //sum way 2
        Integer sumSalary2 = personList.stream().reduce(0, (sum1, p) -> sum1 += p.getSalary(),
                (sum11, sum22) -> sum11 + sum22);
        //way 3
        Integer sumSalary3 = personList.stream().reduce(0, (sum111, p) -> sum111 += p.getSalary(), Integer::sum);

        Integer maxSalary1 = personList.stream().reduce(0, (max, p) -> max > p.getSalary() ? max : p.getSalary(),
                Integer::max);
        Integer maxSalary2 = personList.stream().reduce(0, (max, p) -> max > p.getSalary() ? max : p.getSalary(),
                (max1x,max2x)->max1x>max2x?max1x:max2x);

    }


//    public void


}









