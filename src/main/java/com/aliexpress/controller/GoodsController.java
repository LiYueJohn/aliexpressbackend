package com.aliexpress.controller;

import com.aliexpress.config.Consts;
import com.aliexpress.dto.ResultDto;
import com.aliexpress.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RestController
public class GoodsController extends BaseController{

@Autowired
    private GoodsService goodsService;


@RequestMapping(value = Consts.Url.GOODS_LIST, method = RequestMethod.POST)
    public ResultDto queryGoodsList(@RequestBody Map<String, Object> params, HttpServletRequest request) {
    return goodsService.getGoodsList(params);
}

    @RequestMapping(value = Consts.Url.GOODS_STATUS, method = RequestMethod.GET)
    public ResultDto queryGoodsStatus(HttpServletResponse response, HttpServletRequest request) {
        return goodsService.queryGoodsStatus();
    }

    @RequestMapping(value = Consts.Url.GOODS_BYID, method = RequestMethod.POST)
public ResultDto getGoods(@RequestBody Map<String, Object> parms, HttpServletRequest request) {
    return goodsService.getGoods(parms);
}

    @RequestMapping(value = Consts.Url.GOODS_ADD, method = RequestMethod.POST)
public ResultDto addGoods(@RequestBody Map<String, Object> parms, HttpServletRequest request) {
    return goodsService.addGoods(parms);
}

    @RequestMapping(value = Consts.Url.GOODS_DEL, method = RequestMethod.POST)
public ResultDto delGoods(@RequestBody Map<String, Object> parms, HttpServletRequest request) {
    return goodsService.delGoods(parms);
}

    @RequestMapping(value = Consts.Url.GOODS_EDIT, method = RequestMethod.POST)
public ResultDto editGoods(@RequestBody Map<String, Object> parms, HttpServletRequest request) {
    return goodsService.editGoods(parms);
}

}