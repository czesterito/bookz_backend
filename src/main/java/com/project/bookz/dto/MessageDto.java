package com.project.bookz.dto;

import lombok.Data;
import java.sql.Timestamp;

@Data
public class MessageDto {

    private Integer messageId;
    private String content;
    private Timestamp time;
    private String nameUser;
    private Integer offerId;
}
