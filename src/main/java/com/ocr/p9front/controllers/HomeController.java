package com.ocr.medicalcare.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Class HomeController
 */

@Controller
public class HomeController {

    private static final Logger log = LogManager.getLogger(HomeController.class);

    @RequestMapping("/")
    public String home(Model model) {
        log.debug("home");
        return "home";
    }

    @RequestMapping("/admin/home")
    public String adminHome(Model model) {
        log.debug("adminHome");
        return "redirect:/bidList/list";
    }


}
