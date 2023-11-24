package com.satrack.task.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskDTO {

    private Long id;

    @NotNull
    @Size(max = 255)
    private String title;

    private String description;

    private Boolean isTaskCompleted;

    private List<Long> types;

    private Priority priority;

}
