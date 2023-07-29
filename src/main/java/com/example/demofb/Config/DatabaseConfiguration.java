package com.example.demofb.Config;

import lombok.Data;

@Data
public class DatabaseConfiguration {
    private final String url = "jdbc:mysql://localhost:3306/Demo_FB";
    private final String user = "root";
    private final String password = "password";
}
