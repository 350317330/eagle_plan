package com.example.postgis;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.dream.PostgisApplication;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.gavaghan.geodesy.Ellipsoid;
import org.gavaghan.geodesy.GeodeticCalculator;
import org.gavaghan.geodesy.GeodeticCurve;
import org.gavaghan.geodesy.GlobalCoordinates;
import org.geotools.data.DataUtilities;
import org.geotools.feature.FeatureCollection;
import org.geotools.feature.FeatureIterator;
import org.geotools.feature.simple.SimpleFeatureBuilder;
import org.geotools.feature.simple.SimpleFeatureImpl;
import org.geotools.feature.simple.SimpleFeatureTypeImpl;
import org.geotools.geojson.GeoJSONUtil;
import org.geotools.geojson.feature.FeatureJSON;
import org.geotools.geojson.geom.GeometryJSON;
import org.geotools.geometry.jts.JTSFactoryFinder;
import org.junit.jupiter.api.Test;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.io.WKTReader;
import org.opengis.feature.Feature;
import org.opengis.feature.Property;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureType;
import org.opengis.feature.type.FeatureType;
import org.opengis.geometry.coordinate.LineString;
import org.opengis.geometry.coordinate.Polygon;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.FileCopyUtils;

import java.io.*;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@SpringBootTest(classes = PostgisApplication.class)
public class PostgisApplicationTests {


    @Test
    public void test7()throws Exception{



        InputStream in = new FileInputStream("D:\\Java\\project\\eagle_plan\\postgis\\src\\main\\resources\\gis\\data\\paddy.json");


        FeatureJSON featureJSON = new FeatureJSON( new GeometryJSON(20));

       // FeatureCollection<SimpleFeatureType, SimpleFeatureImpl> featureCollection = featureJSON.readFeatureCollection(in);
        //featureJSON.writeFeatureCollection(featureCollection,System.out);  // 控制台输出和原始geojson一致
        FeatureCollection featureCollection = featureJSON.readFeatureCollection(in);

        String s = featureJSON.toString(featureCollection);

        System.out.println(s);

    }

    @Test
    public void test6()throws Exception{

        String s = FileCopyUtils.copyToString(new FileReader("D:\\Java\\project\\eagle_plan\\postgis\\src\\main\\resources\\gis\\data\\paddy.json"));

        ObjectMapper mapper=new ObjectMapper();
        mapper.enable(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS);
        Map<String,Object> map = mapper.readValue(s, Map.class);
        List<Map> list = (List<Map>) map.get("features");
        for(Map map1:list){
            Object geometry = map1.get("geometry");
            String s1 = mapper.writeValueAsString(geometry);
            GeometryJSON gjson = new GeometryJSON(20);
            Reader reader = new StringReader(s1);
            Geometry geometry1 = gjson.read(reader);
            String wkt = geometry1.toText();
            System.out.println(wkt);
        }
        System.out.println(map);
    }

    @Test
    public void test1()throws Exception{
        String wktPoint = "POINT(11.11111 12.22222)";
        WKTReader reader = new WKTReader();
        Geometry geometry = reader.read(wktPoint);
        StringWriter writer = new StringWriter();
        GeometryJSON g = new GeometryJSON();
        g.write(geometry, writer);
        String result = writer.toString();
        System.out.println("GeoJson结果=" + result);

    }

    @Test
    public void test3()throws Exception{
        String geoJson = "{\"type\":\"Point\",\"coordinates\":[11.1111,12.2222]}";
        GeometryJSON gjson = new GeometryJSON();
        Reader reader = new StringReader(geoJson);
        Geometry geometry = gjson.read(reader);
        String wkt = geometry.toText();
        System.out.println("wkt结果=" + wkt);

    }

    @Test
    public void test()throws Exception{

        final SimpleFeatureType TYPE = DataUtilities.createType("Link",
                "geometry:LineString," + // <- the geometry attribute: Point type
                        "gid:String," +   // <- a String attribute
                        "direction:Integer," +   // a number attribute
                        "orientation:Integer"
        );
        SimpleFeatureBuilder featureBuilder = new SimpleFeatureBuilder(TYPE);
        GeometryFactory geometryFactory = JTSFactoryFinder.getGeometryFactory();
        WKTReader reader = new WKTReader( geometryFactory );
        FeatureJSON fjson = new FeatureJSON(new GeometryJSON(20));
        Geometry geometry = reader.read("LINESTRING (254058.76074485347 475001.2186020431, 255351.04293761664 474966.9279243938)");

        // 按照TYPE中声明的顺序为属性赋值就可以
        featureBuilder.add(geometry);
        featureBuilder.add("123456");
        featureBuilder.add(2);
        featureBuilder.add(0);
        SimpleFeature feature = featureBuilder.buildFeature(null);
        StringWriter writer = new StringWriter();
        fjson.writeFeature(feature, writer);
        System.out.println(writer.toString());
    }


    @Test
    public void test2()throws Exception{
        //Thumbnails.of("C:\\Users\\shisi\\Desktop\\理赔照片\\rice\\1\\捕获.PNG").scale(1f).outputQuality(0.5f).toFile("C:\\Users\\shisi\\Desktop\\理赔照片\\rice\\1\\test1.PNG");
        //String s = DigestUtils.md5DigestAsHex(new FileInputStream("C:\\Users\\shisi\\Desktop\\理赔照片\\rice\\1\\test1.PNG"));
        //System.out.println(s);
        //String s1 = DigestUtils.md5DigestAsHex(new FileInputStream("C:\\Users\\shisi\\Desktop\\理赔照片\\rice\\1\\test1.PNG"));
        //System.out.println(s1);

        GlobalCoordinates source = new GlobalCoordinates(29.490295, 106.486654);
        GlobalCoordinates target = new GlobalCoordinates(29.615467, 106.581515);

        double meter1 = getDistanceMeter(source, target, Ellipsoid.Sphere);
        double meter2 = getDistanceMeter(source, target, Ellipsoid.WGS84);

        System.out.println("Sphere坐标系计算结果："+meter1 + "米");
        System.out.println("WGS84坐标系计算结果："+meter2 + "米");

    }

    public static double getDistanceMeter(GlobalCoordinates gpsFrom, GlobalCoordinates gpsTo, Ellipsoid ellipsoid){
        //创建GeodeticCalculator，调用计算方法，传入坐标系、经纬度用于计算距离
        GeodeticCurve geoCurve = new GeodeticCalculator().calculateGeodeticCurve(ellipsoid, gpsFrom, gpsTo);
        return geoCurve.getEllipsoidalDistance();
    }



    @Test
    void contextLoads() throws Exception{
        ImportParams params = new ImportParams();
        params.setHeadRows(1);
        List<Insurance> list = ExcelImportUtil.importExcel(new File("C:\\Users\\shisi\\Desktop\\水稻（1-9）(1).xlsx"), Insurance.class, params);
        System.out.println(list);
        AtomicInteger integer=new AtomicInteger();

        for(Insurance insurance:list){
            insurance.setInsuranceType("0");
            String[] replace = insurance.getLngLat().split(",");
            insurance.setLongitude(Double.valueOf(replace[0]));
            insurance.setLatitude(Double.valueOf(replace[1]));
            insurance.setUrls(new String[]{"/img/rice/"+insurance.getId()+"/捕获.PNG","/img/rice/"+insurance.getId()+"/捕获2.PNG"});
            insurance.setId(integer.incrementAndGet());
        }

        List<Insurance> list1 = ExcelImportUtil.importExcel(new File("C:\\Users\\shisi\\Desktop\\小麦大麦（1-40）.xlsx"), Insurance.class, params);
        for(Insurance insurance:list1){
            insurance.setInsuranceType("1");
            String[] replace = insurance.getLngLat().split(",");
            insurance.setLongitude(Double.valueOf(replace[0]));
            insurance.setLatitude(Double.valueOf(replace[1]));
            insurance.setUrls(new String[]{"/img/wheat/"+insurance.getId()+"/捕获.PNG","/img/wheat/"+insurance.getId()+"/捕获2.PNG"});
            insurance.setId(integer.incrementAndGet());
        }

        list.addAll(list1);

        JsonMapper json=new JsonMapper();

        String s = json.writeValueAsString(list);
        System.out.println(s);


    }


    @Getter
    @Setter
    @ToString
    public static class Insurance {

        @Excel(name = "序号")
        private Integer id;
        @Excel(name = "险种名称")
        private String insuranceName;
        @Excel(name = "险种类型")
        private String insuranceType;
        @Excel(name = "参保面积")
        private BigDecimal insured_area;
        @Excel(name = "出险面积")
        private BigDecimal riskArea;
        @Excel(name = "地址")
        private String address;
        @JsonIgnore
        @Excel(name = "经纬度")
        private String lngLat;
        @Excel(name = "经度")
        private Double longitude;
        @Excel(name = "纬度")
        private Double latitude;
        @Excel(name = "理赔时间", format = "yyyy-MM-dd")
        @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
        private Date claimTime;
        @Excel(name = "巨灾标志")
        private String catastrophe;
        @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
        @Excel(name = "出险日期", format = "yyyy-MM-dd")
        private Date accidentTime;
        @Excel(name = "赔款金额")
        private BigDecimal claimAmount;
        @Excel(name = "损失标的")
        private String loss;
        @Excel(name = "出险原因")
        private String accidentReason;
        @Excel(name = "被保险人")
        private String insured;
        @Excel(name = "证件号码")
        private String idNo;
        @Excel(name = "联系电话")
        private String phone;

        private String[] urls;





    }

}
