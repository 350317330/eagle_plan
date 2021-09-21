package com.example.postgis;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.io.WKTReader;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.gavaghan.geodesy.Ellipsoid;
import org.gavaghan.geodesy.GeodeticCalculator;
import org.gavaghan.geodesy.GeodeticCurve;
import org.gavaghan.geodesy.GlobalCoordinates;
import org.geotools.data.DataUtilities;
import org.geotools.data.collection.ListFeatureCollection;
import org.geotools.data.simple.SimpleFeatureCollection;
import org.geotools.feature.simple.SimpleFeatureBuilder;
import org.geotools.geojson.feature.FeatureJSON;
import org.geotools.geometry.jts.JTSFactoryFinder;
import org.junit.jupiter.api.Test;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureType;
import org.opengis.geometry.coordinate.LineString;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.DigestUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@SpringBootTest
class PostgisApplicationTests {


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
        FeatureJSON fjson = new FeatureJSON();
        LineString lineString = (LineString)reader.read("LINESTRING (254058.76074485347 475001.2186020431, 255351.04293761664 474966.9279243938)");
// 按照TYPE中声明的顺序为属性赋值就可以
        featureBuilder.add(lineString);
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
        String s = DigestUtils.md5DigestAsHex(new FileInputStream("C:\\Users\\shisi\\Desktop\\理赔照片\\rice\\1\\test1.PNG"));
        System.out.println(s);
        String s1 = DigestUtils.md5DigestAsHex(new FileInputStream("C:\\Users\\shisi\\Desktop\\理赔照片\\rice\\1\\test1.PNG"));
        System.out.println(s1);

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
