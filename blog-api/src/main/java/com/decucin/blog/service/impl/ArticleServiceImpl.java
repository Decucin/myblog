package com.decucin.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.decucin.blog.dao.mapper.ArticleMapper;
import com.decucin.blog.dao.pojo.Article;
<<<<<<< HEAD
import com.decucin.blog.service.BodyService;
import com.decucin.blog.service.SysUserService;
import com.decucin.blog.service.TagService;
import com.decucin.blog.vo.ArticleVo;
import com.decucin.blog.vo.Result;
import com.decucin.blog.service.ArticleService;
import com.decucin.blog.vo.params.PageParams;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
=======
import com.decucin.blog.service.ArticleService;
import com.decucin.blog.service.BodyService;
import com.decucin.blog.service.SysUserService;
import com.decucin.blog.service.TagService;
import com.decucin.blog.utils.JWTTokenUtils;
import com.decucin.blog.vo.ArticleVo;
import com.decucin.blog.vo.Result;
import com.decucin.blog.vo.params.PageParams;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.search.sort.ScoreSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
>>>>>>> master

/**
 * @author ：decucin
 * @date ：Created in 2021/10/22 13:46
 * @description：这个类用于实现文章功能
 * @modified By：
 * @version: 1.0$
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
<<<<<<< HEAD
=======
    @Qualifier("restHighLevelClient")
    private RestHighLevelClient client;

    @Autowired
>>>>>>> master
    private TagService tagService;

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private BodyService bodyService;

    /**
    *  @param pageParams
    *  @return com.decucin.blog.vo.Result
    *  @author decucin
    *  @date 2021/10/25 12:07
    **/
    @Override
    public Result findAll(PageParams pageParams) {
        /**
         *  TODO 找到首页全部文章并以List<ArticleVo>形式返回(不含文章内容)
         *  @author decucin
         *  @date 2021/10/25 12:07
         **/
        Page<Article> page = new Page<>(pageParams.getPage(), pageParams.getPageSize());
        LambdaQueryWrapper<Article> queryWrapper= new LambdaQueryWrapper<>();
        queryWrapper.orderByDesc(Article::getWeight, Article::getCreateDate);
        Page<Article> articlePage = articleMapper.selectPage(page, queryWrapper);
        //  这里已经获取到了List，但需要再将其转化为vo对象即可
        List<Article> articleList = articlePage.getRecords();
        //  将其转化为vo对象
        List<ArticleVo> articles = copyList(articleList, true, true, false);
        return Result.success(articles);
    }


    /**
    *  @param categoryId
    *  @return com.decucin.blog.vo.Result
    *  @author decucin
    *  @date 2021/10/20 16:05
    **/
    @Override
    public Result findArticleByCategory(PageParams pageParams, Long categoryId) {
        /**
        *  TODO 根据标签id找到对应的文章列表并以List<ArticleVo>形式返回(不含文章内容)
        *  @author decucin
        *  @date 2021/10/20 16:07
        **/
        Page<Article> page = new Page<>(pageParams.getPage(), pageParams.getPageSize());
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Article::getCategoryId, categoryId);
        Page<Article> articlePage = articleMapper.selectPage(page, queryWrapper);
        List<Article> articleList = articlePage.getRecords();
        List<ArticleVo> articles = copyList(articleList, true, true,false);
        return Result.success(articles);
    }

    /**
    *  @param id
    *  @return com.decucin.blog.vo.Result
    *  @author decucin
    *  @date 2021/10/25 12:10
    **/
    @Override
    public Result findArticleInfo(Long id) {
        /**
         *  TODO 根据文章id找到对应的文章的详细信息并以ArticleVo形式返回(含文章内容)
         *  @author decucin
         *  @date 2021/10/20 16:35
         **/
        Article article = articleMapper.selectById(id);
        if(article != null) {
            LambdaUpdateWrapper<Article> updateWrapper = new LambdaUpdateWrapper<>();
            updateWrapper.eq(Article::getId, id).set(Article::getViewCount, article.getViewCount() + 1);
            articleMapper.update(null, updateWrapper);
            return Result.success(copy(article, true, true, true));
        }
        return Result.fail(404, "文章不存在！");
    }

    /**
<<<<<<< HEAD
    *  @param userId
=======
    *  @param token
>>>>>>> master
    *  @param articleId
    *  @return com.decucin.blog.vo.Result
    *  @author decucin
    *  @date 2021/10/25 12:11
    **/
    @Override
<<<<<<< HEAD
    public Result likeArticle(Long userId, Long articleId) {
        /**
         *  TODO 根据用户id以及文章id使该用户对此篇文章进行点赞
         *  @author decucin
         *  @date 2021/10/20 17:13
         **/
=======
    public Result likeArticle(String token, Long articleId) {
        /**
         *  TODO 根据token确定的用户id以及文章id使该用户对此篇文章进行点赞
         *  @author decucin
         *  @date 2021/10/20 17:13
         **/
        Long userId = (Long) JWTTokenUtils.getTokenBody(token).get("id");
>>>>>>> master
        Article article = articleMapper.selectById(articleId);
        if(article == null){
            return Result.fail(404, "文章不存在！");
        }
        Boolean ifLike = articleMapper.selectIfLike(userId, articleId);
        if(ifLike == null || !ifLike) {
            if(ifLike == null){
                // 进入这里表示还没有点赞（不存在点赞后又取消）
                // like_article表插入信息
                articleMapper.insertLike(userId, articleId);
            }else{
                // 这里表示表中已有信息但if_like为0（点赞后又取消）
                // like_article表修改信息
                articleMapper.updateLike(userId, articleId);
            }
            // 更新文章点赞数
            LambdaUpdateWrapper<Article> updateWrapper = new LambdaUpdateWrapper<>();
            updateWrapper.eq(Article::getId, articleId).set(Article::getLikeCount, article.getLikeCount() + 1);

            return Result.success(articleMapper.update(null, updateWrapper));
        }
        return Result.fail(300, "该文章已经点赞！");
    }

    /**
<<<<<<< HEAD
    *  @param userId
=======
    *  @param token
>>>>>>> master
    *  @param articleId
    *  @return com.decucin.blog.vo.Result
    *  @author decucin
    *  @date 2021/10/25 12:13
    **/
    @Override
<<<<<<< HEAD
    public Result notLikeArticle(Long userId, Long articleId) {
=======
    public Result notLikeArticle(String token, Long articleId) {
>>>>>>> master
        /**
         *  TODO 根据用户id以及文章id使该用户对此篇文章进行取消点赞
         *  @author decucin
         *  @date 2021/10/20 17:39
         **/
<<<<<<< HEAD
=======
        Long userId = (Long) JWTTokenUtils.getTokenBody(token).get("id");
>>>>>>> master
        Article article = articleMapper.selectById(articleId);
        if(article == null){
            return Result.fail(404, "文章不存在！");
        }
        Boolean ifLike = articleMapper.selectIfLike(userId, articleId);
        if(ifLike){
            // 这里表示用户确实已经点赞了
            // 首先将like_comment表中的ifLiKe置为0
            articleMapper.cancelLike(userId, articleId);
            // 写到这里忽然想到取消点赞如果设置为删除对应记录，那之前点赞的判断部分就不需要判断isLike是否为false（不过我这里还是使用的是更新记录）
            // 更新文章点赞数
            LambdaUpdateWrapper<Article> updateWrapper = new LambdaUpdateWrapper<>();
            updateWrapper.eq(Article::getId, articleId).set(Article::getLikeCount, article.getLikeCount() - 1);
            return Result.success(articleMapper.update(null, updateWrapper));
        }
        // 这里表示用户还没有点赞
        return Result.fail(301, "用户已经点赞了！");
    }

    /**
    *  @param articleId
    *  @return com.decucin.blog.vo.Result
    *  @author decucin
    *  @date 2021/10/25 12:14
    **/
    @Override
    public Result addCommentCount(Long articleId) {
        /**
         *  TODO 增加文章对应的评论数，与addComment共同使用
         *  @author decucin
         *  @date 2021/10/20 17:39
         **/
        Article article = articleMapper.selectById(articleId);
        if(article == null){
            return Result.fail(404, "文章不存在");
        }
        LambdaUpdateWrapper<Article> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(Article::getId, articleId).set(Article::getCommentCount, article.getCommentCount() + 1);
        return Result.success(articleMapper.update(null, updateWrapper));
    }

    /**
    *  @param articleId
    *  @return com.decucin.blog.vo.Result
    *  @author decucin
    *  @date 2021/10/25 12:17
    **/
    @Override
    public Result deleteCommentCount(Long articleId) {
        /**
         *  TODO 减少文章对应的评论数，与deleteComment共同使用
         *  @author decucin
         *  @date 2021/10/20 17:48
         **/
        Article article = articleMapper.selectById(articleId);
        if(article == null){
            return Result.fail(404, "文章不存在！");
        }
        LambdaUpdateWrapper<Article> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(Article::getId, articleId).set(Article::getCommentCount, article.getCommentCount() - 1);
        return Result.success(articleMapper.update(null, updateWrapper));
    }

<<<<<<< HEAD
=======
    /***
     * @description: elasticsearch搜索功能实现
     * @param key
     * @return: com.decucin.blog.vo.Result
     * @author: decucin
     * @date: 2021/12/9 20:27
     */
    @Override
    public Result search(String key) throws IOException {

        String index = "body";
        String field = "content";

        SearchRequest request = new SearchRequest();
        request.indices(index);
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.query(QueryBuilders.matchQuery(field, key).minimumShouldMatch("70%"));
        sourceBuilder.sort(new ScoreSortBuilder().order(SortOrder.DESC));  //DESC降序

        sourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));

        //高亮
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        HighlightBuilder.Field content =
                new HighlightBuilder.Field(field);

        content.highlighterType("unified");
        highlightBuilder.field(content);
        highlightBuilder.requireFieldMatch(Boolean.TRUE);
        highlightBuilder.preTags("<span style='color:red'>");
        highlightBuilder.postTags("</span>");
        sourceBuilder.highlighter(highlightBuilder);

        request.source(sourceBuilder);
        SearchResponse search = client.search(request, RequestOptions.DEFAULT);

        SearchHits hits = search.getHits();

        ArrayList<Map<String,Object>> list = new ArrayList<>();
        for (SearchHit hit : hits) {
            Map<String, HighlightField> highlightFields = hit.getHighlightFields();
            HighlightField title = highlightFields.get(field);
            Map<String,Object> sourceAsMap = hit.getSourceAsMap();  //没高亮的数据
            System.out.println(sourceAsMap);
            if(title != null){
                Text[] fragments = title.fragments();
                String n_title = "";
                for (Text text : fragments) {
                    n_title += text;
                }
                sourceAsMap.put(field,n_title);   //把高亮字段替换掉原本的内容即可
            }
            list.add(sourceAsMap);

        }
        return Result.success(list);
    }

>>>>>>> master
    /**
    *  @param articleList
    *  @param isTags
    *  @param isAuthor
    *  @param isBody
    *  @return java.util.List<com.decucin.blog.vo.ArticleVo>
    *  @author decucin
    *  @date 2021/10/25 12:17
    **/
    private List<ArticleVo> copyList(List<Article> articleList, boolean isTags, boolean isAuthor, boolean isBody) {
        /**
         *  TODO 根据是否具有标签信息、作者信息以及文章主体信息将articleList转化为articleVoList
         *  @author decucin
         *  @date 2021/10/20 17:56
         **/
        List<ArticleVo> articleVos = new ArrayList<>();
        for (Article article : articleList) {
            articleVos.add(copy(article, isTags, isAuthor, isBody));
        }
        return articleVos;
    }

    /**
    *  @param article
    *  @param isTags
    *  @param isAuthor
    *  @param isBody
    *  @return com.decucin.blog.vo.ArticleVo
    *  @author decucin
    *  @date 2021/10/25 12:19
    **/
    private ArticleVo copy(Article article, boolean isTags, boolean isAuthor, boolean isBody){
        /**
         *  TODO 根据是否具有标签信息、作者信息以及文章主体信息将article转化为articleVo
         *  @author decucin
         *  @date 2021/10/20 18:23
         **/
        ArticleVo articleVo = new ArticleVo();
        BeanUtils.copyProperties(article, articleVo);
        if(isTags){
            Long articleId = article.getId();
            articleVo.setTags(tagService.findTagsByArticleId(articleId));
        }
        if(isAuthor){
            Long authorId = article.getAuthorId();
            articleVo.setAuthor(sysUserService.findUserById(authorId).getNickname());
        }
        if(isBody){
            Long bodyId = article.getBodyId();
            articleVo.setBody(bodyService.findBodyById(bodyId));
        }
        return articleVo;
    }
}
