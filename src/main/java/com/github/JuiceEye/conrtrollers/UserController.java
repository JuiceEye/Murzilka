package com.github.JuiceEye.conrtrollers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.JuiceEye.config.DatabaseUtils;
import com.github.JuiceEye.models.Role;
import com.github.JuiceEye.models.User;
import com.github.JuiceEye.services.UserService;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import io.javalin.http.Context;

import java.sql.SQLException;

public interface UserController {
    default Dao<User, Integer> userDao() throws SQLException {
        return DaoManager.createDao(DatabaseUtils.CONNECTION_SOURCE, User.class);
    }
    //Комманды для пользователя
    static void getAllUsers(Context context) throws SQLException {
        if (new UserService().defineUserRole(context) == Role.UNDEFINED) {
            context.result("Вы не авторизованы");
        } else if (new UserService().defineUserRole(context) == Role.ADMIN) {
            context.result(new UserService().getAll().toString());
        } else {
            context.result("У вас недостаточно прав для совершения этого действия");
        }
    }

    static void getUser(Context context) throws SQLException {
        if (new UserService().defineUserRole(context) == Role.UNDEFINED) {
            context.result("Вы не авторизованы");
        } else if (new UserService().defineUserRole(context) == Role.ADMIN) {
            context.result(new UserService().read(Integer.parseInt(context.pathParam("userId"))).toString());
        } else {
            context.result("У вас недостаточно прав для совершения этого действия");
        }
    }

    static void createUser(Context context, ObjectMapper mapper) throws SQLException, JsonProcessingException {
        if (new UserService().defineUserRole(context) == Role.UNDEFINED) {
            context.result("Вы не авторизованы");
        } else if (new UserService().defineUserRole(context) == Role.ADMIN) {
            new UserService().create(mapper.readValue(context.body(), User.class));
        } else {
            context.result("У вас недостаточно прав для совершения этого действия");
        }
    }

    static void updateUser(Context context, ObjectMapper mapper) throws SQLException, JsonProcessingException {
        if (new UserService().defineUserRole(context) == Role.UNDEFINED) {
            context.result("Вы не авторизованы");
        } else if (new UserService().defineUserRole(context) == Role.ADMIN) {
            User u = mapper.readValue(context.body(), User.class);
            u.setUserId(Integer.parseInt(context.pathParam("userId")));
            new UserService().update(u);
        } else {
            context.result("У вас недостаточно прав для совершения этого действия");
        }
    }

    static void deleteUser(Context context) throws SQLException {
        if (new UserService().defineUserRole(context) == Role.UNDEFINED) {
            context.result("Вы не авторизованы");
        } else if (new UserService().defineUserRole(context) == Role.ADMIN) {
            new UserService().delete(Integer.parseInt(context.pathParam("userId")));
        } else {
            context.result("У вас недостаточно прав для совершения этого действия");
        }
    }
}