package com.pat.tiger.service.message.bean.dto;

import com.alibaba.cola.dto.DTO;
import com.pat.tiger.service.message.bean.enums.WXCallBackEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description:
 * @Author: Hemon
 * @Date: 2022/9/15
 **/
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WxCallbackDTO extends DTO {

    private WXCallBackEnum key;

    private String json;
    
}