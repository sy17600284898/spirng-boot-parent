package com.syy.springboot.result;

/**
 * ResultStatusCode
 *
 * @author: shiyan
 * @version: 2019-12-06 15:42
 * @Copyright: 2019 www.lenovo.com Inc. All rights reserved.
 */

public enum ResultStatusCode {
    OK(200, "ok", "OK"),
    TIME_OUT(130, "Access timeout", "访问超时"),
    BAD_REQUEST(400, "Parameter parsing failed", "参数解析失败"),
    INVALID_TOKEN(401, "Invalid authorization code", "无效的授权码"),
    METHOD_NOT_ALLOWED(405, "Does not support the current request method", "不支持当前请求方法"),
    SYSTEM_ERR(500, "The server is running abnormally", "服务器运行异常"),
    NOT_EXIST_USER_OR_ERROR_PWD(10000, "The user does not exist or the password is incorrect", "该用户不存在或密码错误"),
    LOGINED_IN(10001, "The user does not exist or the password is incorrect", "该用户已登录"),
    SHIRO_ERROR(10003, "Login exception", "登录异常"),
    UNAUTHO_ERROR(10004, "You do not have this permission", "您没有该权限");

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
