package com.github.JuiceEye.config;

import com.github.JuiceEye.models.Post;
import com.github.JuiceEye.models.User;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

public class DatabaseUtils {


    public static String JDBC_CONNECTION_STRING = "jdbc:sqlite:C:\\Users\\Администратор\\Desktop\\database\\murzilkaDatabase.sqlite";

    public static ConnectionSource CONNECTION_SOURCE;

    static {
        try {
            CONNECTION_SOURCE = new JdbcConnectionSource(JDBC_CONNECTION_STRING);
            TableUtils.createTableIfNotExists(CONNECTION_SOURCE, User.class);
            TableUtils.createTableIfNotExists(CONNECTION_SOURCE, Post.class);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
