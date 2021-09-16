package com.doydoit.springmvcjpa.domain.login;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;

@Slf4j
@RequiredArgsConstructor
@Controller
public class LoginController {

    /**
     * 로그인
     * 	- 아이디 , 비밀 번호 (NotEmpty)
     * 	- findById "없는 아이디입니다"
     * 	- bindingResult.reject("아이디 비밀 번호가 맞지 않습니다");
     * 		- MemberService.loginCheck()
     *
     * 	- 로그인 성공 시
     * 		- new Cookie 생성 후 response.setCookie 전달
     * 		-
     *
     * 홈 화면
     * 	- Cookie 확인
     *        @CookieValue name= ?, require=false
     * 	 	- null  -> home
     * 		- notnull -> loginhome
     */

}
