package com.aliexpress.service;

import com.aliexpress.beans.Img;
import com.aliexpress.config.Consts;
import com.aliexpress.dto.ResultDto;
import com.aliexpress.model.ImgAction;
import com.aliexpress.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.misc.BASE64Encoder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ImgService {


@Autowired
    ImgAction imgAction;

    public ResultDto getImgListByIds(Map<String, Object> parms) {
    ResultDto resultDto = new ResultDto();
    resultDto.setCode(Consts.ErrCode.SUCCESS);
    List<Map<String, Object>> ImgList = imgAction.getImgListByIds(parms);
    resultDto.setResult(ImgList);
    return resultDto;
}
public ResultDto getImgListById(String id) {
    ResultDto resultDto = new ResultDto();
    resultDto.setCode(Consts.ErrCode.SUCCESS);
    Img img = imgAction.getImgListById(id);
    resultDto.setResult(img);
    return resultDto;
}


public ResultDto uploadImg(String name,byte[] bytes) {
    // 将图片转为base64
    BASE64Encoder encoder = new BASE64Encoder();
    String imgData = encoder.encode(bytes);
    Map<String, Object> parms = new HashMap<>();
    parms.put("uuid", Util.getUUID());
    parms.put("img", imgData);
    parms.put("name", name);
    String res = imgAction.addImg(parms);
    ResultDto resultDto = new ResultDto();
    resultDto.setCode(Consts.ErrCode.SUCCESS);
    resultDto.setResult(res);
    return resultDto;
}
}
