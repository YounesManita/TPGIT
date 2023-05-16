package com.example.javaproject;
import java.util.List;


public interface CRUD<T>{
    void create(T c);
    void update(T c);
    void delete(T c);
    List<T> findAll();
    T findById(int id);
}
