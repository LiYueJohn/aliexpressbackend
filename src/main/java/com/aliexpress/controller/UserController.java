package com.aliexpress.controller;

import com.aliexpress.config.Consts;
import com.aliexpress.dto.ResultDto;
import com.aliexpress.service.UserService;
import com.aliexpress.util.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

@RestController
public class UserController extends BaseController{

@Autowired
    private UserService userService;


@RequestMapping(value = Consts.Url.USER_LIST, method = RequestMethod.GET)
    public ResultDto queryUserList( HttpServletRequest request) {
    return userService.getUserList();
}

    @RequestMapping(value = Consts.Url.USER_LOGIN, method = RequestMethod.POST)
public ResultDto login(@RequestBody Map<String, Object> parms, HttpServletRequest request) {
    return userService.login(parms);
}

    @RequestMapping(value = Consts.Url.USER_DEL, method = RequestMethod.POST)
public ResultDto del(@RequestBody Map<String, Object> parms, HttpServletRequest request) {
    return userService.delUser(parms);
}

    @RequestMapping(value = Consts.Url.USER_ADD, method = RequestMethod.POST)
public ResultDto add(@RequestBody Map<String, Object> parms, HttpServletRequest request) {
    return userService.addUser(parms);
}

}