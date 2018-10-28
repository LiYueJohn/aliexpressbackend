package com.aliexpress.service;

import com.aliexpress.config.Consts;
import com.aliexpress.dto.ResultDto;
import com.aliexpress.model.GoodsAction;
import com.aliexpress.beans.Goods;
import com.aliexpress.util.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class GoodsService {

@Autowired
    GoodsAction goodsAction;

    public ResultDto getGoodsList(Map<String,Object> params) {
    ResultDto resultDto = new ResultDto();
    resultDto.setCode(Consts.ErrCode.SUCCESS);
    resultDto.setResult(goodsAction.getGoodsList(params));
    return resultDto;
}

public ResultDto getGoods(Map<String, Object> parms) {
    ResultDto resultDto = new ResultDto();
    resultDto.setCode(Consts.ErrCode.SUCCESS);
    Goods Goods = goodsAction.getGoods(parms);
    resultDto.setResult(Goods);
    return resultDto;
}
public ResultDto addGoods(Map<String, Object> parms) {
    ResultDto resultDto = new ResultDto();
    resultDto.setCode(Consts.ErrCode.SUCCESS);
    Boolean aBoolean = goodsAction.addGoods(parms);
    resultDto.setResult(aBoolean);
    return resultDto;
}

public ResultDto delGoods(Map<String, Object> parms) {
    //        新建MyRowMapper类实现RowMapper接口，重写mapRow方法，
    ResultDto resultDto = new ResultDto();
    String goodsIds = (String) parms.get("goodsIds");
    String picIds = (String) parms.get("picIds");
    if(Validation.isNULL(goodsIds)){
        resultDto.setCode(Consts.ErrCode.FAILED);
        resultDto.setResult("参数异常");
        return resultDto;
    }
    resultDto.setCode(Consts.ErrCode.SUCCESS);
    Boolean aBoolean = goodsAction.delGoods(goodsIds,picIds);
    resultDto.setResult(aBoolean);
    return resultDto;
}

public ResultDto editGoods(Map<String, Object> parms) {
    ResultDto resultDto = new ResultDto();
    resultDto.setCode(Consts.ErrCode.SUCCESS);
    Boolean aBoolean = goodsAction.editGoods(parms);
    resultDto.setResult(aBoolean);
    return resultDto;
}

    public ResultDto queryGoodsStatus() {
        ResultDto resultDto = new ResultDto();
        resultDto.setCode(Consts.ErrCode.SUCCESS);
        resultDto.setResult(goodsAction.queryGoodsStatus());
        return resultDto;
    }
}
