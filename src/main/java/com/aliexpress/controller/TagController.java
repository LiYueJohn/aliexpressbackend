package com.aliexpress.controller;

import com.aliexpress.config.Consts;
import com.aliexpress.dto.ResultDto;
import com.aliexpress.service.TagService;
import com.aliexpress.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
public class TagController extends BaseController{

@Autowired
    private TagService tagService;


@RequestMapping(value = Consts.Url.TAG_LIST, method = RequestMethod.POST)
    public ResultDto queryTagList( HttpServletRequest request) {
    return tagService.getTagList();
}

    @RequestMapping(value = Consts.Url.TAG_BYID, method = RequestMethod.POST)
public ResultDto getTag(@RequestBody Map<String, Object> parms, HttpServletRequest request) {
    return tagService.getTag(parms);
}

    @RequestMapping(value = Consts.Url.TAG_ADD, method = RequestMethod.POST)
public ResultDto addTag(@RequestBody Map<String, Object> parms, HttpServletRequest request) {
    return tagService.addTag(parms);
}

    @RequestMapping(value = Consts.Url.TAG_DEL, method = RequestMethod.POST)
public ResultDto delTag(@RequestBody Map<String, Object> parms, HttpServletRequest request) {
    return tagService.delTag(parms);
}

    @RequestMapping(value = Consts.Url.TAG_EDIT, method = RequestMethod.POST)
public ResultDto editTag(@RequestBody Map<String, Object> parms, HttpServletRequest request) {
    return tagService.editTag(parms);
}

}