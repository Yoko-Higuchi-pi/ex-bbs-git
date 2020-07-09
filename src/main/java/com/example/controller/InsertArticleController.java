package com.example.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Article;
import com.example.form.ArticleForm;
import com.example.repository.ArticleRepository;

/**
 * 投稿内容を登録を行うコントローラクラス.
 * 
 * @author yoko.higuchi
 */
@Controller
@RequestMapping("/article")
public class InsertArticleController {
	@Autowired
	private ArticleRepository articleRepository;
	
	/**
	 * 登録する投稿内容を保持するフォーム.
	 * 
	 * @return フォーム
	 */
	private ArticleForm setUpArticleForm() {
		return new ArticleForm();
	}
	
	
	/**
	 * 投稿内容を登録する.
	 * 
	 * @param form フォーム
	 * @param model モデル
	 * @return 投稿一覧画面
	 */
	@RequestMapping("/insert")
	public String insert(ArticleForm form) {
		Article article = new Article();
		BeanUtils.copyProperties(form, article);
		articleRepository.insert(article);
		
		return "redirect:/";
	}
}
