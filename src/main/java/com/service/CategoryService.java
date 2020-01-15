package com.service;

import com.bean.Article;
import com.bean.Category;
import com.mapper.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author duanbochao
 * @version 1.0
 * @date 2020/1/8 14:57
 */
@Service
public class CategoryService {

    @Autowired
    CategoryMapper categoryMapper;

    public List<Category> getAllCategories(){
        return categoryMapper.getAllCategories();
    }

    public Integer addNewCateGory(Category category){
        category.setDate(new Timestamp(System.currentTimeMillis()));
        return categoryMapper.addNewCateGory(category);
    }


    public Integer updateCateCoryById(Category category){
        return categoryMapper.updateCateCoryById(category);
    }

    /**
     * 删除分类
     * @param ids
     * @return
     */
    public Integer deleteCateGory(String ids){
        String[] split = ids.split(",");
        return categoryMapper.deleteCateGory(split);
    }
}
