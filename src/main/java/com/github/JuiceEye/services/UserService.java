package com.github.JuiceEye.services;

import com.github.JuiceEye.config.DatabaseUtils;
import com.github.JuiceEye.models.Role;
import com.github.JuiceEye.models.User;
import com.github.JuiceEye.conrtrollers.UserController;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import io.javalin.http.Context;
import org.mindrot.jbcrypt.BCrypt;
import java.sql.SQLException;
import java.util.List;

public class UserService implements UserController, Handler {
    Dao<User, Integer> userDao;

    {
        try {
            userDao = DaoManager.createDao(DatabaseUtils.CONNECTION_SOURCE, User.class);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void create(Object objectUser) throws SQLException {
        User user = (User) objectUser;
        userDao().create(user);
        System.out.println("Created user " + user);
    }

    @Override
    public Object read(int id) throws SQLException {
        User user = userDao().queryForId(id);
        System.out.println(user);
        return user;
    }

    @Override
    public void update(Object objectUser) throws SQLException {
        User user = (User) objectUser;
        userDao().update(user);
        System.out.println("Updated user " + user);
    }

    @Override
    public void delete(int id) throws SQLException {
        User user = userDao().queryForId(id);
        System.out.println("Deleted" + user);
        userDao().delete(user);
    }

    @Override
    public List<?> getAll() throws SQLException {
        return userDao().queryForAll();
    }

    public Role defineUserRole(Context context) throws SQLException {
        List<User> userList;
        QueryBuilder<User, Integer> queryBuilder = userDao.queryBuilder();
        queryBuilder.where().eq("username", context.basicAuthCredentials().getUsername());
        PreparedQuery<User> preparedQuery = queryBuilder.prepare();
        userList = userDao.query(preparedQuery);
        Role role = null;
        try {
            if (BCrypt.checkpw(context.basicAuthCredentials().getPassword(), userList.get(0).getPassword())) {
                role = userList.get(0).getUserRole();
            } else {
                role = Role.UNDEFINED;
            }
        } catch (IndexOutOfBoundsException IOOBE) {
            role = Role.UNDEFINED;
        }
        return role;
    }
}
