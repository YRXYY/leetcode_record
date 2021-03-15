package javalanguage.reflect;

import org.springframework.beans.BeanUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Objects;

//精确定位服务
public class PreciseLocateService {

    //定位目标是否为预期目标
    public <T> boolean locateTarget(Class<T> tClass, T obj, String propertyName, Object propertyVal) {
        Object extractPropertyVal = extractPropertyVal(tClass, obj, propertyName);
        return Objects.equals(extractPropertyVal, propertyVal);
    }

    //提取指定对象的指定属性
    public <T> Object extractPropertyVal(Class<T> tClass, T obj, String propertyName) {
        //本该是 StringUtil.isBlank(propertyName)
        if(Objects.isNull(tClass) || Objects.isNull(obj) || propertyName == null) {
            return null;
        }
        // class如果是原始类型，直接返回false
        if (tClass.isPrimitive()) {
            return false;
        }
        //如果属性是map类型，采用get方法
        //原来语句是： Map.class.isAssignableForm(tClass),不知道为啥是错的，换成Object.class.isA  or  String.class.isA  就是对的
        if (tClass.isInstance(Map.class)) {
            return ((Map) obj).get(propertyName);
        }
        PropertyDescriptor propertyDescriptor = BeanUtils.getPropertyDescriptor(tClass, propertyName);
        Method method = propertyDescriptor.getReadMethod();

        if (Objects.isNull(method)) {
            return null;
        }

        try{
            return method.invoke(obj);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        return null;
    }

}
