package com.github.JuiceEye.conrtrollers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.JuiceEye.models.Post;
import com.github.JuiceEye.models.Role;
import com.github.JuiceEye.models.User;
import com.github.JuiceEye.services.PostService;
import com.github.JuiceEye.services.UserService;
import io.javalin.http.Context;

import java.sql.SQLException;

public interface Controller {

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

    static <Mapper> void createUser(Context context, ObjectMapper mapper) throws SQLException, JsonProcessingException {
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





    //Комманды для юзера
    static void getAllPosts(Context context) throws SQLException {
        if (new UserService().defineUserRole(context) == Role.UNDEFINED) {
            context.result("Вы не авторизованы");
        } else if (new UserService().defineUserRole(context) == Role.ADMIN) {
            context.result(new PostService().getAll().toString());
        } else {
            context.result("У вас недостаточно прав для совершения этого действия");
        }
    }

    static void getPost(Context context) throws SQLException {
        if (new UserService().defineUserRole(context) == Role.UNDEFINED) {
            context.result("Вы не авторизованы");
        } else if (new PostService().isVipPost(context)) {
            if (new UserService().defineUserRole(context) == Role.VIP || new UserService().defineUserRole(context) == Role.ADMIN) {
                context.result(new PostService().read(Integer.parseInt(context.pathParam("postId"))).getText());
            } else {
                context.result("Данный пост доступен только для VIP читателей. Приобрести статус VIP вы можете написав сюда --> vk.com/MurzilkaAdministration");
            }
        } else {
            context.result(new PostService().read(Integer.parseInt(context.pathParam("postId"))).getText());
        }
    }

    static void createPost(Context context, ObjectMapper mapper) throws SQLException, JsonProcessingException {
        if (new UserService().defineUserRole(context) == Role.UNDEFINED) {
            context.result("Вы не авторизованы");
        } else if (new UserService().defineUserRole(context) == Role.ADMIN) {
            new PostService().create(mapper.readValue(context.body(), Post.class));
        } else {
            context.result("Вы не можете писать новости, так как вы не администратор. Но если вам есть что рассказать, дайте нам знать --> vk.com/MurzilkaAdministration");
        }
    }

    static void updatePost(Context context, ObjectMapper mapper) throws SQLException, JsonProcessingException {
        if (new UserService().defineUserRole(context) == Role.UNDEFINED) {
            context.result("Вы не авторизованы");
        } else if (new UserService().defineUserRole(context) == Role.ADMIN) {
            Post p = mapper.readValue(context.body(), Post.class);
            p.setPostId(Integer.parseInt(context.pathParam("postId")));
            new PostService().update(p);
        } else {
            context.result("Вы не можете редактировать новости, так как вы не администратор. Но если вы заметили ошибку или неточность, дайте нам знать --> vk.com/MurzilkaAdministration");
        }
    }

    static void deletePost(Context context) throws SQLException {
        if (new UserService().defineUserRole(context) == Role.UNDEFINED) {
            context.result("Вы не авторизованы");
        } else if (new UserService().defineUserRole(context) == Role.ADMIN) {
            new PostService().delete(Integer.parseInt(context.pathParam("postId")));
        } else {
            context.result("Вы не можете удалять новости, так как вы не администратор. Но если вы считаете этот пост неприемлимым или хотите удалить пост по какой-либо другой причине,  --> vk.com/MurzilkaAdministration");
        }
    }
}
