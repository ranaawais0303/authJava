package com.awais.myauth.payloads;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {

    private int id;

    @NotEmpty
    @Size(min=4,message = "Username must be min of 4 characters")
    private String name;

    @Email(message = "Email address is not valid!!")
    private String email;

    @NotEmpty
    @Size(min = 3, max=10,message = "password must be min 3 chars and max of 10 chars")
    private String password;


    ////for ignore password for frontend getter

    @JsonIgnore
    public String getPassword(){
        return this.password;
    }
}
