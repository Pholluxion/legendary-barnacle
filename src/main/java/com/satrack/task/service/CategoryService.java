package com.satrack.task.service;

import com.satrack.task.model.CategoryDTO;

import java.util.List;

public interface CategoryService {
    public List<CategoryDTO> findAll();
    public CategoryDTO get(final Long id);
    public Long create(final CategoryDTO categoryDTO);
    public void update(final Long id, final CategoryDTO categoryDTO);
    public void delete(final Long id);
}
