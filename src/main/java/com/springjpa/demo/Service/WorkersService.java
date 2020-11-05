package com.springjpa.demo.Service;

import com.springjpa.demo.entity.Person;
import com.springjpa.demo.entity.QWorkers;
import com.springjpa.demo.entity.Workers;
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
import java.util.Map;

@Service
public class WorkersService {
    @Autowired
    private ApacheHttpUtil apacheHttpUtil;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    WorkersRepository workersRepository;

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

    public Long checkId() {
        try {
            String sql = "SELECT id FROM workers ORDER BY id DESC LIMIT 1 OFFSET 0";
            long id = jdbcTemplate.queryForObject(sql, Long.class);
            return id;
        } catch (Exception e) {
            return null;
        }
    }

    public void InsetInfo(List<Map<String, Object>> list) {


        for (Map<String, Object> map : list) {

            Workers w = new Workers();
            w.setUserId(String.valueOf(map.get("userId")));
            w.setAddress(String.valueOf(map.get("address")));
            w.setBuild(String.valueOf(map.get("build")));
            w.setCollectType(String.valueOf(map.get("collectType")));
            w.setCollectUser(String.valueOf(map.get("collectUser")));
            w.setCreateTime(String.valueOf(map.get("createTime")));
            w.setIdentityInfo(String.valueOf(map.get("identity")));
            w.setImgPath(String.valueOf(map.get("imgPath")));
            w.setPoint(String.valueOf(map.get("point")));
            w.setPutTime(String.valueOf(map.get("putTime")));
            w.setRubbishType(String.valueOf(map.get("rubbishType")));
            w.setUnit(String.valueOf(map.get("unit")));
            w.setUserName(String.valueOf(map.get("username")));
            w.setUserTel(String.valueOf(map.get("userTel")));
            w.setVillage(String.valueOf(map.get("village")));
            w.setWeight(String.valueOf(map.get("weight")));
            workersRepository.save(w);
        }
    }


}
