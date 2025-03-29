package com.domhub.api.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageToRequest {
    private int messageId;
    private int receiver;
}
