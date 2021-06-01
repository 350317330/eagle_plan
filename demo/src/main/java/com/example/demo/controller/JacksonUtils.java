package com.example.demo.controller;

import com.example.demo.PersonModel;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.googlecode.protobuf.format.JsonFormat;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Map;

/**
 * json系列化工具类
 *
 * @author shisi
 * @date 2020/12/26 10:50
 **/
public class JacksonUtils {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    static {
        // 取消默认转换timestamps形式,false使用日期格式转换，true不使用日期转换，结果是时间的数值157113793535
        OBJECT_MAPPER.configure(JsonGenerator.Feature.WRITE_BIGDECIMAL_AS_PLAIN, false);
        // 忽略空Bean转json的错误
        OBJECT_MAPPER.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        // 所有的日期格式统一样式： yyyy-MM-dd HH:mm:ss
        OBJECT_MAPPER.setDateFormat(new SimpleDateFormat(" yyyy-MM-dd HH:mm:ss"));
        // 忽略 在json字符串中存在，但是在对象中不存在对应属性的情况，防止错误。
        // 例如json数据中多出字段，而对象中没有此字段。如果设置true，抛出异常，因为字段不对应；false则忽略多出的字段，默认值为null，将其他字段反序列化成功
        OBJECT_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    }

    /**
     * 把JAVA对象转换成JSON字符串
     *
     * @param obj JAVA对象
     * @return java.lang.String
     * @author shisi
     * @date 2020/12/26 13:18
     */
    public static <T> String toJsonString(T obj) {
        try {
            return OBJECT_MAPPER.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 把JSON字符串转换成JAVA对象
     *
     * @param str   JSON字符串
     * @param clazz JAVA字节码
     * @return T
     * @author shisi
     * @date 2020/12/26 13:19
     */
    public static <T> T parse(String str, Class<T> clazz) {
        try {
            return OBJECT_MAPPER.readValue(str, clazz);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 把JSON字符串转换成MAP结构
     *
     * @param str
     * @return java.util.Map
     * @author shisi
     * @date 2020/12/26 13:20
     */
    @SuppressWarnings("all")
    public static Map<String, Object> parseToMap(String str) {
        try {
            return OBJECT_MAPPER.readValue(str, Map.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws Exception {
        PersonModel.Person.Builder builder = PersonModel.Person.newBuilder();
        builder.setId(1);
        builder.setName("shisi");
        builder.setEmail("shisixiangwq@qq.com");
        PersonModel.Person person = builder.build();
        System.out.println(person);

        System.out.println("===Person Byte:");
        for (byte b : person.toByteArray()) {
            System.out.print(b);
        }
        System.out.println("================");

        byte[] byteArray = person.toByteArray();
        PersonModel.Person p2 = PersonModel.Person.parseFrom(byteArray);
        System.out.println("after id:" + p2.getId());
        System.out.println("after name:" + p2.getName());
        System.out.println("after email:" + p2.getEmail());

        String json = JsonFormat.printToString(person);
        System.out.println(json);
        PersonModel.Person.Builder builder1 = PersonModel.Person.newBuilder();
        JsonFormat.merge(json, builder1);
        System.out.println(builder1);
    }

}
