package com.example.demo.service;

import org.springframework.web.bind.annotation.*;

import java.io.File;

/**
 * The availability checking service
 *
 * @author <a href="mailto:lstonoyan@gmail.com">Levon Tonoyan</a>
 */
@RestController
@RequestMapping(value = "/service/available")
public class ServiceAvailable {

    @RequestMapping(method = RequestMethod.GET)
    public String availableGet() {
        return "Service available GET";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String availablePost() {
        return "Service available POST";
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String uploadFile(@RequestParam("name") String name,
                             @RequestParam(value = "file", required = false) File file) {
        return "redirect:uploadSuccess";
    }
}
