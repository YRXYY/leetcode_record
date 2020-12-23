package annotation;


/**
 * @Author xyy
 * 重要等级，1~3
 * 1 ：普通
 * 2： 简单，一个思考点
 * 3： 经典方法
 * 4： middle 多个思考点
 * 5： hard
 */
public @interface Level {

    int value()  default 3;

    String message()  default "";

}
