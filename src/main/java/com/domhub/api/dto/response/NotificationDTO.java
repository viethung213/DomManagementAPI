package com.domhub.api.dto.response;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

import lombok.AllArgsConstructor;

@Getter
@Setter
@AllArgsConstructor
public class NotificationDTO {
    private String title;
    private String content;
    private LocalDate create_date;
    private String name_person_create;
}
