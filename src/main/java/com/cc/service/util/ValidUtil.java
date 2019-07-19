package com.cc.service.util;

import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class ValidUtil {


    public static class Regexs{
        public static final String EMAIL = "\\w+(\\.\\w+)*@\\w+(\\.\\w+)+";
        public static final String PHONE = "(\\+\\d+)?(\\d{3,4}\\-?)?\\d{7,8}$";
        public static final String MOBILE = "(\\+\\d+)?1[34578]\\d{9}$";
        public static final String IP_ADDRESS = "[1-9](\\d{1,2})?\\.(0|([1-9](\\d{1,2})?))\\.(0|([1-9](\\d{1,2})?))\\.(0|([1-9](\\d{1,2})?))";
        public static final String INTEGER = "^-?(([1-9]\\d*$)|0)";
        public static final String INTEGER_POSITIVE = "^[1-9]\\d*|0$";
        public static final String INTEGER_NEGATIV = "^-[1-9]\\d*|0$";
        public static final String AGE = "^(?:[1-9][0-9]?|1[0-4][0-9]|150)$";
        public static final String POST_CODE = "[0-9]\\d{5}(?!\\d)";
        public static final String STR_SPECIAL = com.my.tools.core.regex.Regexs.STR_SPECIAL.getRegex();
        public static final String CHINESE = com.my.tools.core.regex.Regexs.CHINESE.getRegex();
        public static final String IDCARD = com.my.tools.core.regex.Regexs.IDCARD.getRegex();
    }


    /**
     * 把实体对象的关系正确
     * 多对一，是否绑定了空对象（没有id的对象），有则去除（set null）
     * 一对多，去除集合中都是空对象（所有属性都是null的对象），有则去除（set null），集合对象本身会保留
     */
    public static void deleteEmptyRelation(Object entity) {
        // 遍历属性
        Class<?> clazz = entity.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try{
                Object obj = field.get(entity);
                if(obj == null)
                    continue;
                if(field.getAnnotation(ManyToOne.class) != null){
                    // 拿出这个属性对象的id，如果是null，移除这个属性
                    // 拿出id
                    Field idField = Arrays.asList(obj.getClass().getDeclaredFields()).stream().filter(e -> e.getAnnotation(Id.class) != null).findFirst().get();
                    idField.setAccessible(true);
                    if(idField.get(obj) == null){
                        field.set(entity, null);
                    }
                } else if(field.getAnnotation(OneToMany.class) != null){
                    Set set = (Set) obj;

                    // 遍历验证所有对象的模型关系
                    set.forEach(ValidUtil::deleteEmptyRelation);

                    // 只留下非空对象(只要有一个属性不为null)
                    Set set2 = (Set) set.stream().filter(e -> {
                        Field[] eFields = e.getClass().getDeclaredFields();
                        for (Field f : eFields) {
                            if("serialVersionUID".equals(f.getName()))
                                continue;
                            f.setAccessible(true);
                            try {
                                Object value = f.get(e);
                                if(value != null && !"null".equals(value)){
                                    return true;
                                }
                            } catch (IllegalAccessException ex) {
                                ex.printStackTrace();
                            }
                        }
                        return false;
                    }).collect(Collectors.toSet());

                    // 把新集合设置到Entity的属性上
                    //field.set(set, entity);
                    set.clear();
                    set.addAll(set2);
                }
            } catch (IllegalAccessException e){
                e.printStackTrace();
            } catch (ClassCastException e){
                e.printStackTrace();
            }

        }

    }

}
