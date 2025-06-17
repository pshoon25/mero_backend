package com.mero.mero_backend.controller;

import com.mero.mero_backend.service.LoginService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class LoginController {
    private final LoginService loginService;

    @GetMapping("/login")
    public Map<String, Object> login(@RequestParam("loginId") String loginId,
                                     @RequestParam("password") String password,
                                     HttpServletResponse response) throws Exception {
        return loginService.checkLoginAuth(loginId, password, response);
    }

    /**
     * 유저 로그아웃
     * @param
     * @return
     * @throws java.lang.Exception
     */
    @PostMapping("/logout")
    public void logout() throws Exception {
        loginService.logout();
    }
}