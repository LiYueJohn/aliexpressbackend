package com.aliexpress.controller;

import com.aliexpress.config.Consts;
import com.aliexpress.dto.ResultDto;

public class BaseController {

    protected ResultDto getIllResult() {
        ResultDto dto = new ResultDto();
        dto.setCode(Consts.ErrCode.ILL_PARAMS);
        dto.setResult("参数错误");
        return dto;
    }
}
