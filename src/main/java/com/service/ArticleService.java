package com.service;

import com.bean.Article;
import com.mapper.ArticleMapper;
import com.mapper.TagsMapper;
import com.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author duanbochao
 * @version 1.0
 * @date 2020/1/10 8:35
 */
@Service
public class ArticleService {

    @Autowired
    ArticleMapper articleMapper;

    @Autowired
    TagsMapper tagsMapper;

    public int addNewArticle(Article article) {

        //获取文章摘要信息
        if (article.getSummary()==null || "".equals(article.getSummary())){
            String htmlContent = article.getHtmlContent();
            article.setSummary(htmlContent.substring(0, htmlContent.length() > 50 ? 50 : htmlContent.length()));
        }
        if (article.getId()==-1){
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());

            if (article.getState()==1){
                article.setpublishDate(timestamp);
            }
            article.setEditTime(timestamp);

            //设置当前用户
            article.setUid(UserUtils.getCurrentUser().getId());
            Integer i = articleMapper.addNewArticle(article);

            //打标签
            String[] dynamicTags = article.getDynamicTags();
                if (dynamicTags!=null && dynamicTags.length>0){
                    int tags = addTagsToArticle(dynamicTags, article.getId());
                    if (tags==-1){
                        return tags;
                    }
                }
            return i;
        }else {
            return 0;
        }
    }

    //给文章添加标签
    public int addTagsToArticle(String[] dynamicTags,Integer aid){
        //根据文章id删除第三张表中的有关内容
        tagsMapper.deleteTagsByArticleId(aid);

        //将上传过来的Tags全部存储到数据库中
        tagsMapper.saveTags(dynamicTags);

        //获取存入到数据中的标签的id
        List<Integer> tagIds = tagsMapper.getTagsIdByTagsName(dynamicTags);

        //将新的标签插入到标签库中
        int i = tagsMapper.addNewTags(tagIds, aid);
        return i;
    }

    //获取该用户的数据条数
    public Integer getArticleCountByState(Integer state,Integer uid,String keyworlds){
        return articleMapper.getArticleCountByState(state, uid, keyworlds);
    }

    //获取分页数据

    public List<Article> getArticleByState(Integer state, Integer page, Integer count,String keywords) {
       Integer start= (page-1)*count;
        Integer uid = UserUtils.getCurrentUser().getId();
        return articleMapper.getArticleByState(state, uid, keywords, count, start);
    }


    /**
     * 获取七天日期
     * @param uid
     * @return
     */
    public   List<String> getCategories(){
        return articleMapper.getCategories(UserUtils.getCurrentUser().getId());
    }

    /**
     * 获取七天数据
     * @param uid
     * @return
     */
   public List<Integer> getDataStatistics(){
       return articleMapper.getDataStatistics(UserUtils.getCurrentUser().getId());
   }


}
