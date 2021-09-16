package ch.zli.m223.punchclock.service;

import java.time.Duration;
import java.util.Arrays;
import java.util.HashSet;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import ch.zli.m223.punchclock.domain.EntryUser;
import io.smallrye.jwt.build.Jwt;
import org.eclipse.microprofile.jwt.Claims;

@RequestScoped
public class AuthenticationService {
    @Inject
    EntityManager entityManager;

    public boolean userExists(EntryUser entryUser) {
        var query = entityManager.createQuery("SELECT COUNT(*) FROM EntryUser WHERE username = :name AND password = :password");
        query.setParameter("name", entryUser.getUsername());
        query.setParameter("password", entryUser.getPassword());
        var result = query.getSingleResult();

        return (Long)result == 1;
    }
    public String GenerateValidJwtToken(String username){
        String token =
            Jwt.issuer("https://zli.ch/issuer") 
            .upn(username) 
            .groups(new HashSet<>(Arrays.asList("User", "Admin"))) 
            .claim(Claims.birthdate.name(), "2001-07-13")
            .expiresIn(Duration.ofHours(1)) 
            .sign();
        return token;
    }

}
