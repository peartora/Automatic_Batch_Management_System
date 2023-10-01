package com.example.batchmanagingsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController
{
    @GetMapping("/")
    public String returnIndexPage()
    {
        return "index";
    }

    @GetMapping("/batch-information")
    public String returnBatchInformationPage()
    {
        return "batch-information";
    }

    @GetMapping("/input-history")
    public String returnHistoryPage()
    {
        return "input-history";
    }
}
