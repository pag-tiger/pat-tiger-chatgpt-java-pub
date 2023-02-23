package com.pat.tiger.controller;


import com.alibaba.cola.dto.SingleResponse;
import com.alibaba.fastjson.JSONObject;
import com.pat.tiger.bean.query.MessageQuery;
import com.pat.tiger.service.message.IMessageService;
import com.pat.tiger.service.message.bean.dto.WxCallbackDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("message")
public class MessageController {

    @Autowired
    IMessageService iMessageService;


    @PostMapping
    public SingleResponse message(@RequestBody WxCallbackDTO wxCallbackDTO) {
        //发送消息
        iMessageService.send(wxCallbackDTO);
        return SingleResponse.buildSuccess();
    }

    @PostMapping("white")
    public SingleResponse setWhite(@RequestBody JSONObject jsonObject) {
        //发送消息
        iMessageService.setWhite(jsonObject);
        return SingleResponse.buildSuccess();
    }

    @GetMapping("white/list")
    public SingleResponse whiteList() {
        //发送消息
        Map<String, Integer> map = iMessageService.whiteList();
        return SingleResponse.of(map);
    }

}