package com.example.domain;

/**
 * 投稿情報を保持するドメインクラス.
 * 
 * @author yoko.higuchi
 *
 */
public class Article {
	/** 主キー(ID) */
	private Integer id;
	/** 投稿者名 */
	private String name;
	/** 投稿内容 */
	private String comment;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	@Override
	public String toString() {
		return "Article [name=" + name + ", comment=" + comment + "]";
	}
}
