package com.aliexpress.controller;

import com.aliexpress.config.Consts;
import com.aliexpress.dto.ResultDto;
import com.aliexpress.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

@RestController
public class GoodsController extends BaseController{

@Autowired
    private GoodsService GoodsService;


@RequestMapping(value = Consts.Url.GOODS_LIST, method = RequestMethod.POST)
    public ResultDto queryGoodsList(@RequestBody Map<String, Object> params, HttpServletRequest request) {
    return GoodsService.getGoodsList(params);
}

    @RequestMapping(value = Consts.Url.GOODS_BYID, method = RequestMethod.POST)
public ResultDto getGoods(@RequestBody Map<String, Object> parms, HttpServletRequest request) {
    return GoodsService.getGoods(parms);
}

    @RequestMapping(value = Consts.Url.GOODS_ADD, method = RequestMethod.POST)
public ResultDto addGoods(@RequestBody Map<String, Object> parms, HttpServletRequest request) {
    return GoodsService.addGoods(parms);
}

    @RequestMapping(value = Consts.Url.GOODS_DEL, method = RequestMethod.POST)
public ResultDto delGoods(@RequestBody Map<String, Object> parms, HttpServletRequest request) {
    return GoodsService.delGoods(parms);
}

    @RequestMapping(value = Consts.Url.GOODS_EDIT, method = RequestMethod.POST)
public ResultDto editGoods(@RequestBody Map<String, Object> parms, HttpServletRequest request) {
    return GoodsService.editGoods(parms);
}

}