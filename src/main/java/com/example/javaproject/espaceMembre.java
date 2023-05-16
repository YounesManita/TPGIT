package com.example.javaproject;

import entities.membreClub;

import java.util.LinkedList;
import java.util.List;

public class espaceMembre implements CRUD<membreClub> {

    private List<membreClub> lesmembre;

    espaceMembre(){
        lesmembre = new LinkedList<membreClub>();
    }

    @Override
    public void create(membreClub m) {
        if (lesmembre.contains(m)==false){
            lesmembre.add(m);}
    }

    @Override
    public void update(membreClub m) {
        for(membreClub mm:lesmembre) {
            if(m.getCIN()==mm.getCIN()) {
                mm.setDaten(m.getDaten());
                mm.setNom(m.getNom());
                mm.setPrenom(m.getPrenom());
                mm.setTel(m.getTel());
                mm.setEmail(m.getEmail());
            }
        }
    }

    @Override
    public void delete(membreClub c) {
        lesmembre.remove(c);
    }

    @Override
    public List<membreClub> findAll(){
        return lesmembre ;
    }

    @Override
    public membreClub findById(int id) {
        for(membreClub m :lesmembre) {
            if(m.getCIN()==id) {return m;}

        }
        return null;
    }



}
