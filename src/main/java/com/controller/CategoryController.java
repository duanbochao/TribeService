package com.controller;

import com.bean.Article;
import com.bean.Category;
import com.bean.RespBean;
import com.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author duanbochao
 * @version 1.0
 * @date 2020/1/8 14:58
 */
@RestController
@RequestMapping("/admin/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @RequestMapping(value = "/all",method = RequestMethod.GET)
    public List<Category> getAllCategories(){
        return categoryService.getAllCategories();
    }


    @RequestMapping(value = "/",method = RequestMethod.POST)
    public RespBean addNewCateGory(Category category){
        Integer integer = categoryService.addNewCateGory(category);
        if (integer>0){
            return new RespBean("success","添加成功");
        }
        return new RespBean("error","添加失败");
    }


    @RequestMapping("/updateCateCoryById")
    public RespBean updateCateCoryById(Category category){
        Integer integer = categoryService.updateCateCoryById(category);
        if (integer>0){
            return new RespBean("success","更新成功！");
        }
        return new RespBean("error","更新失败！");
    }

}
