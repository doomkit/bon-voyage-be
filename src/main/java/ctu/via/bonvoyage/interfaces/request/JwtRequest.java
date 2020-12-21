package ctu.via.bonvoyage.interfaces.request;

import io.swagger.annotations.ApiParam;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class JwtRequest implements Serializable {

    private static final long serialVersionUID = 5926468583005150707L;
    @NotNull
    private String email;
    @NotNull
    private String password;

    public JwtRequest() {}

    public JwtRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    @ApiParam(required = true)
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    @ApiParam(required = true)
    public void setPassword(String password) {
        this.password = password;
    }

}
