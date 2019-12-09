package com.syy.springboot.enums;

/**
 * ResultStatusCode
 *
 * @author: shiyan
 * @version: 2019-12-06 15:42
 * @Copyright: 2019 www.lenovo.com Inc. All rights reserved.
 */

public enum ResultStatusCode {
    OK(0, "ok", "OK"),
    SIGN_ERROR(120, "Signature error", "签名错误"),
    TIME_OUT(130, "Access timeout", "访问超时"),
    BAD_REQUEST(400, "Parameter parsing failed", "参数解析失败"),
    INVALID_TOKEN(401, "Invalid authorization code", "无效的授权码"),
    INVALID_CLIENTID(402, "Invalid key", "无效的密钥"),
    METHOD_NOT_ALLOWED(405, "Does not support the current request method", "不支持当前请求方法"),
    SYSTEM_ERR(500, "The server is running abnormally", "服务器运行异常"),
    NOT_EXIST_USER_OR_ERROR_PWD(10000, "The user does not exist or the password is incorrect", "该用户不存在或密码错误"),
    LOGINED_IN(10001, "The user does not exist or the password is incorrect", "该用户已登录"),
    NOT_EXIST_BUSINESS(10002, "The business does not exist", "该商家不存在"),
    SHIRO_ERROR(10003, "Login exception", "登录异常"),
    UNAUTHO_ERROR(10004, "You do not have this permission", "您没有该权限"),
    BIND_PHONE(10010, "Please bind mobile number", "请绑定手机号"),
    UPLOAD_ERROR(20000, "Upload file exception", "上传文件异常"),
    INVALID_CAPTCHA(30005, "Invalid verification code", "无效的验证码"),
    USER_FROZEN(40000, "The user has been frozen", "该用户已被冻结");

    private int code;
    private String msg;
    private String explanation;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    ResultStatusCode(int code, String msg, String explanation) {
        this.code = code;
        this.msg = msg;
        this.explanation = explanation;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }
}
