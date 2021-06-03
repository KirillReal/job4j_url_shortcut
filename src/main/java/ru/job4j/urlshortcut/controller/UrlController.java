package ru.job4j.urlshortcut.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.job4j.urlshortcut.model.Url;
import ru.job4j.urlshortcut.model.json.Code;
import ru.job4j.urlshortcut.service.StatisticService;
import ru.job4j.urlshortcut.service.UrlService;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@RestController
public class UrlController {
    private final UrlService urlService;
    private final StatisticService statisticService;

    public UrlController(UrlService urlService, StatisticService statisticService) {
        this.urlService = urlService;
        this.statisticService = statisticService;
    }

    @PostMapping("/convert")
    public ResponseEntity<Code> convert(@RequestBody Url url) {
        return new ResponseEntity<>(
                this.urlService.getCode(url),
                HttpStatus.OK);
    }

    @GetMapping("/redirect/{code}")
    public ResponseEntity<Void> redirect(@PathVariable String code, HttpServletResponse response) {
        Optional<Url> url = this.urlService.findUrlByCode(code);
        if (url.isPresent()) {
            response.setStatus(302);
            response.addHeader("REDIRECT", url.get().getUrl());
            this.statisticService.updateUrlStatistic(url.get());
            return new ResponseEntity<>(HttpStatus.valueOf(302));
        }
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }
}
