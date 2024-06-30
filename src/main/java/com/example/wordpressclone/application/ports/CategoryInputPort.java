package com.example.wordpressclone.application.ports;

import com.example.wordpressclone.application.dtos.CategoryDTO;
import com.example.wordpressclone.application.exceptions.DataValidationException;
import com.example.wordpressclone.domain.exceptions.CategoryNotFoundException;

/**
 * Interface defining the operations for managing categories in the application.
 * @throws DataValidationException if input data is invalid
 * @throws CategoryNotFoundException if the category does not exist
 */
public interface CategoryInputPort {

    /**
     * Creates a new category in the system and returns the created category data.
     * @param categoryDTO the category data to create
     * @return the created category DTO
     * @throws DataValidationException if the input data is invalid
     */
    CategoryDTO createCategory(CategoryDTO categoryDTO) throws DataValidationException;

    /**
     * Updates an existing category identified by categoryId with the new data provided in categoryDTO.
     * @param categoryId the ID of the category to update
     * @param categoryDTO the new category data
     * @return the updated category DTO
     * @throws CategoryNotFoundException if the category does not exist
     */
    CategoryDTO updateCategory(Long categoryId, CategoryDTO categoryDTO) throws Category...