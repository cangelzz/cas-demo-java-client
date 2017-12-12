package com.casdemo.controller;

import org.jasig.cas.client.authentication.AttributePrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by xiaofei.cheng on 2017/6/2.
 */
@Controller
public class HomeController {
    @Autowired
    HttpServletRequest request;

    @RequestMapping(value = "/")
    public String home(Model model) {
        AttributePrincipal principal = (AttributePrincipal) request.getUserPrincipal();
        Map attributes = principal.getAttributes();
        model.addAttribute("attributes", attributes);
        return "index";
    }
}
