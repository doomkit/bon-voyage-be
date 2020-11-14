package ctu.via.bonvoyage.interfaces;

import ctu.via.bonvoyage.interfaces.response.JwtResponse;
import org.springframework.security.core.GrantedAuthority;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;

public class UserObject {

    @NotNull
    @Size(max = 50)
    private String name;

    @NotNull
    @Size(max = 50)
    private String surname;

    @NotNull
    @Size(max = 50)
    private String email;

    @NotNull
    private String password;

    private JwtResponse jwtResponse;
    
    private Collection<? extends GrantedAuthority> grantedAuthorities;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
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

    public JwtResponse getJwtResponse() {
        return jwtResponse;
    }

    public void setJwtResponse(JwtResponse jwtResponse) {
        this.jwtResponse = jwtResponse;
    }

    public Collection<? extends GrantedAuthority> getGrantedAuthorities() {
        return grantedAuthorities;
    }

    public void setGrantedAuthorities(Collection<? extends GrantedAuthority> grantedAuthorities) {
        this.grantedAuthorities = grantedAuthorities;
    }

}
