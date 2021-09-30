package com.example.demo;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.entity.Properties;
import com.example.demo.service.PropertiesService;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileInputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JsonTest {

    @Autowired
    private PropertiesService propertiesService;


    @Test
    public void test2()throws Exception{
        FileInputStream in = new FileInputStream("C:\\Users\\admin\\Desktop\\GIS_JSON数据\\补贴田块.json");
        FileInputStream in1 = new FileInputStream("C:\\Users\\admin\\Desktop\\GIS_JSON数据\\wheat.json");
        String plot = IOUtils.toString(in, Charset.defaultCharset());
        String wheat = IOUtils.toString(in1, Charset.defaultCharset());


        JSONObject plotJson = JSONObject.parseObject(plot);
        JSONArray plotArray = (JSONArray) plotJson.get("features");


        JSONObject wheatJson = JSONObject.parseObject(wheat);
        JSONArray wheatArray = (JSONArray) wheatJson.get("features");

        plotArray.forEach(e->{
            JSONObject obj= (JSONObject) e;
            JSONObject properties = (JSONObject) obj.get("properties");
            System.out.println(properties);
            return ;
        });

        wheatArray.forEach(e->{
            JSONObject obj= (JSONObject) e;
            JSONObject properties = (JSONObject) obj.get("properties");
            System.out.println(properties);
            return ;
        });



    }


    @Test
    public void test()throws Exception{
        FileInputStream in = new FileInputStream("D:\\project\\brain\\src\\static\\gis\\data\\butie2_points.json");
        String result = IOUtils.toString(in, Charset.defaultCharset());
        JSONObject jsonObject = JSONObject.parseObject(result);
        JSONArray jsonArray = (JSONArray) jsonObject.get("features");
        List<Properties> list=new ArrayList<>();
        jsonArray.forEach(e->{
            JSONObject obj= (JSONObject) e;
            JSONObject geometry = (JSONObject) obj.get("geometry");
            JSONObject properties = (JSONObject) obj.get("properties");
            JSONArray coordinates= (JSONArray) geometry.get("coordinates");
            properties.put("coordinates",coordinates);

            Properties entity = new Properties(
                    properties.getString("OBJECTID"),
                    properties.getLong("plot_num"),
                    properties.getString("plot_city_"),
                    properties.getString("plot_villa"),
                    properties.getString("plot_town_"),
                    properties.getString("memo"),
                    properties.getString("plot_contr"),
                    properties.getString("plot_con_1"),
                    properties.getString("plot_con_2"),
                    properties.getString("plot_user_"),
                    properties.getBigDecimal("plot_area"),
                    properties.getBigDecimal("plot_area_"),
                    properties.getInteger("plot_plant"),
                    properties.getString("plot_pla_1"),
                    properties.getString("plot_pla_2"),
                    properties.getString("coordinate"),
                    properties.getBigDecimal("SHAPE_Leng"),
                    properties.getBigDecimal("SHAPE_Area"),
                    properties.getString("是否可推"),
                    properties.getString("link"),
                    properties.getString("IFYN"),
                    properties.getString("plot_suer_"),
                    properties.getString("备注"),
                    properties.getString("检查"),
                    coordinates.toString()
            );

            list.add(entity);
        });


        List<List<Properties>> lists = averageAssign(list, 10);
        lists.forEach(e->{
            propertiesService.addBatch(e);
            System.out.println("==================================================");
        });



    }

    public static <T> List<List<T>> averageAssign(List<T> source, int n) {
        List<List<T>> result = new ArrayList<>();
        int remainder = source.size() % n;  //(先计算出余数)
        int number = source.size() / n;  //然后是商
        int offset = 0;//偏移量
        for (int i = 0; i < n; i++) {
            List<T> value;
            if (remainder > 0) {
                value = source.subList(i * number + offset, (i + 1) * number + offset + 1);
                remainder--;
                offset++;
            } else {
                value = source.subList(i * number + offset, (i + 1) * number + offset);
            }
            result.add(value);
        }
        return result;
    }



               /* "plot_city_" -> "嘉兴市南湖区"
                "plot_town_" -> "七星街道"
                "link" -> "1"
                "memo" -> ""
                "是否可推" -> "1"
                "plot_area_" -> "52.9208213617"
                "备注" -> null
                "plot_suer_" -> null
                "OBJECTID" -> "1599"
                "IFYN" -> "1"
                "plot_pla_2" -> "小麦"
                "plot_pla_1" -> "春季"
                "SHAPE_Leng" -> {  BigDecimal @3183} "0.0078883877805999997"
                "plot_contr" -> "陈玉珍"
                "plot_plant" -> "2021"
                "coordinate" -> ""
                "SUCCESS" -> null
                "检查" -> null
                "SHAPE_Area" -> "0.000003"
                "plot_con_2" -> ""
                "plot_user_" -> ""
                "plot_id" -> "陈玉珍-2021-春季-1599"
                "plot_con_1" -> ""
                "plot_villa" -> "博山村"
                "plot_num" -> "1599"
                "plot_area" -> "35280.5475745"*/

}
