package ru.otus.merets.library.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/*")
public class IndexController {

    /**
     * En: We use CrossOrigin here to use "integrity"-param in script (index.html)
     * Ru: CrossOrigin здесь нужен для проверки контрольной суммы скрипта, который
     * мы подгружаем с CDN jquery
     */
    @GetMapping
    @CrossOrigin("code.jquery.com")
    public String index() {
        return "index";
    }

}
