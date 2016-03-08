package br.com.knowrad.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/roadmap")
public class RoadMapController {

    private static final String ROADMAP = "config/roadmap";

    @RequestMapping(value = "/")
    public ModelAndView roadmap() {
        return new ModelAndView(ROADMAP);
    }

}