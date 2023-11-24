package com.satrack.task.service;

import com.satrack.task.model.TaskDTO;

import java.util.List;

public interface TaskService {
    public List<TaskDTO> findAll();
    public TaskDTO get(final Long id);
    public Long create(final TaskDTO taskDTO);
    public void update(final Long id, final TaskDTO taskDTO);
    public void delete(final Long id);

}
