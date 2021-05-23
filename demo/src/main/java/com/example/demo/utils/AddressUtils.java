package com.example.demo.utils;


import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.lionsoul.ip2region.DataBlock;
import org.lionsoul.ip2region.DbConfig;
import org.lionsoul.ip2region.DbSearcher;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.lang.reflect.Method;

/**
 * 获取地址类
 *
 * @author yiyue
 */
public class AddressUtils {
    private static final Logger log = LoggerFactory.getLogger(AddressUtils.class);

    public static final String IP_URL = "http://ip.taobao.com/service/getIpInfo.php";

    public static final String REGION = "内网IP|内网IP";

    public static void main(String[] args) throws Exception {

        System.out.println(getRealAddressByIP("119.123.77.78"));
    }

    public static String getRealAddressByIP(String ip) {
        // 内网不查询
        if (IpUtils.internalIp(ip)) {
            return "内网IP";
        }
        /*String rspStr = HttpUtils.sendGet(IP_URL, "ip=" + ip);
        if (StringUtils.isEmpty(rspStr))
        {
            log.error("获取地理位置异常 {}", ip);
            return address;
        }
        JSONObject obj = JSONObject.parseObject(rspStr);
        JSONObject data = obj.getObject("data", JSONObject.class);
        String region = data.getString("region");
        String city = data.getString("city");
        address = region + " " + city;*/
        String address = "XX|XX";
        try {
            DbConfig config = new DbConfig();
            DbSearcher searcher = new DbSearcher(config,  ResourceUtils.getFile("classpath:ip2region.db").getPath());
            Method method = searcher.getClass().getMethod("btreeSearch", String.class);
            DataBlock dataBlock;
            dataBlock = (DataBlock) method.invoke(searcher, ip);
            address = dataBlock.getRegion().replace("0|", "");
            if (address.charAt(address.length() - 1) == '|') {
                address = address.substring(0, address.length() - 1);
            }
            return address;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return address;
        }
    }
}
