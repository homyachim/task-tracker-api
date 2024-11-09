package com.khomel.model.dto;

import com.khomel.model.entity.enums.TaskState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskDto {
    private String title;
    private String description;
    private TaskState status;
    private LocalDate deadline;
    private LocalDate createdAt;
    private LocalDate startedAt;
    private LocalDate finishedAt;
    private Long userId;
}
