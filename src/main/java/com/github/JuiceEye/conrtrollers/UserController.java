package com.github.JuiceEye.conrtrollers;

import com.github.JuiceEye.config.DatabaseUtils;
import com.github.JuiceEye.models.User;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;

import java.sql.SQLException;

public interface UserController extends Controller {
    default Dao<User, Integer> userDao() throws SQLException {
        return DaoManager.createDao(DatabaseUtils.CONNECTION_SOURCE, User.class);
    }
}