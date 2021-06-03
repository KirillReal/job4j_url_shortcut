package ru.job4j.urlshortcut.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.job4j.urlshortcut.model.Site;
import ru.job4j.urlshortcut.model.User;
import ru.job4j.urlshortcut.model.json.StatisticResponse;
import ru.job4j.urlshortcut.repository.SiteRepository;
import ru.job4j.urlshortcut.service.UserService;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StatisticController {
    private final SiteRepository siteRepository;
    private final UserService userService;

    public StatisticController(SiteRepository siteRepository, UserService userService) {
        this.siteRepository = siteRepository;
        this.userService = userService;
    }

    @GetMapping("/statistic")
    public List<StatisticResponse> statistic(@AuthenticationPrincipal String username) {
        User user = this.userService.getUserByUsername(username);
        Site site = this.siteRepository.getSiteByUser(user);
        ArrayList<StatisticResponse> convertedStats = new ArrayList<>();
        site.getStatistics().forEach(statistic -> {
            StatisticResponse statisticResponse = StatisticResponse.builder()
                    .url(statistic.getUrl().getUrl())
                    .total(statistic.getTotalCalls())
                    .build();
            convertedStats.add(statisticResponse);
        });
        return convertedStats;
    }
}
