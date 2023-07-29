package com.example.demofb.DAO;

import com.example.demofb.Config.DatabaseConfiguration;
import com.example.demofb.DTO.PostDTO;
import com.example.demofb.DTO.UserDTO;
import com.mysql.cj.jdbc.Driver;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class PostDAO {
    private Connection connection;
    public void connect() throws SQLException {
        Driver driver = new Driver();
        DatabaseConfiguration databaseConfiguration = new DatabaseConfiguration();
        Properties properties = new Properties();
        properties.setProperty("user", databaseConfiguration.getUser());
        properties.setProperty("password", databaseConfiguration.getPassword());
        if (connection==null||connection.isClosed()){
            connection = driver.connect(databaseConfiguration.getUrl(), properties);
        }
    }

    public void savePost(String postContent, Long userId) throws SQLException {
        System.out.println("this is the content: "+postContent+" and user id: "+userId);
        connect();
        String query ="insert into Demo_FB.posts (content, user_id) values (?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, postContent);
        preparedStatement.setLong(2, userId);
        preparedStatement.execute();
        connection.close();
    }

    public List<PostDTO> findAllPostsByUser(Long userId) throws SQLException {
        connect();
        String query = "select * from posts where " +
                "user_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setLong(1, userId);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<PostDTO> postDTOList = new ArrayList<>();
        while (resultSet.next()){
            PostDTO postDTO = PostDTO.builder()
                    .id(resultSet.getLong(1))
                    .content(resultSet.getString(2))
                    .build();
            postDTOList.add(postDTO);
        }
        return postDTOList;
    }


    public void updateUserPost(Long postId, String updateContent) throws SQLException {
        connect();
        String query = "update posts set content = ? where " +
                "posts.id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, updateContent);
        preparedStatement.setLong(2, postId);
        preparedStatement.executeUpdate();
    }


    public boolean deleteUserPost(Long postId) throws SQLException {
        connect();
        String query = "delete from posts where id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setLong(1, postId);
        return preparedStatement.execute();
    }

    public PostDTO findById(Long postId) throws SQLException {
        connect();
        String query = "select * from posts where " +
                "id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setLong(1, postId);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()){
            return PostDTO.builder()
                    .id(resultSet.getLong(1))
                    .content(resultSet.getString(2))
                    .build();
        }
        return null;
    }
}
