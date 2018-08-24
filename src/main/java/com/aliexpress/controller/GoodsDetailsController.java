package com.aliexpress.controller;

import com.aliexpress.config.Consts;
import com.aliexpress.dto.ResultDto;
import com.aliexpress.service.GoodsDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
public class GoodsDetailsController extends BaseController {

@Autowired
    private GoodsDetailsService goodsDetailsService;


@RequestMapping(value = Consts.Url.GOODS_DETAILS, method = RequestMethod.POST)
    public ResultDto getGoods(@RequestBody Map<String, Object> parms, HttpServletRequest request) {
    return goodsDetailsService.getGoodsDetails(parms);
}

    @RequestMapping(value = Consts.Url.GOODS_ADD_DETAILS, method = RequestMethod.POST)
public ResultDto addGoods(@RequestBody Map<String, Object> parms, HttpServletRequest request) {
    return goodsDetailsService.addGoodsDetails(parms);
}

    @RequestMapping(value = Consts.Url.GOODS_DEL_DETAILS, method = RequestMethod.POST)
public ResultDto delGoods(@RequestBody Map<String, Object> parms, HttpServletRequest request) {
    return goodsDetailsService.delGoodsDetails(parms);
}

    @RequestMapping(value = Consts.Url.GOODS_EDIT_DETAILS, method = RequestMethod.POST)
public ResultDto editGoods(@RequestBody Map<String, Object> parms, HttpServletRequest request) {
    return goodsDetailsService.editGoodsDetails(parms);
}

}