package security;

import java.io.IOException;
import java.util.List;
import java.util.StringTokenizer;

import javax.ejb.EJB;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;

import dao.AdminDao;
import entity.Admin;
//import org.glassfish.jersey.internal.util.Base64;

public class SecurityFilter implements ContainerRequestFilter{

	private static final String AUTHORIZATION_HEADER_KEY = "Authorization";
	private static final String AUTHORIZATION_HEADER_PREFIX = "Basic";
	
	@EJB
	AdminDao adminDao;
	
	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		// TODO Auto-generated method stub
		List<String> authHeader = requestContext.getHeaders().get(AUTHORIZATION_HEADER_KEY);
		
		if(authHeader.size() > 0) {
			String authToken = authHeader.get(0);
			authToken = authToken.replaceFirst(AUTHORIZATION_HEADER_PREFIX, "");
			
			byte[] decodedBytes = java.util.Base64.getDecoder().decode(authToken);
			String decodedString = new String(decodedBytes);
			StringTokenizer tokenizer = new StringTokenizer(decodedString,":");
			String username = tokenizer.nextToken();
			String password = tokenizer.nextToken();
			
			List<Admin> users = adminDao.findAll();
			for(Admin u : users) {
				if(u.getName().equals(username) && u.getPassword().equals(password))
					return;
			}
			
		}
		
		Response unauthorizedStatus = Response
				.status(Response.Status.UNAUTHORIZED)
				.entity("User cannot access this page!")
				.build();
		requestContext.abortWith(unauthorizedStatus);
	}

}
