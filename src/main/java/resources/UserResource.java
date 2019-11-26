package resources;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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

import com.google.common.hash.Hashing;

import dao.UserDao;
import entity.User;
import security.SecuritySHA256;

import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;


@Stateless
@Path("user")
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class UserResource {
	
	
	
	public UserResource() {
		super();
	}

	@EJB
	UserDao userDao;
	
	@GET
    public JsonArray findall() {		
		JsonArrayBuilder jsonList = Json.createArrayBuilder();
		List<User> list = userDao.findAll();
		
		for(User user : list) {
			jsonList.add(user.toJson());
		}	
		return jsonList.build();	
    }
    

    //TESTED - WORKING
    @GET
    @Path("{id}")
    public JsonObject findById(@PathParam("id") Integer id) {
        User user = this.userDao.findById(id);
        return user.toJson();
    }
    
    //TESTED - WORKING
    @POST
    @Path("authentication")
    public Response authenticate(@Valid User user) {
    	
    	//Hashing pasword
    	final String hashed = Hashing.sha256()
		        .hashString(user.getPassword(), StandardCharsets.UTF_8)
		        .toString();
    	
    	//Getting users
    	List<User> users = userDao.findAll();
    	
    	//Iterating through users
    	for(User u : users) {
    		if(u.getName().equals(user.getName()) && u.getPassword().equals(hashed)) {
    			return Response.ok().build();
    		}
    	}
    	
    	return Response.status(401)
    				   .build();
    	
    }
    
    
    //TESTED - WORKING
    @POST
    public Response create(@Valid User user) throws NoSuchAlgorithmException {
    	
    	if(user !=null) {
    		System.out.println("userDao called from POST");
    		
    		final SecuritySHA256 hasher = new SecuritySHA256();
    		user.setPassword(hasher.hashPassword(user.getPassword()));
    		
    		this.userDao.create(user);
    	}
        
        //return Response.ok().header("Access-Control-Allow-Origin", "*").build();
    	return Response.ok().build();
    }
    
    //TESTED - WORKING
    @PUT
    @Path("{id}")
    public Response update(@PathParam("id") final Integer id, @Valid User user) {
    	user.setUserId(id);
        this.userDao.update(user);

        return Response.accepted().build();
    }

    //TESTED-WORKING
    @DELETE
    @Path("{id}")
    public Response remove(@PathParam("id") final Integer id) {
    	User user= this.userDao.findById(id);
        this.userDao.delete(user);

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
