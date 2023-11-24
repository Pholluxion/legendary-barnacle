package com.satrack.task.service;

import com.satrack.task.domain.Category;
import com.satrack.task.domain.Task;
import com.satrack.task.model.TaskDTO;
import com.satrack.task.repos.CategoryRepository;
import com.satrack.task.repos.TaskRepository;
import com.satrack.task.util.NotFoundException;
import jakarta.transaction.Transactional;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
@Transactional
public class TaskServiceImpl implements  TaskService{

    private final TaskRepository taskRepository;
    private final CategoryRepository categoryRepository;

    public TaskServiceImpl(final TaskRepository taskRepository,
                           final CategoryRepository categoryRepository) {
        this.taskRepository = taskRepository;
        this.categoryRepository = categoryRepository;
    }



    @Override
    public List<TaskDTO> findAll() {
        final List<Task> tasks = taskRepository.findAll(Sort.by("id"));
        return tasks.stream()
                .map(task -> mapToDTO(task, new TaskDTO()))
                .toList();
    }

    @Override
    public TaskDTO get(Long id) {
        return taskRepository.findById(id)
                .map(task -> mapToDTO(task, new TaskDTO()))
                .orElseThrow(NotFoundException::new);
    }

    @Override
    public Long create(TaskDTO taskDTO) {
        final Task task = new Task();
        mapToEntity(taskDTO, task);
        return taskRepository.save(task).getId();
    }

    @Override
    public void update(Long id, TaskDTO taskDTO) {
        final Task task = taskRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(taskDTO, task);
        taskRepository.save(task);
    }

    @Override
    public void delete(Long id) {
        taskRepository.deleteById(id);
    }

    private TaskDTO mapToDTO(final Task task, final TaskDTO taskDTO) {
        taskDTO.setId(task.getId());
        taskDTO.setTitle(task.getTitle());
        taskDTO.setDescription(task.getDescription());
        taskDTO.setIsTaskCompleted(task.getIsTaskCompleted());
        taskDTO.setEndDate(task.getEndDate());
        taskDTO.setPriority(task.getPriority());
        taskDTO.setCategories(task.getCategories().stream()
                .map(Category::getId)
                .toList());
        return taskDTO;
    }

    private Task mapToEntity(final TaskDTO taskDTO, final Task task) {
        task.setTitle(taskDTO.getTitle());
        task.setDescription(taskDTO.getDescription());
        task.setIsTaskCompleted(taskDTO.getIsTaskCompleted());
        task.setPriority(taskDTO.getPriority());
        task.setEndDate(taskDTO.getEndDate());
        final List<Category> types = categoryRepository.findAllById(
                taskDTO.getCategories() == null ? Collections.emptyList() : taskDTO.getCategories());
        if (types.size() != (taskDTO.getCategories() == null ? 0 : taskDTO.getCategories().size())) {
            throw new NotFoundException("Uno o más tipos no fueron encontrados");
        }
        task.setCategories(new HashSet<>(types));
        return task;
    }
}
