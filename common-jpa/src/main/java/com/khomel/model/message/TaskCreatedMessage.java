package com.khomel.model.message;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskCreatedMessage implements Serializable {
    private Long taskId;
    private String title;
    private String description;
    private String deadLine;
    private Long userId;
}
