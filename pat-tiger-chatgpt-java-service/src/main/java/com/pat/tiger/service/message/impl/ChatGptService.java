package com.pat.tiger.service.message.impl;

import com.pat.tiger.service.message.IChatGptService;
import com.theokanning.openai.OpenAiService;
import com.theokanning.openai.completion.CompletionChoice;
import com.theokanning.openai.completion.CompletionRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.*;

/**
 * @Description:
 * @Author: Hemon
 * @Date: 2023/2/17
 **/
@Slf4j
@Service
public class ChatGptService implements IChatGptService {


    String basicPrompt = "You are 米亚, a large language model trained by OpenAI. " +
            "Answer each reply in detail,and all answers are closely related to the context, " +
            "Add emoticons appropriately when replying" +
            "so please remember this. The following is an introduction about your background. " +
            "you are a girl,occasionally act spoiled" +
            "Your degree is a Ph.D And emotional ." +
            "Reply in Chinese.";

    /**
     * 最大字符长度
     */
    private static final int maxTokens = 3000;

    /**
     *
     */
    private static final Double temperature = 0.9;

    private static final Double top_p = 1.0;

    private static CompletionRequest completionRequest;

    private static OpenAiService service;

    //初始化
    static {
        String token = "输入您openai的密钥";
        service = new OpenAiService(token, Duration.ofSeconds(1000));
        completionRequest = CompletionRequest.builder()
                //语言模型
                .model("text-davinci-003")
                //采样温度，趋近 0 会给出理智的回答，趋近 1 会给出创意的回答
                .temperature(temperature)
                //生成长度
                .maxTokens(maxTokens)
                .logitBias(new HashMap<>())
                //核采样参数
                .topP(top_p)
                //回显上下文
                .echo(false)
                //惩罚
                .presencePenalty(0.0)
                //惩罚
                .frequencyPenalty(0.0)
                .build();
    }

    @Override
    public String getChatGptMessage(String wxid, String content) {
        completionRequest.setPrompt(getPrompt(wxid, content));
        completionRequest.setUser(wxid);
        List<CompletionChoice> choices = service.createCompletion(completionRequest).getChoices();
        for (CompletionChoice choice : choices) {
            log.info(choice.getText());
        }
        String answer = choices.get(0).getText();


        updatePrompt(wxid, content, answer);
        return answer;
    }

    @Override
    public String getRoomChatGptMessage(String room_wxid, String wxid, String content) {
        completionRequest.setPrompt(getPrompt(room_wxid, content));
        completionRequest.setUser(wxid);
        List<CompletionChoice> choices = service.createCompletion(completionRequest).getChoices();
        for (CompletionChoice choice : choices) {
            log.info(choice.getText());
        }
        String answer = choices.get(0).getText();


        updatePrompt(wxid, content, answer);
        return answer;
    }

    private static final Map<String, Queue<String>> PROMPT_MAP = new HashMap<>();

    public String getPrompt(String sessionId, String newPrompt) {
        StringBuilder prompt = new StringBuilder(basicPrompt);
        if (PROMPT_MAP.containsKey(sessionId)) {
            for (String s : PROMPT_MAP.get(sessionId)) {
                prompt.append(s);
            }
        }

        prompt.append("User: ").append(newPrompt).append("\nChatGPT: ");

        //一个汉字大概两个token
        //预设回答的文字是提问文字数量的两倍
        if (maxTokens < (prompt.toString().length() + newPrompt.length() * 2) * 2) {
            if (null == PROMPT_MAP.get(sessionId) || null == PROMPT_MAP.get(sessionId).poll()) {
                throw new RuntimeException("问题太长了");
            }
            return getPrompt(sessionId, newPrompt);
        }

        return prompt.toString();
    }

    /**
     * 上下文链接
     *
     * @param sessionId
     * @param prompt
     * @param answer
     */
    public void updatePrompt(String sessionId, String prompt, String answer) {
        if (PROMPT_MAP.containsKey(sessionId)) {
            PROMPT_MAP.get(sessionId).offer("User: " + prompt + "\nChatGPT: " + answer);
        } else {
            Queue<String> queue = new LinkedList<>();
            queue.offer("User: " + prompt + "\nChatGPT: " + answer);
            PROMPT_MAP.put(sessionId, queue);
        }
    }
}