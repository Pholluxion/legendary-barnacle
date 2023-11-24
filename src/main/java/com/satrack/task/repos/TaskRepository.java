package com.satrack.task.repos;

import com.satrack.task.domain.Category;
import com.satrack.task.domain.Task;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findAllByCategories(Category category);

}
