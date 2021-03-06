package com.example.demo;

import lombok.Data;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AreaTest {

    @Data
    static class RegionEntry{
        private String code;
        private String name;
        private String parentCode;
        private List<RegionEntry> sub = new ArrayList<>();
    }

    public static String SITE_URL ="http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2020/";
    private static List<RegionEntry> regions = new ArrayList<RegionEntry>();

    public static void main(String[] args) throws Exception{
        System.out.println("抓取开始:" + new Date());
        getProvince();
//        StringBuffer content = new StringBuffer();
//        for (RegionEntry one : regions) {
//            content.append("insert into sys_province values(null,'").append(one.getCode()).append("', '").append(one.getName()).append("', 1 );\r\n");
//            for (RegionEntry two : one.getSub()) {
//                content.append("insert into sys_city values(null,'").append(one.getCode()).append("', '").append(two.getCode()+"','").append(two.getName()).append("', 2);\r\n");
//                for (RegionEntry three : two.getSub()) {
//                    content.append("insert into sys_county values(null,'").append(one.getCode()).append("', '").append(two.getCode()).append("', '").append(three.getCode()).append("', '").append(three.getName()).append("', 3 );\r\n");
//                    for(RegionEntry four:three.getSub()){
//                        content.append("insert into sys_town values(null,'").append(one.getCode()).append("', '").append(two.getCode()).append("', '").append(three.getCode()).append("', '").append(four.getCode()).append("','").append(four.getName()).append("', 4 );\r\n");
//                    }
//                }
//            }
//        }
//        FileOutputStream out = null;
////        Region.writeFile(content.toString());
//        try{
//            out = new FileOutputStream(new File("G:\\log\\city.txt"));
//            byte[] bytes = content.toString().getBytes();
//            out.write(bytes);
//            out.flush();
//        }catch(Exception e){
//            e.printStackTrace();
//        }finally{
//            if(out!=null)
//                try{
//                    out.close();
//                }catch (Exception e){
//                    e.printStackTrace();
//                }
//        }
        System.out.println("抓取完毕:" + new Date());

    }

    private static void getProvince() {
        Document doc;
        try {
            doc = Jsoup.connect(SITE_URL).get(); //Jsoup.connect(SITE_URL).get();
            Elements links = doc.select("tr.provincetr").select("a");
            RegionEntry region = null;
            for (Element e : links) {
                region = new RegionEntry();
                String href = e.attr("href");
                String[] arr = href.split("\\.");
                String code = arr[0];
                if (arr[0].length() < 6) {
                    for (int i = 0; i < 6 - arr[0].length(); i++) {
                        code += "0";
                    }
                }
                region.setCode(code);
                region.setName(e.text());
//                href的绝地路径
                String absHref = e.attr("abs:href");
                System.out.println(absHref);
                getCity(absHref, region);
                regions.add(region);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取市地址
     * @param url
     * @param region
     */
    private static void getCity(String url, RegionEntry region) {
        Document doc;
        try {
            doc = Jsoup.connect(url).get(); //Jsoup.connect(url).get().charset(charset);
//            <tr class='citytr'><td><a href='65/6501.html'>650100000000</a></td><td><a href='65/6501.html'>乌鲁木齐市</a></td></tr>
            Elements links = doc.select("tr.citytr");
            RegionEntry city;
            for (Element e : links) {
                city = new RegionEntry();
                Elements alist = e.select("a");
                Element codeE = alist.get(0);
                Element codeN = alist.get(1);
                String name = codeN.text();
                String code = codeE.text();
                if ("市辖区".equals(name)) {
                    name = region.getName();
                    //code = region.getCode();
                }
                city.setCode(code);
                city.setName(name);
                String absHref = codeE.attr("abs:href");
                getArea(absHref, city);
                region.getSub().add(city);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 获取区县地址
     * @param url
     * @param region
     */
    private static void getArea(String url, RegionEntry region) {
        Document doc;
        try {
            doc = Jsoup.connect(url).get(); // Jsoup.connect(url).get();
            //<tr class='countytr'><td><a href='01/130102.html'>130102000000</a></td><td><a href='01/130102.html'>长安区</a></td></tr>
            Elements links = doc.select("tr.countytr");
            RegionEntry area;
            for (Element e : links) {
                area = new RegionEntry();
                Elements alist = e.select("a");
                if (alist.size() > 0) {
                    Element codeE = alist.get(0);
                    String code = codeE.text();
                    area.setCode(code);
                    Element codeN = alist.get(1);
                    String name = codeN.text();
                    area.setName(name);
                    String absHref = codeE.attr("abs:href");
                    getTown(absHref, area);
                    region.getSub().add(area);
                } else {
                    alist = e.select("td");
                    area.setCode(alist.get(0).text());
                    area.setName(alist.get(1).text());
                    region.getSub().add(area);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //乡镇
    private static void getTown(String url, RegionEntry region) {
        Document doc;
        try {
            doc = Jsoup.connect(url).get(); // Jsoup.connect(url).get();
            //<tr class='towntr'><td><a href='07/110107001.html'>110107001000</a></td><td><a href='07/110107001.html'>八宝山街道办事处</a></td></tr>
            Elements links = doc.select("tr.towntr");
            RegionEntry town;
            for (Element e : links) {
                town = new RegionEntry();
                Elements alist = e.select("a");
                if (alist.size() > 0) {
                    Element codeE = alist.get(0);
                    String code = codeE.text();
                    town.setCode(code);
                    Element codeN = alist.get(1);
                    String name = codeN.text();
                    town.setName(name);
                    region.getSub().add(town);
                } else {
                    alist = e.select("td");
                    town.setCode(alist.get(0).text());
                    town.setName(alist.get(1).text());
                    region.getSub().add(town);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
