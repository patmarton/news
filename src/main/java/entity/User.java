package entity;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
@Table(name = "Userdata")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class User {
	
	
	@Id
	@GeneratedValue()
	protected Integer userId;
	
	@Column(name="name")
	private String name;
	
	@Column(name="email")
	private String email;
	
	@Column(name="password")
	private String password;
	


	//Json Convereter
	
	public JsonObject toJson() {
	    return Json.createObjectBuilder()
	    		.add("id", this.userId)
	            .add("name", this.name)
	            .add("email", this.email)
	            .add("password", this.password)
	            .build();
	}
	
	
	//Constructors
	
	public User(Integer userId, String name, String email, String password, List<Article> articles) throws NoSuchAlgorithmException {
		super();
		this.userId = userId;
		this.name = name;
		this.email = email;
		this.password = password;	
		
	}

	

	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	//Accessors


	public Integer getUserId() {
		return userId;
	}


	public void setUserId(Integer userId) {
		this.userId = userId;
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

	




	
	
	
	
	
	
	

}
