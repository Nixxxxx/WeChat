package com.jiang.controller;

import com.jiang.util.WeixinUtil;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@RequestMapping("/user")
public class PassengerController {

    @RequestMapping("/q")
    public String q(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String code = request.getParameter("code");
        JSONObject json = WeixinUtil.getAccessToken(code);
        HttpSession session = request.getSession();
        if(session.getAttribute("openid") == null){
            session.setAttribute("openid", json.get("openid"));
        }
        System.out.println(json.get("openid"));
        return "redirect:./w";
    }

    @RequestMapping("/w")
    public void w(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println(1);
        System.out.println(request.getSession().getAttribute("openid"));
        response.sendRedirect("../view/binding.jsp");
    }

}
