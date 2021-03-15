package javalanguage.reflect;

import org.springframework.beans.BeanUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Objects;

//��ȷ��λ����
public class PreciseLocateService {

    //��λĿ���Ƿ�ΪԤ��Ŀ��
    public <T> boolean locateTarget(Class<T> tClass, T obj, String propertyName, Object propertyVal) {
        Object extractPropertyVal = extractPropertyVal(tClass, obj, propertyName);
        return Objects.equals(extractPropertyVal, propertyVal);
    }

    //��ȡָ�������ָ������
    public <T> Object extractPropertyVal(Class<T> tClass, T obj, String propertyName) {
        //������ StringUtil.isBlank(propertyName)
        if(Objects.isNull(tClass) || Objects.isNull(obj) || propertyName == null) {
            return null;
        }
        // class�����ԭʼ���ͣ�ֱ�ӷ���false
        if (tClass.isPrimitive()) {
            return false;
        }
        //���������map���ͣ�����get����
        //ԭ������ǣ� Map.class.isAssignableForm(tClass),��֪��Ϊɶ�Ǵ�ģ�����Object.class.isA  or  String.class.isA  ���ǶԵ�
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
