package com.aliexpress.service;

import com.aliexpress.config.Consts;
import com.aliexpress.dto.ResultDto;
import com.aliexpress.model.UserAction;
import com.aliexpress.beans.User;
import com.aliexpress.util.Util;
import com.aliexpress.util.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserService {

@Autowired
    UserAction userAction;
    public ResultDto getUserList() {
        ResultDto resultDto = new ResultDto();
        resultDto.setCode(Consts.ErrCode.SUCCESS);
        List list = userAction.getUserList();
        resultDto.setResult(list);
        return resultDto;
    }

    public ResultDto login(Map<String, Object> parms) {
    ResultDto resultDto = new ResultDto();
    resultDto.setCode(Consts.ErrCode.SUCCESS);
    User user = userAction.login2(parms);
    if(Validation.isNULL(user)){
    resultDto.setResult("failed");
}else {
    String role = "";
    switch (user.getRole()){
        case 1 : role = Util.encryptBASE64("主管"); break;
        case 2 : role = Util.encryptBASE64("产品编辑"); break;
        case 3 : role = Util.encryptBASE64("产品开发"); break;
        case 4 : role = Util.encryptBASE64("销售员"); break;
        case 5 : role = Util.encryptBASE64("仓库管理员"); break;
        default : role =Util.encryptBASE64("error");
    }
    resultDto.setResult(role);
}
return resultDto;
}

public ResultDto delUser(Map<String, Object> parms) {
    ResultDto resultDto = new ResultDto();
    int id = (int) parms.get("id");
    if (Validation.isNULL(id)) {
        resultDto.setCode(Consts.ErrCode.FAILED);
        resultDto.setResult("参数异常");
        return resultDto;
    }
    resultDto.setCode(Consts.ErrCode.SUCCESS);
    Boolean aBoolean = userAction.delUser(id);
    resultDto.setResult(aBoolean);
    return resultDto;
}
public ResultDto addUser(Map<String, Object> parms) {
    ResultDto resultDto = new ResultDto();
    String account = (String) parms.get("account");
    if (Validation.isNULL(account)) {
        resultDto.setCode(Consts.ErrCode.FAILED);
        resultDto.setResult("参数异常");
        return resultDto;
    }
    resultDto.setCode(Consts.ErrCode.SUCCESS);
    List<Map<String, Object>> list = userAction.getUser(account);
    if(!Validation.isNULL(list)&&list.size()>0){
        resultDto.setResult("-1");//用户已经存在
        return resultDto;
    }
    Boolean aBoolean = userAction.addUser(parms);
    resultDto.setResult(aBoolean);
    return resultDto;
}

}
