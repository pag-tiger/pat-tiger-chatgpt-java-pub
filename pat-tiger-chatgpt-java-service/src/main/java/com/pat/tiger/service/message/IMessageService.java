package com.pat.tiger.service.message;

import com.alibaba.fastjson.JSONObject;
import com.pat.tiger.service.message.bean.dto.WxCallbackDTO;

import java.util.Map;

/**
 * @Description:
 * @Author: Hemon
 * @Date: 2023/2/17
 **/

public interface IMessageService {

    void send(WxCallbackDTO wxCallbackDTO);

    void setWhite(JSONObject jsonObject);

    Map<String, Integer> whiteList();

}
