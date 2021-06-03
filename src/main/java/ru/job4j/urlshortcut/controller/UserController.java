package ru.job4j.urlshortcut.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ru.job4j.urlshortcut.model.Site;
import ru.job4j.urlshortcut.model.User;
import ru.job4j.urlshortcut.model.json.Reg;
import ru.job4j.urlshortcut.model.json.SiteName;
import ru.job4j.urlshortcut.service.AuthorityService;
import ru.job4j.urlshortcut.service.SiteService;
import ru.job4j.urlshortcut.service.UserService;

import javax.transaction.Transactional;

@RestController
public class UserController {
    private final UserService userService;
    private final SiteService siteService;
    private final AuthorityService authorityService;
    private final BCryptPasswordEncoder encoder;

    public UserController(UserService users,
                          SiteService siteService,
                          AuthorityService authorityService,
                          BCryptPasswordEncoder encoder) {
        this.userService = users;
        this.siteService = siteService;
        this.authorityService = authorityService;
        this.encoder = encoder;
    }

    @PostMapping("/registration")
    @Transactional
    public ResponseEntity<Reg> registration(@RequestBody SiteName siteName) {
        Site site = Site.builder()
                .name(siteName.getSite())
                .build();
        if (this.siteService.isSiteRegistered(site.getName())) {
            return new ResponseEntity<>(
                    new Reg(false, "", ""),
                    HttpStatus.CONFLICT);
        }
        String username = this.userService.getNewLogin();
        String password = this.userService.getUniqueString();
        Long authorityId = this.authorityService.getAuthorityByAuthority("ROLE_USER").getId();
        site = this.siteService.save(site);
        User user = User.builder()
                .site(site)
                .username(username)
                .password(encoder.encode(password))
                .authorityId(authorityId)
                .build();
        user = this.userService.save(user);
        site.addUser(user);
        this.siteService.save(site);
        return new ResponseEntity<>(
                new Reg(true, username, password),
                HttpStatus.OK);
    }

    @GetMapping("/onlyAuthenticated")
    public ResponseEntity<Void> checkAuthentication() {
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
