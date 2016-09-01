package com.example.jhnns.saludo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jhnns on 30.08.2016.
 */
public class Personalia extends SaludModel{
    Integer id;
    String name;
    String last_name;
    String birthday;
    String gender;
    String add_date;
    String user;

    public Personalia(String last_name, String name) {
        this.last_name = last_name;
        this.name = name;
        this.id = -1;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    @Override
    public SaludBundle getAutoCompleteBundle() {
        return new SaludBundle(name,id,this,new Scope("Personalia","personalia"));
    }

    @Override
    public List<SaludModel> getAutoCompletedModels() {
        List<SaludModel> autocomplete = new ArrayList<>();
        autocomplete.add(this);

        return autocomplete;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

}
