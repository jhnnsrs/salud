package com.example.jhnns.saludo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jhnns on 30.08.2016.
 */
public class Symptom extends SaludModel{
    Integer id;
    String name;

    public Integer getID() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public SaludBundle getAutoCompleteBundle() {
        return new SaludBundle(name,id,this, new Scope("Symptom","symptom"));
    }

    @Override
    public List<SaludModel> getAutoCompletedModels() {
        List<SaludModel> autocomplete = new ArrayList<>();
        autocomplete.add(this);

        return autocomplete;
    }
}


