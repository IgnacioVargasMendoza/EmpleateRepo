
package com.empleate.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Ignac
 */
@Controller
public class HomeController {
    
    @RequestMapping("/")
    public String page(Model model) {
        /*model.addAttribute("attribute", "value");*/
        return "/home";
    }
    
}
