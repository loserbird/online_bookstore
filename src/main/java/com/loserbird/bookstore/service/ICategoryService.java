package com.loserbird.bookstore.service;


import com.loserbird.bookstore.common.ServerResponse;

/**
 * Created by geely
 */
public interface ICategoryService {
    ServerResponse addCategory(String categoryName, Integer parentId);
    ServerResponse updateCategoryName(Integer categoryId, String categoryName);

}
