package resources;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.validation.Valid;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import dao.ArticleDao;
import entity.Article;

import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;


@Stateless
@Path("article")
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class ArticleResource {
	
	
	
	public ArticleResource() {
		super();
	}

	@EJB
	ArticleDao articleDao;
	
	@GET
    public JsonArray findall() {		
		JsonArrayBuilder jsonList = Json.createArrayBuilder();
		List<Article> list = articleDao.findAll();
		Date curDate = new Date();
		for(Article article : list) {
			if((article.getDate().getTime()-curDate.getTime())>0)
			jsonList.add(article.toJson());
		}	
		return jsonList.build();	
    }
	
	@GET
	@Path("/main")
    public JsonArray findallMain() {		
		JsonArrayBuilder jsonList = Json.createArrayBuilder();
		List<Article> list = articleDao.findAll();
		
		for(Article article : list) {
			if(article.isMain_article()== true)
			jsonList.add(article.toJson());
		}	
		return jsonList.build();	
    }
	


    //TESTED - WORKING
    @GET
    @Path("{id}")
    public JsonObject findById(@PathParam("id") Integer id) {
        Article article = this.articleDao.findById(id);
        return article.toJson();
    }
    
    
    //TESTED - WORKING
    @POST
    public Response create(@Valid Article article) {
    	
    	if(article !=null) {
    		System.out.println("articleDao called from POST");
    		this.articleDao.create(article);
    	}
        
        //return Response.ok().header("Access-Control-Allow-Origin", "*").build();
    	return Response.ok().build();
    }
    
    //TESTED - WORKING
    @PUT
    @Path("{id}")
    public Response update(@PathParam("id") final Integer id, @Valid Article article) {
    	article.setArticleId(id);
        this.articleDao.update(article);

        return Response.accepted().build();
    }

    //TESTED-WORKING
    @DELETE
    @Path("{id}")
    public Response remove(@PathParam("id") final Integer id) {
    	Article article= this.articleDao.findById(id);
        this.articleDao.delete(article);

        return Response.ok().build();
    }
    
    
    /*@OPTIONS
    public Response getOptions() {
      return Response.ok()
        .header("Access-Control-Allow-Origin", "*")
        .header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS")
        .header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With").build();
    }*/


	
 
}
