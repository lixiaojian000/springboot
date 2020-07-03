package com.example.pms.error;

import org.apache.shiro.authc.AuthenticationException;

public class NotPremissionException extends AuthenticationException {
    public NotPremissionException(String 无权限, String s) {
    }
}
