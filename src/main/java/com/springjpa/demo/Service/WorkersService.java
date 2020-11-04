package com.springjpa.demo.Service;

import com.springjpa.demo.repository.WorkersRepository;
import com.springjpa.demo.util.ApacheHttpUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class WorkersService {
    @Autowired
    private ApacheHttpUtil apacheHttpUtil;

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Value("${rubbish.url}")
    private String url;
    @Value("${rubbish.appid}")
    private String appid;
    @Value("${rubbish.secret}")
    private String secret;

    private String param;
    List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();


    public String getRubbishData(String checkData) {


        String l = String.valueOf(System.currentTimeMillis());
        if (StringUtils.isEmpty(checkData)) {
            System.out.println("本地表为空正在插入");
            param = "appId=" + appid + "&pageSize=5&time=" + l + "&key=" + secret;
            String sign = DigestUtils.md5Hex(param).toUpperCase();

            params.add(new BasicNameValuePair("appId", appid));
            params.add(new BasicNameValuePair("time", l));
            params.add(new BasicNameValuePair("sign", sign));
            params.add(new BasicNameValuePair("pageSize", "5"));
        } else {
            param = "appId=" + appid + "&pageSize=5" + "&startTime=" + checkData + "&time=" + l + "&key=" + secret;
            String sign = DigestUtils.md5Hex(param).toUpperCase();

            params.add(new BasicNameValuePair("appId", appid));
            params.add(new BasicNameValuePair("startTime", checkData));
            params.add(new BasicNameValuePair("time", l));
            params.add(new BasicNameValuePair("sign", sign));
            params.add(new BasicNameValuePair("pageSize", "5"));
        }


        String response = null;
        try {
            response = apacheHttpUtil.httpPost(url + "/cooPaPi/getRubbishList", params);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    public String checkCreateDate() {
        try {

            String sql2 = "SELECT createtime FROM workers ORDER BY createtime DESC LIMIT 1 OFFSET 0";

            String checkData = jdbcTemplate.queryForObject(sql2, String.class);
            return checkData;
        } catch (Exception e) {

            return null;
        }

    }
    }
