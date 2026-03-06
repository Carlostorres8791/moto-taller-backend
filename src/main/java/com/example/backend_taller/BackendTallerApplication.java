package com.example.backend_taller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.SignatureAlgorithm;
import java.security.Key;
import java.util.Base64;



@SpringBootApplication
public class BackendTallerApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendTallerApplication.class, args);

         /*BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

       System.out.println("=================================================================");
        System.out.println(encoder.encode("12345"));*/

        /*Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
        String base64Key = Base64.getEncoder().encodeToString(key.getEncoded());
        System.out.println(base64Key);*/
	}
}
