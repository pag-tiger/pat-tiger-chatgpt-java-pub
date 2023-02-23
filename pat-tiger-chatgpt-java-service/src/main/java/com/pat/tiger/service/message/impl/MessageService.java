package com.pat.tiger.service.message.impl;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import com.pat.tiger.service.message.IChatGptService;
import com.pat.tiger.service.message.IMessageService;
import com.pat.tiger.service.message.bean.dto.WxCallbackDTO;
import com.pat.tiger.service.message.bean.enums.WXCallBackEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @Author: Hemon
 * @Date: 2023/2/17
 **/
@Slf4j
@Service
public class MessageService implements IMessageService {

    @Autowired
    IChatGptService iChatGptService;

    Map<String, Integer> whiteMap = new HashMap<>();

    /**
     * wxid 白名单
     */
    @Bean
    void init() {
        whiteMap.put("wxid_1123331233812", 1);
        whiteMap.put("j8s119", 1);
        whiteMap.put("kai8015", 1);
        whiteMap.put("wxid_7sswza6mmx8451", 1);
        whiteMap.put("19960437829@chatroom", 1);
        whiteMap.put("18925319433@chatroom", 1);
        whiteMap.put("wxid_qzyu815sdq9721", 1);
        whiteMap.put("wxid_v4dq6bqmjr7s21", 1);
        whiteMap.put("20962744848@chatroom", 1);
    }

    @Override
    public void send(WxCallbackDTO wxCallbackDTO) {
        String json = wxCallbackDTO.getJson();
        if (WXCallBackEnum.WX_CALL_BACK_11046 == wxCallbackDTO.getKey()) {
            //文本
            JSONObject jsonObject = JSONObject.parseObject(json);
            log.info("原文:{}", wxCallbackDTO);
            log.info("JSON:{}", jsonObject.toJSONString());

            JSONObject data = jsonObject.getJSONObject("data");
            String msg = data.getString("msg");
            String wxid = data.getString("from_wxid");
            String room_wxid = data.getString("room_wxid");
            Integer wxidInteger = whiteMap.get(wxid);
            Integer roomWxidInteger = whiteMap.get(room_wxid);
            if (wxidInteger == null && roomWxidInteger == null) {
                return;
            }

            if (StringUtils.isEmpty(room_wxid)) {
                String answer = iChatGptService.getChatGptMessage(wxid, msg);
                //消息发送
                JSONObject body = new JSONObject();
                body.put("to_wxid", wxid);
                body.put("content", answer);
                senQwMessage(body.toJSONString());
            } else {
                //群聊
                if (msg.contains("@life")) {
                    //回复
                    msg = msg.replaceAll("@life", "");
                    //获取chatGpt答案
//                    String answer = iChatGptService.getRoomChatGptMessage(room_wxid, wxid, msg);
                    String answer = iChatGptService.getChatGptMessage(wxid, msg);
                    JSONObject body = new JSONObject();
                    body.put("to_wxid", room_wxid);
                    body.put("content", answer);
                    body.put("wxid", wxid);
                    //消息发送
                    senQwMessage(body.toJSONString());
                }
            }
        }
    }

    /**
     * 企微发送
     *
     * @param json
     */
    private void senQwMessage(String json) {
        HttpUtil.post("企微的发送url", json);
    }

    @Override
    public void setWhite(JSONObject jsonObject) {
        String wxid = jsonObject.getString("data");
        whiteMap.put(wxid, 1);
    }

    @Override
    public Map<String, Integer> whiteList() {
        return whiteMap;
    }

}