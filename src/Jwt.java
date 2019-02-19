import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

public class Jwt {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		/*
		try {
		    Algorithm algorithm = Algorithm.HMAC256("secret");
		    System.out.println("[Jwt.java] algorithm: " + algorithm);
		    
		    String token = JWT.create().withIssuer("auth0").sign(algorithm);
		    System.out.println("[Jwt.java] token: " + token);
		    
		} catch (JWTCreationException exception){
		    // Invalid Signing configuration / Couldn't convert Claims.
		}
		*/
		
		Person person = new Person("guest", "0000");
		System.out.println("[Jwt.java] person: " + person);
		
		Algorithm algorithm = Algorithm.HMAC256("secret");
		System.out.println("[Jwt.java] algorithm: " + algorithm);
		
		String token = JWT.create().withClaim("id", person.getId()).withClaim("password", person.getPassword()).withIssuer("auth0").sign(algorithm);
		System.out.println("[Jwt.java] token: " + token);
		
		JWTVerifier verifier = JWT.require(algorithm).withIssuer("auth0").build(); // Reusable verifier instance
		DecodedJWT jwt = verifier.verify(token);
		
		System.out.println("[Jwt.java] id: " + jwt.getClaim("id").asString() + " / password : " + jwt.getClaim("password").asString());
	}

}
