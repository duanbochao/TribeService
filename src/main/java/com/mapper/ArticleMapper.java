package com.mapper;

import com.bean.Article;

import java.util.List;

/**
 * @author duanbochao
 * @version 1.0
 * @date 2020/1/10 11:17
 */
public interface ArticleMapper {

    public Integer addNewArticle(Article article);

    Integer getArticleCountByState(Integer state,Integer uid,String keyworlds);

    List<Article> getArticleByState(Integer state,Integer uid,String keyworlds,Integer count,Integer start);

    List<String> getCategories(Integer uid);

    List<Integer> getDataStatistics(Integer uid);


}
