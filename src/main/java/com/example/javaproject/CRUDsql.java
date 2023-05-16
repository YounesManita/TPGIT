package com.example.javaproject;

import java.sql.Connection;

public interface CRUDsql {
    Connection getConnection();
    void insertRecord();
    void updateRecord();
    void deleteButton();
    void executeQuery();

}
