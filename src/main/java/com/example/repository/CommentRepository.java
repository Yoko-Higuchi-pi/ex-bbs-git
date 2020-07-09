package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Article;
import com.example.domain.Comment;

/**
 * commentsテーブルを操作するリポジトリ.
 * 
 * @author kaoru.shibata
 *
 */
@Repository
public class CommentRepository {
	private static final RowMapper<Comment> COMMENT_ROW_MAPPER = new BeanPropertyRowMapper<>(Comment.class);
	@Autowired
	private NamedParameterJdbcTemplate template;

	/**
	 * 記事IDでコメントを検索、取得.
	 * 
	 * @param id 記事ID
	 * @return 記事IDに紐付けられたコメント一覧（List型）
	 */
	public List<Comment> findByArticleId(Article id) {
		String sql = "SELECT id,name,content,article_id FROM commens WHERE article_id= :id;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("articleId", id);
		List<Comment> comment = template.query(sql, param, COMMENT_ROW_MAPPER);
		return comment;
	}

	/**
	 * コメントを挿入.
	 * 
	 * @param comment コメント情報
	 */
	public void insert(Comment comment) {
		String sql = "INSERT INTO comments(name, content,article_id) VALUES(:name,:content,:articleId);";
		SqlParameterSource param = new MapSqlParameterSource().addValue("name", comment.getName())
				.addValue("content", comment.getContent()).addValue("articleId", comment.getArticleId());
		template.update(sql, param);
	}

	/**
	 * 記事IDに紐づいているコメントを削除.
	 * 
	 * @param articleId 記事ID
	 * 
	 */
	public void deleteByArtcleId(Integer articleId) {
		String sql = "DELETE FROM comments WHERE article_id=:articleId;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("articleId", articleId);
		template.update(sql, param);
	}

}
