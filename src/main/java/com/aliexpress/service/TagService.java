package com.aliexpress.service;

import com.aliexpress.config.Consts;
import com.aliexpress.dto.ResultDto;
import com.aliexpress.model.TagAction;
import com.aliexpress.beans.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TagService {

@Autowired
    TagAction tagAction;

    public ResultDto getTagList() {
        ResultDto resultDto = new ResultDto();
        resultDto.setCode(Consts.ErrCode.SUCCESS);
        List list = tagAction.getTagList();
        resultDto.setResult(list);
        return resultDto;
    }

    public ResultDto getTag(Map<String, Object> parms) {
    ResultDto resultDto = new ResultDto();
    resultDto.setCode(Consts.ErrCode.SUCCESS);
    Tag tag = tagAction.getTag(parms);
    resultDto.setResult(tag);
    return resultDto;
}
public ResultDto addTag(Map<String, Object> parms) {
    ResultDto resultDto = new ResultDto();
    resultDto.setCode(Consts.ErrCode.SUCCESS);
    Boolean aBoolean = tagAction.addTag(parms);
    resultDto.setResult(aBoolean);
    return resultDto;
}

public ResultDto delTag(Map<String, Object> parms) {
    ResultDto resultDto = new ResultDto();
    resultDto.setCode(Consts.ErrCode.SUCCESS);
    Boolean aBoolean = tagAction.delTag(parms);
    resultDto.setResult(aBoolean);
    return resultDto;
}

public ResultDto editTag(Map<String, Object> parms) {
    ResultDto resultDto = new ResultDto();
    resultDto.setCode(Consts.ErrCode.SUCCESS);
    Boolean aBoolean = tagAction.editTag(parms);
    resultDto.setResult(aBoolean);
    return resultDto;
}
}
