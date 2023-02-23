package com.pat.tiger.bean.query;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description:
 * @Author: Hemon
 * @Date: 2023/2/17
 **/
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageQuery {

    private String wxid;

    private String content;

}