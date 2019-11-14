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
import javax.persistence.Lob;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;


@Entity
@Table(name = "Editor")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Editor {
	
	
	@Id
	@GeneratedValue()
	protected Integer editorId;
	
	@Column(name="name")
	private String name;
	
	@Column(name="email")
	private String email;
	
	@Column(name="password")
	private String password;
	
	@OneToMany(mappedBy = "editor")
	private List<Article> articles;
	


	//Json Convereter
	
	public JsonObject toJson() {
	    return Json.createObjectBuilder()
	    		.add("id", this.editorId)
	            .add("name", this.name)
	            .add("email", this.email)
	            .add("password", this.password)
	            .build();
	}
	
	
	//Constructors
	
	public Editor(Integer editorId, String name, String email, String password, List<Article> articles) {
		super();
		this.editorId = editorId;
		this.name = name;
		this.email = email;
		this.password = password;
		this.articles = articles;
	}

	

	
	public Editor() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	//Accessors


	public Integer getEditorId() {
		return editorId;
	}




	public void setEditorId(Integer editorId) {
		this.editorId = editorId;
	}




	public String getName() {
		return name;
	}




	public void setName(String name) {
		this.name = name;
	}




	public String getEmail() {
		return email;
	}




	public void setEmail(String email) {
		this.email = email;
	}




	public String getPassword() {
		return password;
	}




	public void setPassword(String password) {
		this.password = password;
	}




	public List<Article> getArticles() {
		return articles;
	}




	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}
	
	
	
	
	
	
	
	
	

}
