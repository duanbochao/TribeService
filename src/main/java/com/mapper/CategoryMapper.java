package com.mapper;

import com.bean.Category;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author duanbochao
 * @version 1.0
 * @date 2020/1/8 14:55
 */
public interface CategoryMapper {

    List<Category> getAllCategories();

    Integer addNewCateGory(Category category);

    Integer updateCateCoryById(Category category);

    Integer deleteCateGory(@Param("ids") String[] ids);
}
