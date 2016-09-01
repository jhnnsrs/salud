package com.example.jhnns.saludo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jhnns on 30.08.2016.
 */
public class Address extends SaludModel{
    Integer id;
    String street;
    String postcode;

    public Address() {
        id = -1;
    }

    @Override
    public SaludBundle getAutoCompleteBundle() {
        return new SaludBundle(street,id,this,new Scope("Address","address"));
    }

    @Override
    public List<SaludModel> getAutoCompletedModels() {
        List<SaludModel> autocomplete = new ArrayList<>();
        autocomplete.add(this);

        return autocomplete;
    }
}
