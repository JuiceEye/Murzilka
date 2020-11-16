package com.github.JuiceEye.services;

import java.sql.SQLException;
import java.util.List;

public interface Handler {
    void create(Object object) throws SQLException;
    Object read(int id) throws SQLException;
    void update(Object object) throws SQLException;
    void delete(int id) throws SQLException;
    List<?> getAll() throws SQLException;
}
