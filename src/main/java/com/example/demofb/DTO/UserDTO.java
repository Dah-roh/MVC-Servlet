package com.example.demofb.DTO;

import com.example.demofb.Enums.Role;
import com.example.demofb.Models.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.sql.ResultSet;
import java.sql.SQLException;

@Data
@AllArgsConstructor
@Builder
public class UserDTO {
    private Long id;
    private String firstname;
    private String lastname;
    private String email;

    private Role role;

    private String password;

    public UserDTO(User user){
        this.firstname = user.getFirstname();
        this.lastname =  user.getLastname();
        this.email = user.getPassword();
        this.password = user.getPassword();
    }

    public UserDTO(ResultSet resultSet) throws SQLException {

        this.email = resultSet.getString(1);
        this.password = resultSet.getString(1);
        this.firstname = resultSet.getString(1);
        this.lastname =  resultSet.getString(1);
    }
}
