package com.controller;

import com.bean.Article;
import com.bean.RespBean;
import com.service.ArticleService;
import com.utils.UserUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author duanbochao
 * @version 1.0
 * @date 2020/1/9 13:21
 */
@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    ArticleService articleService;

    private SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");


    @RequestMapping("/")
    public RespBean addNewArticle(Article article){
        int i = articleService.addNewArticle(article);
        if (i>0){
            return new RespBean("success",article.getId()+"");
        }else {
            return new RespBean("error",article.getState()==0 ? "文件保存失败":"文件发表失败");
        }

    }


    /**
     * 图片上传
     * @param request
     * @param image
     * @return
     */
    @RequestMapping("/uploadimg")
    public RespBean upload(HttpServletRequest request, MultipartFile image){
        StringBuffer url = new StringBuffer(); //存放返回url地址的容器
        String imagePath="/blogimg/"+sdf.format(new Date()); //图片的相对路径，格式：/blogimg/日期
        String realPath = request.getServletContext().getRealPath(imagePath); //获取项目的绝对路径
        File imageFolder = new File(realPath); //查看是否有该文件夹

        if (!imageFolder.exists()) { //如果不存在
            imageFolder.mkdirs(); //创建该文件夹
        }
        //如果存在,将图片的名称重新命名
         String imageName= UUID.randomUUID()+"_"+image.getOriginalFilename().replaceAll(" ", "");
        //获取返回的url
        url.append(request.getScheme()) //相当于http
                .append("://") //相当于http://
                .append(request.getServerName()) //相当于http://localhost
                 .append(":")//相当于http://localhost:
                 .append(request.getServerPort())//相当于http://localhost:8080
                 .append(request.getContextPath()) //获取该tomcat容器的路径
                .append(imagePath); //相当于http://localhost:8080/项目容器/blogimage+日期

        try {
            IOUtils.write(image.getBytes(), new FileOutputStream(new File(realPath, imageName))); //存image图片到该realPath路径中
            url.append("/")//相当于http://localhost:8080/项目容器/blogimage+日期/
                    .append(imageName);//相当于http://localhost:8080/项目容器/blogimage+日期/图片名称
            return new RespBean("success",url.toString()); //上传成功后返回图片地址
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new RespBean("error","上传失败"); //上传成功后返回图片地址
    }

    /**
     * 分页查询
     * page
     * count
     * keyworlds
     * state
     */
    @RequestMapping("/all")
    public Map<String, Object> getArticleByState(Integer state, Integer count, Integer page, String keyworlds){
        Integer totalCount = articleService.getArticleCountByState(state, UserUtils.getCurrentUser().getId(), keyworlds);
        List<Article> articleList = articleService.getArticleByState(state, page, count, keyworlds);
        Map<String, Object> map = new HashMap<>();
        map.put("totalCount" ,totalCount);
        map.put("articleList" ,articleList);
        return map;
    }


    /**
     * 统计数据
     * @return
     */
    @RequestMapping("/dataStatistics")
    public Map<String,Object> dataStatistics(){
        HashMap<String, Object> map = new HashMap<>();
        List<String> categories = articleService.getCategories();
        List<Integer> dataStatistics = articleService.getDataStatistics();
        map.put("categories",categories);
        map.put("ds",dataStatistics);
        return map;
    }


}
