package com.loserbird.bookstore.service;


import com.loserbird.bookstore.common.ServerResponse;
import com.loserbird.bookstore.pojo.Category;

import java.util.List;


public interface ICategoryService {
    ServerResponse addCategory(String categoryName);
    ServerResponse updateCategoryName(Integer categoryId, String categoryName);
    ServerResponse<List<Category>> getAllCategory();

    Category queryCategoryByName(String name);

}
