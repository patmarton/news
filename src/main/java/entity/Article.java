package entity;

import java.util.Date;
import java.util.List;

import javax.json.Json;
import javax.json.JsonObject;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;


@Entity
@Table(name = "Article")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Article {
	
	
	@Id
	@GeneratedValue()
	private Integer articleId;
	
	@Column(name="title")
	private String title;
	
	@Column(name="content")
	private String content;
	
	@Column(name="category")
	private String category;
	
	@Lob
	@Column(name="picture")
	private byte[] picture;
	
	@Column(name="date_valid")
	private Date date;
	
	@Column(name="is_main")
	private boolean main_article;
	
	@ManyToOne
	@JoinColumn(name="editorId")
	private Editor editor;
	
	
	
	//Json Convereter
	
	public JsonObject toJson() {
	    return Json.createObjectBuilder()
	    		.add("id", this.articleId)
	            .add("title", this.title)
	            .add("content", this.content)
	            .add("category", this.category)
	            .add("picture", this.picture.toString())
	            .add("date", this.date.toString())
	            .add("main_article", this.main_article)
	            .build();
	}
	
	
	public Article(Integer articleId, String title, String content, String category, byte[] picture, Date date,
			boolean main_article, Editor editor) {
		super();
		this.articleId = articleId;
		this.title = title;
		this.content = content;
		this.category = category;
		this.picture = picture;
		this.date = date;
		this.main_article = main_article;
		this.editor = editor;
	}

	public Article() {
		super();

	}

	//Accessors

	public Integer getArticleId() {
		return articleId;
	}

	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public byte[] getPicture() {
		return picture;
	}

	public void setPicture(byte[] picture) {
		this.picture = picture;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}


	public boolean isMain_article() {
		return main_article;
	}


	public void setMain_article(boolean main_article) {
		this.main_article = main_article;
	}


	public Editor getEditor() {
		return editor;
	}


	public void setEditor(Editor editor) {
		this.editor = editor;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
