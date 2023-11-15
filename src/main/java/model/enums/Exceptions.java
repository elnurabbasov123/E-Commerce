package model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public enum Exceptions {
    OBJECT_DONT_ADD("%s dont added !"),
    PASSWORD_OR_LOGIN_NOT_FOUND("%s your password or login is wrong !"),
    OPERATION_NOT_FOUND("Operation Not Found!"),
    LOGIN_EXIST("%s Login Already Exist"),
    DONT_UPDATED("%s Dont Updated !"),
    DONT_ADOPTED("Dear %s %s Your order dont adopted"),
    INSUFFICIENT_FUNDS(" Dear %s %s , there are not enough funds in your account!"),
    OBJECT_NOT_FOUND("%s  %s Not Found");

    private final String message;
}
