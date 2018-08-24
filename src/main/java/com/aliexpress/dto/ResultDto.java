package com.aliexpress.dto;

/**
 * Created by lawi
 */
public class ResultDto {
    /**
     * 返回结果编码
     */
    private int code;
    /**
     * 返回结果
     */
    private Object result;

    public ResultDto() {
        super();
    }

    public ResultDto(int code, Object result) {
    this.code = code;
    this.result = result;
}

public int getCode() {
    return code;
}

public void setCode(int code) {
    this.code = code;
}

public Object getResult() {
    return result;
}

public void setResult(Object result) {
    this.result = result;
}
}
