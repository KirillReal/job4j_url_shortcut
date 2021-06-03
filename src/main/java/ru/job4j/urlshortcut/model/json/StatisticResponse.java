package ru.job4j.urlshortcut.model.json;

import lombok.*;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StatisticResponse {
    private String url;
    private Long total;
}
