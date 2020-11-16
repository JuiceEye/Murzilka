package com.github.JuiceEye.services;

import com.github.JuiceEye.config.DatabaseUtils;
import com.github.JuiceEye.models.Post;
import com.github.JuiceEye.conrtrollers.PostController;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import io.javalin.http.Context;

import java.sql.SQLException;
import java.util.List;

public class PostService implements PostController, Handler {

    Dao<Post, Integer> postDao;

    {
        try {
            postDao = DaoManager.createDao(DatabaseUtils.CONNECTION_SOURCE, Post.class);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void create(Object objectPost) throws SQLException {
        Post post = (Post) objectPost;
        postDao().create(post);
        System.out.println("Created post " + post);
    }

    @Override
    public Post read(int id) throws SQLException {
        Post post = postDao().queryForId(id);
        System.out.println(post);
        return post;
    }

    @Override
    public void update(Object objectPost) throws SQLException {
        Post post = (Post) objectPost;
        postDao().update(post);
        System.out.println("Updated post " + post);
    }

    @Override
    public void delete(int id) throws SQLException {
        Post post = postDao().queryForId(id);
        System.out.println("Deleted " + post);
        postDao().delete(post);
    }

    @Override
    public List<?> getAll() throws SQLException {
        return postDao().queryForAll();
    }

    public boolean isVipPost(Context context) throws SQLException {
        boolean isVip = new PostService().read(Integer.parseInt(context.pathParam("postId"))).getIsVip();
        return isVip;
    }
}
