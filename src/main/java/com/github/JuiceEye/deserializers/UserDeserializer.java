package com.github.JuiceEye.deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.github.JuiceEye.models.Role;
import com.github.JuiceEye.models.User;
import org.mindrot.jbcrypt.BCrypt;
import java.io.IOException;

public class UserDeserializer extends StdDeserializer<User> {

    public UserDeserializer() {
        super(User.class);
    }

    @Override
    public User deserialize(JsonParser p, DeserializationContext deserializationContext) throws IOException {
        JsonNode root = p.getCodec().readTree(p);
//        int userId = Integer.valueOf(root.get("userId").asText());
        String username = root.get("username").asText();
        String password = BCrypt.hashpw(root.get("password").asText(), BCrypt.gensalt());
        Role role = Role.valueOf(root.get("role").asText());
        return new User(/*userId*/0, username, password, role);
    }
}
