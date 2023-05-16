package com.example.javaproject;

import entities.club;

import java.util.LinkedList;
import java.util.List;


public class espaceClub implements CRUD<club> {

    private List<club> lesclub;

    espaceClub(){
        lesclub = new LinkedList<club>();
    }

    @Override
    public void create(club c) {
        lesclub.add(c);
    }

    @Override
    public void update(club c) {
        for(club cl:lesclub) {
            if(c.getIdclub()==cl.getIdclub()) {
                cl.setNomc(c.getNomc());
                cl.setCatc(c.getCatc());
                cl.setNbrc(c.getNbrc());
                cl.setCotisation(c.getCotisation());
            }
        }
    }

    @Override
    public void delete(club c) {
        lesclub.remove(c);
    }

    @Override
    public List<club> findAll(){
        return lesclub ;
    }

    @Override
    public club findById(int id) {
        for(club c :lesclub) {
            if(c.getIdclub()==id) {return c;}

        }
        return null;
    }
}