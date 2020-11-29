package other;

public class Number {

    public static void main(String[] args) {
        //new Number().test1();
        new Number().test2();
    }

    public void test1(){
        long a = 2147483647*2;
        System.out.println(a);
        //a = -2
        /**
         * 因为在编译阶段，程序就已经将静态变量加载好了，2147483647默认为int，int*2 = -2L。
         */
        int x = 2147483647;
        a = (long)x * 2;
        System.out.println(a);
        //这样才会输出正确的结果
    }

    public void test2(){
        int a = 2147483647;
        System.out.println(++a);
        //-2147483648
    }

}
