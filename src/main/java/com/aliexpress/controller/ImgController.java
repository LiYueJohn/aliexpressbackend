package com.aliexpress.controller;

import com.aliexpress.beans.Img;
import com.aliexpress.config.Consts;
import com.aliexpress.dto.ResultDto;
import com.aliexpress.service.ImgService;
import com.aliexpress.util.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Decoder;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@RestController
public class ImgController extends BaseController {

@Autowired
    private ImgService imgService;


@RequestMapping(value = Consts.Url.IMGLIST_BYIDS, method = RequestMethod.POST)
    public ResultDto queryImgList(@RequestBody Map<String, Object> parms, HttpServletRequest request) {
    return imgService.getImgListByIds(parms);
}

    @RequestMapping(value = Consts.Url.ADD_IMG, method = RequestMethod.POST)
public ResultDto updateImg(@RequestParam("file") MultipartFile multiFile, HttpServletRequest request) throws IOException {
    if (multiFile.isEmpty()) {
        ResultDto resultDto = getIllResult();
        resultDto.setResult("上传文件为空");
        return resultDto;
    }
    return imgService.uploadImg(multiFile.getOriginalFilename(), multiFile.getBytes());
}

//多图片上传
@RequestMapping(value = Consts.Url.IMG_BATCH_UPLOAD, method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseBody
public ResultDto handleFileUpload(@RequestParam(value = "files") MultipartFile[] multipartFiles, HttpServletRequest req, HttpServletResponse response) throws IOException {
    ResultDto resultDto = new ResultDto();
    resultDto.setCode(Consts.ErrCode.SUCCESS);
    StringBuilder stringBuilder = new StringBuilder();
    for (MultipartFile file : multipartFiles) {
        if (!file.isEmpty()) {
            ResultDto resultDtoTemp = imgService.uploadImg(file.getOriginalFilename(), file.getBytes());
            stringBuilder.append(resultDtoTemp.getResult()).append(",");
        }
    }
    String res = stringBuilder.toString();
    if (Validation.isNotEmpty(res)) {
        resultDto.setResult(res.substring(0, res.length() - 1));
    } else {
        resultDto.setResult("false");
    }
    return resultDto;
}

/*** 取得图片
 * @param request
 * @param response
 */
@RequestMapping(value = Consts.Url.IMG_BYID, method = RequestMethod.GET)
public void getImg(HttpServletRequest request, HttpServletResponse response) {


    String imgId = request.getParameter("id");
    if(!Validation.isNotEmpty(imgId)){
        return ;
    }
    ResultDto resultDto = imgService.getImgListById(imgId);
    Img data = (Img) resultDto.getResult();
    BASE64Decoder decoder = new BASE64Decoder();
    try {
        byte[] bytes = decoder.decodeBuffer(data.getImg());
        for (int i = 0; i < bytes.length; ++i) {
            if (bytes[i] < 0) {// 调整异常数据
                bytes[i] += 256;
            }
        }
        ServletOutputStream out = response.getOutputStream();
        out.write(bytes);
        out.flush();
        out.close();
    } catch (IOException e) {
        e.printStackTrace();
    }
}

/*** 取得图片
 * @param
 * @param response
 */
@RequestMapping(value = Consts.Url.IMG_DOWNBYID, method = RequestMethod.GET)
public void download(HttpServletRequest request, HttpServletResponse response) {

    try {
        Map<String, Object> parms = new HashMap<>();
        parms.put("ids", request.getParameter("ids"));
        ResultDto resultDto = imgService.getImgListByIds(parms);
        List<Map<String, Object>> imgList = (List<Map<String, Object>>) resultDto.getResult();
        Date day = new Date();

        SimpleDateFormat df = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
        String downloadFilename = df.format(day) + ".zip";//文件的名称
        downloadFilename = URLEncoder.encode(downloadFilename, "UTF-8");//转换中文否则可能会产生乱码
        response.setContentType("application/octet-stream");// 指明response的返回对象是文件流
        response.setHeader("Content-Disposition", "attachment;filename=" + downloadFilename);// 设置在下载框默认显示的文件名
        ZipOutputStream zos = new ZipOutputStream(response.getOutputStream());
        imgList.forEach(data -> {
            try {
                zos.putNextEntry(new ZipEntry((String) data.get("name")));
        BASE64Decoder decoder = new BASE64Decoder();
        byte[] buffer1 = (byte[]) data.get("img");
        byte[] buffer = decoder.decodeBuffer(new String(buffer1));
        for (int i = 0; i < buffer.length; ++i) {
            if (buffer[i] < 0) {// 调整异常数据
                buffer[i] += 256;
            }
        }
        zos.write(buffer);
    } catch (Exception e) {
            e.printStackTrace();
        }
    });
        zos.flush();
        zos.close();
    } catch (Exception e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
}

}