package com.github.liuyuyu.dictator.server.common.exception.enums;

import com.github.liuyuyu.dictator.server.common.constant.UserConstants;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author liuyuyu
 */
@Getter
@AllArgsConstructor
public enum UserErrorMessageEnum implements AbstractErrorMessageEnum {
    MISS_TOKEN("没有token参数"),
    INVALID_TOKEN("token无效或已过期"),
    TOKEN_PARAM_NOT_FOUND("缺少参数" + UserConstants.TOKEN_NAME),
    USER_NOT_FOUND("用户名或密码错误"),
    INCORRECT_PASSWORD(USER_NOT_FOUND.getErrorMessage());
    /**
     * 默认信息
     */
    private String errorMessage;
}
