package security;

import java.nio.charset.StandardCharsets;

import com.google.common.hash.Hashing;

public class SecuritySHA256 {
	
	public String hashPassword(String password) {
		
		final String hashed = Hashing.sha256()
		        .hashString(password, StandardCharsets.UTF_8)
		        .toString();
		
		return hashed;
		
	}

}
