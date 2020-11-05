package com.springjpa.demo.controller;

import com.alibaba.fastjson.JSON;
import com.springjpa.demo.Service.WorkersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class WorkersController {
    @Autowired
    private WorkersService workersService;

    @RequestMapping(value = "/SearchInfo", method = {RequestMethod.GET})
    public String SearchInfo() {

        String checkData = workersService.checkCreateDate();

        String data = workersService.getRubbishData(checkData);
        Object succesResponse = JSON.parse(data);    //先转换成Object
        Map map = (Map) succesResponse;
        workersService.InsetInfo((List<Map<String, Object>>) map.get("data"));
        return data;
    }
}
