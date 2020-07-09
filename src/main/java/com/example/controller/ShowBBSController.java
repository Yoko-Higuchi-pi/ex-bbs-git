package com.example.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Article;
import com.example.form.ArticleForm;
import com.example.repository.ArticleRepository;

/**
 * 投稿情報を管理するコントローラクラス.
 * 
 * @author yoko.higuchi
 *
 */
@Controller
@RequestMapping("")
public class ShowBBSController {
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
	 * 投稿画面を表示する.
	 * 
	 * @param model モデル
	 * @return 投稿画面
	 */
	@RequestMapping("")
	public String index(Model model) {
		List<Article> articleList = articleRepository.findAll();
		// TODO : commentListに追加
		
		model.addAllAttributes(articleList);
		
		return "article-list";
	}
}
