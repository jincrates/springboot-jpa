package com.jincrates.springbootjpa.controller;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

//Member Entitiy는 최대한 경량화. 화면관련 로직은 추가하지 않는 것이 유지보수하기 좋다.
//Form이나 DTO를 사용하기를 권장
@Getter @Setter
public class MemberForm {

    @NotEmpty(message = "회원 이름은 필수 입니다.")
    private String name;

    private String city;
    private String street;
    private String zipcode;

}
