package com.mustache.bbs2.controller;

import com.mustache.bbs2.domain.dto.ArticleDto;
import com.mustache.bbs2.domain.entity.Article;
import com.mustache.bbs2.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/articles")
@Slf4j
public class ArticleController {
// spring이 ArticleRepository구현체를 DI(인터페이스 아님)
// ArticleRepository는 Interface이지만 그 구현체(ArticleDao)를 SpringBoot가 넣어준다.

    private final ArticleRepository articleRepository;

    public ArticleController(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @GetMapping("/list")
    public String list(Model model){
        List<Article> articles = articleRepository.findAll();
        model.addAttribute("articles", articles);
        return "articles/list";
    }

    @GetMapping("")
    public String index(){
        return "redirect:/articles/list";
    }

    @GetMapping(value = "/new")
    public String newArticleForm(){
        return "articles/new";
    }

    @GetMapping("/{id}")
    public String selectSingle(@PathVariable Long id, Model model){
        Optional<Article> optional = articleRepository.findById(id);

        if (!optional.isEmpty()){
            //Optional.get() --> Article
            model.addAttribute("article", optional.get());
            return "articles/show";
        }else {
            return "articles/error";
        }
    }

    @PostMapping(value = "/posts")
    public String createArticle(ArticleDto articleDto){
        // 실무에서 println 안씀 로그를 쓴다(서버에서 일어나는 일을 기록하는것)
        log.info(articleDto.getTitle());
        Article savedArticle = articleRepository.save(articleDto.toEntity());
        log.info("generatedId:{}", savedArticle.getId());
        // souf %d %s
        return String.format("redirect:/articles/%d", savedArticle.getId());

    }

}