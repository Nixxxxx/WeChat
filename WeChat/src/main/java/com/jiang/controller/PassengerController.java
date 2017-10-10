package com.jiang.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/passenger")
public class PassengerController {

    @RequestMapping("/q")
    public void q(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.sendRedirect("http://jiangh.ngrok.cc/WeChat/passenger/w");
    }

    @RequestMapping("/w")
    public void w(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        System.out.println(request.getParameter("code"));
    }

}
