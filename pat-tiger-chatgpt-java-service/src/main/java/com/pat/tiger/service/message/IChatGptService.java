package com.pat.tiger.service.message;

/**
 * @Description:
 * @Author: Hemon
 * @Date: 2023/2/17
 **/

public interface IChatGptService {

    String getChatGptMessage(String wxid, String content);

    String getRoomChatGptMessage(String room_wxid, String wxid, String msg);

}
