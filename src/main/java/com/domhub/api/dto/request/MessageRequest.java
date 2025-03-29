package com.domhub.api.dto.request;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageRequest {
    private String title;
    private String content;
    private int sent_by;
    private List<Integer> receiver;
}
