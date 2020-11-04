package com.springjpa.demo.controller;

import com.springjpa.demo.Service.WorkersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WorkersController {
    @Autowired
    private WorkersService workersService;

    @RequestMapping(value = "/SearchInfo", method = {RequestMethod.GET})
    public String SearchInfo() {

        String checkData = workersService.checkCreateDate();

        return workersService.getRubbishData(checkData);

    }
}
