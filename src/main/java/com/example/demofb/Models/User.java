package com.example.demofb.Models;

import com.example.demofb.DAO.UserDAO;
import com.example.demofb.DTO.UserDTO;
import com.example.demofb.Enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class User {
    private Long id;
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private Role userRole;

}
