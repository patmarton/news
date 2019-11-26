package resources;

import java.nio.charset.StandardCharsets;
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

import dao.AdminDao;
import entity.Admin;
import entity.Editor;
import security.SecuritySHA256;

import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;


@Stateless
@Path("admin")
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class AdminResource {
	
	
	
	public AdminResource() {
		super();
	}

	@EJB
	AdminDao adminDao;
	
	@GET
    public JsonArray findall() {		
		JsonArrayBuilder jsonList = Json.createArrayBuilder();
		List<Admin> list = adminDao.findAll();
		
		for(Admin admin : list) {
			jsonList.add(admin.toJson());
		}	
		return jsonList.build();	
    }
    

    //TESTED - WORKING
    @GET
    @Path("{id}")
    public JsonObject findById(@PathParam("id") Integer id) {
        Admin admin = this.adminDao.findById(id);
        return admin.toJson();
    }
    
    
    //TESTED - WORKING
    @POST
    public Response create(@Valid Admin admin) {
    	
    	if(admin !=null) {
    		System.out.println("adminDao called from POST");
    		final SecuritySHA256 hasher = new SecuritySHA256();
    		admin.setPassword(hasher.hashPassword(admin.getPassword()));
    		this.adminDao.create(admin);
    	}
        
        //return Response.ok().header("Access-Control-Allow-Origin", "*").build();
    	return Response.ok().build();
    }
    
    //TESTED - WORKING
    @PUT
    @Path("{id}")
    public Response update(@PathParam("id") final Integer id, @Valid Admin admin) {
    	admin.setEditorId(id);
        this.adminDao.update(admin);

        return Response.accepted().build();
    }

    //TESTED-WORKING
    @DELETE
    @Path("{id}")
    public Response remove(@PathParam("id") final Integer id) {
    	Admin admin= this.adminDao.findById(id);
        this.adminDao.delete(admin);

        return Response.ok().build();
    }
    
    
    @POST
    @Path("authentication")
    public Response authenticate(@Valid Admin admin) {
    	
    	//Hashing pasword
    	final String hashed = Hashing.sha256()
		        .hashString(admin.getPassword(), StandardCharsets.UTF_8)
		        .toString();
    	
    	//Getting users
    	List<Admin> admins = adminDao.findAll();
    	
    	//Iterating through users
    	for(Admin u : admins) {
    		if(u.getName().equals(admin.getName()) && u.getPassword().equals(hashed)) {
    			return Response.ok().build();
    		}
    	}
    	
    	return Response.status(401)
    				   .build();
    	
    }
    
    
    /*@OPTIONS
    public Response getOptions() {
      return Response.ok()
        .header("Access-Control-Allow-Origin", "*")
        .header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS")
        .header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With").build();
    }*/


	
 
}
