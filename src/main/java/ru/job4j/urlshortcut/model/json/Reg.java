package ru.job4j.urlshortcut.model.json;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
@Builder
public class Reg {
    private final boolean registration;
    private final String login;
    private final String password;
}
