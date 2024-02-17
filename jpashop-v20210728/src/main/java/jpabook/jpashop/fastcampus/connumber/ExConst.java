package jpabook.jpashop.fastcampus.connumber;

import lombok.Getter;

@Getter
public enum ExConst {
    SERVER_ERROR(500),USER_ERROR(400);
    int errorNumber;

    ExConst(int errorNumber) {
        this.errorNumber = errorNumber;
    }
}
