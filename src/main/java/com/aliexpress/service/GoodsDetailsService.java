package com.aliexpress.service;

import com.aliexpress.beans.GoodsDetails;
import com.aliexpress.config.Consts;
import com.aliexpress.dto.ResultDto;
import com.aliexpress.model.GoodsDetailsAction;
import com.aliexpress.util.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class GoodsDetailsService {

@Autowired
    GoodsDetailsAction goodsDetailsAction;


    public ResultDto getGoodsDetails(Map<String, Object> parms) {
    ResultDto resultDto = new ResultDto();
    Integer id = (Integer) parms.get("id");
    if (Validation.isNULL(id)) {
    resultDto.setCode(Consts.ErrCode.FAILED);
    resultDto.setResult("参数异常");
    return resultDto;
}
resultDto.setCode(Consts.ErrCode.SUCCESS);
GoodsDetails goodsDetails = goodsDetailsAction.getGoodsDetails(parms);
resultDto.setResult(goodsDetails);
return resultDto;
}

public ResultDto addGoodsDetails(Map<String, Object> parms) {
    ResultDto resultDto = new ResultDto();
    int id = (int) parms.get("id");
    if (Validation.isNULL(id)) {
        resultDto.setCode(Consts.ErrCode.FAILED);
        resultDto.setResult("参数异常");
        return resultDto;
    }
    resultDto.setCode(Consts.ErrCode.SUCCESS);
    Boolean aBoolean = goodsDetailsAction.addGoodsDetails(parms);
    resultDto.setResult(aBoolean);
    return resultDto;
}

public ResultDto delGoodsDetails(Map<String, Object> parms) {
    //        新建MyRowMapper类实现RowMapper接口，重写mapRow方法，
    ResultDto resultDto = new ResultDto();
    int id = (int) parms.get("id");
    if (Validation.isNULL(id)) {
        resultDto.setCode(Consts.ErrCode.FAILED);
        resultDto.setResult("参数异常");
        return resultDto;
    }
    resultDto.setCode(Consts.ErrCode.SUCCESS);
    Boolean aBoolean = goodsDetailsAction.delGoodsDetails(id);
    resultDto.setResult(aBoolean);
    return resultDto;
}

public ResultDto editGoodsDetails(Map<String, Object> parms) {
    ResultDto resultDto = new ResultDto();
    int id = (int) parms.get("id");
    if (Validation.isNULL(id)) {
        resultDto.setCode(Consts.ErrCode.FAILED);
        resultDto.setResult("参数异常");
        return resultDto;
    }
    resultDto.setCode(Consts.ErrCode.SUCCESS);
    Boolean aBoolean = goodsDetailsAction.editGoodsDetails(parms);
    resultDto.setResult(aBoolean);
    return resultDto;
}

}
