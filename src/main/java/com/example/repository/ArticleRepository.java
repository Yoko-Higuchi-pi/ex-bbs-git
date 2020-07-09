package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Article;

/**
 * 投稿情報をDBとやり取りするリポジトリクラス.
 * 
 * @author yoko.higuchi
 *
 */
@Repository
public class ArticleRepository {
	private static final RowMapper<Article> ARTICLE_ROW_MAPPER = new BeanPropertyRowMapper<>(Article.class);
	
	@Autowired
	private NamedParameterJdbcTemplate template;
	
	/**
	 * 投稿情報をすべて抽出する.
	 * 
	 * @return 投稿情報
	 */
	public List<Article> findAll() {
		String sql = "SELECT id, name, content FROM articles;";
		
		return template.query(sql, ARTICLE_ROW_MAPPER);
	}
	
	/**
	 * 投稿情報を新規登録する.
	 * 
	 * @param article 登録情報
	 */
	public void insert(Article article) {
		String sql = "INSERT INTO articles(name, content) VALUES(:name, :content);";
		SqlParameterSource param = new BeanPropertySqlParameterSource(article);
		
		template.update(sql, param);
	}
	
	/**
	 * 投稿情報を削除する.
	 * 
	 * @param id 主キー(ID)
	 */
	public void deleteById(Integer id) {
		String sql = "DELETE FROM articles WHERE id = :id;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		
		template.update(sql, param);
	}
}
