package resources;

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

import dao.EditorDao;
import entity.Editor;

import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;


@Stateless
@Path("editor")
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class EditorResource {
	
	
	
	public EditorResource() {
		super();
	}

	@EJB
	EditorDao editorDao;
	
	@GET
    public JsonArray findall() {		
		JsonArrayBuilder jsonList = Json.createArrayBuilder();
		List<Editor> list = editorDao.findAll();
		
		for(Editor editor : list) {
			jsonList.add(editor.toJson());
		}	
		return jsonList.build();	
    }
    

    //TESTED - WORKING
    @GET
    @Path("{id}")
    public JsonObject findById(@PathParam("id") Integer id) {
        Editor editor = this.editorDao.findById(id);
        return editor.toJson();
    }
    
    
    //TESTED - WORKING
    @POST
    public Response create(@Valid Editor editor) {
    	
    	if(editor !=null) {
    		System.out.println("editorDao called from POST");
    		this.editorDao.create(editor);
    	}
        
        //return Response.ok().header("Access-Control-Allow-Origin", "*").build();
    	return Response.ok().build();
    }
    
    //TESTED - WORKING
    @PUT
    @Path("{id}")
    public Response update(@PathParam("id") final Integer id, @Valid Editor editor) {
    	editor.setEditorId(id);
        this.editorDao.update(editor);

        return Response.accepted().build();
    }

    //TESTED-WORKING
    @DELETE
    @Path("{id}")
    public Response remove(@PathParam("id") final Integer id) {
    	Editor editor= this.editorDao.findById(id);
        this.editorDao.delete(editor);

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
