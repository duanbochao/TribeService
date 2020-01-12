package com.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author duanbochao
 * @version 1.0
 * @date 2020/1/10 20:44
 */
public interface TagsMapper {

    int deleteTagsByArticleId(Integer aid);

    int saveTags(@Param("tags") String[] tags);

    List<Integer> getTagsIdByTagsName(@Param("tagsName") String[] tagsName);

    int addNewTags(@Param("tagIds") List<Integer> tagIds,@Param("aid") Integer aid);
}
