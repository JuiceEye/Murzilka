package com.github.JuiceEye;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.github.JuiceEye.conrtrollers.Controller;
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
        app.get("/users", Controller::getAllUsers);
        app.get("/user/:userId", Controller::getUser);
        app.post("/user", context -> Controller.createUser(context, mapper));
        app.patch("/user/:userId", context -> Controller.updateUser(context, mapper));
        app.delete("/user/:userId", Controller::deleteUser);


        //Команды для постов
        app.get("/posts", Controller::getAllPosts);
        app.get("/post/:postId", Controller::getPost);
        app.post("/post", context -> Controller.createPost(context, mapper));
        app.patch("/post/:postId", context -> Controller.updatePost(context, mapper));
        app.delete("/post/:postId", Controller::deletePost);
        app.start(8080);
    }
}
