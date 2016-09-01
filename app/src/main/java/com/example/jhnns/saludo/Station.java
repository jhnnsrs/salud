package com.example.jhnns.saludo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jhnns on 31.08.2016.
 */
public class Station extends SaludModel {
    public String name;
    public Integer id;
    public List<Patient> recentPatients;
    public List<Patient> allPatients;
    public List<Symptom> allSymptoms;

    public Station() {
        recentPatients = new ArrayList<>();
        allSymptoms = new ArrayList<>();
        name = "7B";
        id = -1;
    }

    public void addPatient(Patient patient) {
        allPatients.add(patient);
    }

    @Override
    public SaludBundle getAutoCompleteBundle() {
        return new SaludBundle(name,id,this, new Scope("Station","station"));
    }

    @Override
    public List<SaludModel> getAutoCompletedModels() {
        List<SaludModel> autocomplete = new ArrayList<>();
        autocomplete.addAll(allSymptoms);
        autocomplete.addAll(recentPatients);
        autocomplete.addAll(allPatients);

        return autocomplete;
    }
}
