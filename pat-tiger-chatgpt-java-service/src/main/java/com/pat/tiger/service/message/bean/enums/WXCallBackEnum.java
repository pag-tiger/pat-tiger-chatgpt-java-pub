package com.pat.tiger.service.message.bean.enums;

/**
 * @Description:
 * @Author: Hemon
 * @Date: 2022/9/15
 **/
public enum WXCallBackEnum {

    other(0, "其他,特殊", "OtherCallback"),

    //联系人
    WX_CALL_BACK_11030(11030, "获取好友列表", "FriendAllCallback"),
    WX_CALL_BACK_11174(11174, "获取好友详细信息", "FriendByWxidCallback"),
    //企业微信联系人,
    WX_CALL_BACK_11132(11132, "获取企业微信好友列表", "QwFriendAllCallback"),
    WX_CALL_BACK_11257(11257, "获取企业微信详细信息", "QwFriendAllCallback"),
    WX_CALL_BACK_11258(11258, "企业号信息", "QwCorpCallback"),


    //正常微信消息
    WX_CALL_BACK_11046(11046, "文本消息", "MessageCallback"),
    WX_CALL_BACK_11047(11047, "图片消息", "MessageCallback"),
    WX_CALL_BACK_11048(11048, "语音消息/链接消息", "MessageCallback"),
    WX_CALL_BACK_11049(11049, "好友请求消息", "MessageCallback"),
    WX_CALL_BACK_11050(11050, "名片消息", "MessageCallback"),
    WX_CALL_BACK_11051(11051, "视频消息", "MessageCallback"),
    WX_CALL_BACK_11052(11052, "表情消息", "MessageCallback"),
    WX_CALL_BACK_11053(11053, "位置消息", "MessageCallback"),
    WX_CALL_BACK_11058(11058, "系统消息", "MessageCallback"),
    WX_CALL_BACK_11059(11059, "撤回消息", "MessageCallback"),
    WX_CALL_BACK_11095(11095, "二维码收款通知", "MessageCallback"),
    WX_CALL_BACK_11060(11060, "其他消息", "MessageCallback"),
    WX_CALL_BACK_11055(11055, "文件消息", "MessageCallback"),
    WX_CALL_BACK_11056(11056, "小程序消息", "MessageCallback"),
    WX_CALL_BACK_11057(11057, "转账消息", "MessageCallback"),
    WX_CALL_BACK_11061(11061, "其他应用类型消息", "MessageCallback"),
    //工作台微信消息监控
    WX_CALL_BACK_11054(11054, "图文、外链", "MessageCallback"),
    WX_CALL_BACK_11089(11089, "图文", "MessageCallback"),


    //CDN
    WX_CALL_BACK_11230(11230, "CDN下载", "CdnDownCallback"),
    WX_CALL_BACK_11228(11228, "CDN初始化", "OtherCallback"),

    //QW CDN
    WX_CALL_BACK_11253(11253, "企业微信CDN下载", "CdnDownCallback"),


    //视频号
    WX_CALL_BACK_11160(11160, "视频号初始化", "OtherCallback"),
    WX_CALL_BACK_11161(11161, "视频号搜索", "VideoSearchCallback"),
    WX_CALL_BACK_11170(11170, "视频号主页", "VideoHomeCallback"),
    WX_CALL_BACK_11169(11169, "视频详情", "VideoDetailCallback"),

    //群组
    WX_CALL_BACK_11031(11031, "群组列表", "OtherCallback"),

    //sql,
    WX_CALL_BACK_11027(11027, "sql", "SqlCallback"),

    ;


    private int type;

    private String alias;

    private String className;

    WXCallBackEnum(int type, String alias, String className) {
        this.type = type;
        this.alias = alias;
        this.className = className;
    }

    public String getAlias() {
        return alias;
    }

    public int getType() {
        return type;
    }

    public String getClassName() {
        return className;
    }

    public static WXCallBackEnum getWxCallBackEnum(int type) {
        switch (type) {
            case 11030:
                return WX_CALL_BACK_11030;
            case 11174:
                return WX_CALL_BACK_11174;
            case 11046:
                return WX_CALL_BACK_11046;
            case 11047:
                return WX_CALL_BACK_11047;
            case 11048:
                return WX_CALL_BACK_11048;
            case 11049:
                return WX_CALL_BACK_11049;
            case 11050:
                return WX_CALL_BACK_11050;
            case 11051:
                return WX_CALL_BACK_11051;
            case 11052:
                return WX_CALL_BACK_11052;
            case 11053:
                return WX_CALL_BACK_11053;
            case 11058:
                return WX_CALL_BACK_11058;
            case 11059:
                return WX_CALL_BACK_11059;
            case 11095:
                return WX_CALL_BACK_11095;
            case 11060:
                return WX_CALL_BACK_11060;
            case 11055:
                return WX_CALL_BACK_11055;
            case 11056:
                return WX_CALL_BACK_11056;
            case 11057:
                return WX_CALL_BACK_11057;
            case 11061:
                return WX_CALL_BACK_11061;
            case 11230:
                return WX_CALL_BACK_11230;
            case 11160:
                return WX_CALL_BACK_11160;
            case 11161:
                return WX_CALL_BACK_11161;
            case 11170:
                return WX_CALL_BACK_11170;
            case 11169:
                return WX_CALL_BACK_11169;
            case 11031:
                return WX_CALL_BACK_11031;
            case 11054:
                return WX_CALL_BACK_11054;
            case 11089:
                return WX_CALL_BACK_11089;
            case 11253:
                return WX_CALL_BACK_11253;
            case 11132:
                return WX_CALL_BACK_11132;
            case 11257:
                return WX_CALL_BACK_11257;
            case 11258:
                return WX_CALL_BACK_11258;
            case 11027:
                return WX_CALL_BACK_11027;
            default:
                return other;
        }
    }

    /**
     * 是否消息
     */
    public static boolean isMessage(WXCallBackEnum wxCallBackEnum) {
        if (WX_CALL_BACK_11046 == wxCallBackEnum ||
                WX_CALL_BACK_11047 == wxCallBackEnum ||
                WX_CALL_BACK_11048 == wxCallBackEnum ||
                WX_CALL_BACK_11049 == wxCallBackEnum ||
                WX_CALL_BACK_11050 == wxCallBackEnum ||
                WX_CALL_BACK_11051 == wxCallBackEnum ||
                WX_CALL_BACK_11052 == wxCallBackEnum ||
                WX_CALL_BACK_11053 == wxCallBackEnum ||
                WX_CALL_BACK_11058 == wxCallBackEnum ||
                WX_CALL_BACK_11059 == wxCallBackEnum ||
                WX_CALL_BACK_11095 == wxCallBackEnum ||
                WX_CALL_BACK_11060 == wxCallBackEnum ||
                WX_CALL_BACK_11055 == wxCallBackEnum ||
                WX_CALL_BACK_11056 == wxCallBackEnum ||
                WX_CALL_BACK_11057 == wxCallBackEnum ||
                WX_CALL_BACK_11061 == wxCallBackEnum ||
                WX_CALL_BACK_11054 == wxCallBackEnum ||
                WX_CALL_BACK_11089 == wxCallBackEnum) {
            return true;
        } else {
            return false;
        }
    }

}
