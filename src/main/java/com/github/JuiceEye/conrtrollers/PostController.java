package com.github.JuiceEye.conrtrollers;

import com.github.JuiceEye.config.DatabaseUtils;
import com.github.JuiceEye.models.Post;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;

import java.sql.SQLException;

public interface PostController extends Controller {
    default Dao<Post, Integer> postDao() throws SQLException {
        return DaoManager.createDao(DatabaseUtils.CONNECTION_SOURCE, Post.class);
    }
}