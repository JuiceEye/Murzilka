package com.github.JuiceEye;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.github.JuiceEye.conrtrollers.PostController;
import com.github.JuiceEye.conrtrollers.UserController;
import com.github.JuiceEye.deserializers.UserDeserializer;
import com.github.JuiceEye.models.User;
import com.github.JuiceEye.serializers.UserSerializer;
import io.javalin.Javalin;

public class Main {
    public static void main(String[] args) {
        Javalin app = Javalin.create();
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addSerializer(new UserSerializer());
        module.addDeserializer(User.class, new UserDeserializer());
        mapper.registerModule(module);

        //Команды для пользователя
        app.get("/users", UserController::getAllUsers);
        app.get("/user/:userId", UserController::getUser);
        app.post("/user", context -> UserController.createUser(context, mapper));
        app.patch("/user/:userId", context -> UserController.updateUser(context, mapper));
        app.delete("/user/:userId", UserController::deleteUser);


        //Команды для постов
        app.get("/posts", PostController::getAllPosts);
        app.get("/post/:postId", PostController::getPost);
        app.post("/post", context -> PostController.createPost(context, mapper));
        app.patch("/post/:postId", context -> PostController.updatePost(context, mapper));
        app.delete("/post/:postId", PostController::deletePost);
        app.start(8080);
    }
}
