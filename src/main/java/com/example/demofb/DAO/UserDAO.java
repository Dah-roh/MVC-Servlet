package com.example.demofb.DAO;

import com.example.demofb.DTO.UserDTO;
import com.example.demofb.Enums.Role;
import com.mysql.cj.jdbc.Driver;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class UserDAO {
private Connection connection;
    public void connect() throws SQLException {
        Driver driver = new Driver();
        Properties properties = new Properties();
        properties.setProperty("user", "root");
        properties.setProperty("password", "password");
//        Class.forName("com.mysql.cj.jdbc.Driver");
        if (connection==null||connection.isClosed()){
            connection = driver.connect("jdbc:mysql://localhost:3306/Demo_FB", properties);
        }
    }

    public void saveUser(UserDTO userDTO) throws SQLException, ClassNotFoundException {
        connect();
        String password = BCrypt.hashpw(userDTO.getPassword(), BCrypt.gensalt());
        String query ="insert into Demo_FB.users (email, password, firstname, lastname, role) values (?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, userDTO.getEmail());
        preparedStatement.setString(2, password);
        preparedStatement.setString(3, userDTO.getFirstname());
        preparedStatement.setString(4, userDTO.getLastname());
        preparedStatement.setString(5, userDTO.getRole().toString());
        preparedStatement.execute();
        connection.close();
    }

    public UserDTO findUser (String email, String password) throws SQLException, ClassNotFoundException {
        connect();
        String query = "select * from users where " +
                "email=?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, email);
        ResultSet resultSet = preparedStatement.executeQuery();
        UserDTO userDTO;
        if(resultSet.next() && BCrypt.checkpw(password, resultSet.getString(3))){
            userDTO =  UserDTO.builder()
                    .id(resultSet.getLong(1))
                    .email(resultSet.getString(2))
                    .firstname(resultSet.getString(4))
                    .lastname(resultSet.getString(5)).build();
            connection.close();
            return userDTO;
        }
        connection.close();
        return null;
    }

    public List<UserDTO> findAllUsers() throws SQLException, ClassNotFoundException {
        connect();
        String query = "select * from users";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        UserDTO userDTO;
        List<UserDTO> userDTOList = new ArrayList<>();
        while(resultSet.next()){
            userDTO =  UserDTO.builder()
                    .id(resultSet.getLong(1))
                    .email(resultSet.getString(2))
                    .firstname(resultSet.getString(4))
                    .lastname(resultSet.getString(5))
                    .role(Role.valueOf(resultSet.getString(6))).build();
            userDTOList.add(userDTO);
        }
        return userDTOList;
    }
}