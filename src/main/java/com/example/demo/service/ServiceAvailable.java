package com.example.demo.service;

import org.springframework.web.bind.annotation.*;

import java.io.File;

/**
 * The availability checking service
 *
 * @author <a href="mailto:lstonoyan@gmail.com">Levon Tonoyan</a>
 */
@RestController
@RequestMapping(value = "/available")
public class ServiceAvailable {

    @GetMapping
    public String availableGet() {
        return "Service available";
    }

    @PostMapping
    public String availablePost() {
        return "Service available";
    }

    @PostMapping
    @RequestMapping("/upload")
    public String uploadFile(@RequestParam("name") String name,
                             @RequestParam(value = "file", required = false) File file) {
        return "redirect:uploadSuccess";
    }
}
