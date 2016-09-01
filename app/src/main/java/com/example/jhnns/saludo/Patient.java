package com.example.jhnns.saludo;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jhnns on 30.08.2016.
 */
public class Patient extends SaludModel{
    Integer id;
    String username;
    String email;
    Personalia personalia;
    List<Symptom> symptoms;
    Address address;
    Links links;

    public Patient(String username, String email) {
        this.username = username;
        this.email = email;
        this.id = 1;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public List<Symptom> getSymptom() {
        return symptoms;
    }

    @Override
    public SaludBundle getAutoCompleteBundle() {
        Log.i("CALLED","patient was called");
        return new SaludBundle(personalia.name,id,this,new Scope("Patient","Normal"));
    }

    @Override
    public List<SaludModel> getAutoCompletedModels() {
        List<SaludModel> autocomplete = new ArrayList<>();
        autocomplete.add(this);
        autocomplete.add(personalia);
        autocomplete.addAll(symptoms);

        return autocomplete;
    }
};
