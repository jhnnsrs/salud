package com.example.jhnns.saludo;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jhnns on 31.08.2016.
 */
public class SaludData {

    public List<SaludModel> models;
    public CustomAdapter mAdapter;
    public List<SaludBundle> autocompleteList;

    public SaludData(CustomAdapter adapter) {
        // SHOULD HAVE ACCESS TO ADAPTER IN ORDER TO SPAWN NEW VIEWS IN IT WHEN NEEDED
        // ALL MODELS ARE REGISTERED INTO SYSTEM ( RIGHT NOW EVERY MODEL THAT IS CREATED SHOULD BE REGISTERED )
        models = new ArrayList<>();
        mAdapter = adapter;
    }

    public void registerModel(SaludModel model){
        models.add(model);
    }

    public List<SaludBundle> getAutocomplete(){
        return autocompleteList;
    }

    public void initializeAutoComplete() {
        // MUST BE CALLED BEFORE ANY SEARCH
        autocompleteList = new ArrayList<>();
        for (SaludModel model:models){
            // THIS ADD ALL RETURNED SALUDBUNDLE TO THE AUTOCOMPLETE LIST
            autocompleteList.addAll(model.getAllAutocomplete());
        }

    }
    public SaludModel isInList(ArrayList<String> matches) {

        // CHECKS ANY MATCHES IF THEY ARE IN THE AUTOCOMPLETE LIST
        for (String matchstring:matches){
            Log.i("AUTOMATCH",matchstring);
            for (SaludBundle bundle:getAutocomplete()) {
                Log.i("AUTO",bundle.autocomplete);
                if (matchstring.contains(bundle.autocomplete)){
                    return bundle.reference;
                }
            }

        }
        // MUST BE ALTERED
        return new Patient("notset","j");

    }

    public void sendToAdapterIfInList(ArrayList<String> matches){
        // SHOULD CHECK IF MATCHES ARE IN LIST AND APPLY THE APPROPRIATE VIEW FOR IT
        // MAYBE ALSO CHECK IF LENS SHOULD CHANGE
        if (isInList(matches) instanceof Patient) {
            ;
            addToAdapter(isInList(matches),0);
        }
        if (isInList(matches) instanceof Symptom) {
            ;
            addToAdapter(isInList(matches),2);
        }
        if (isInList(matches) instanceof Personalia) {
            ;
            addToAdapter(isInList(matches),1);
        }
    }

    public void addToAdapter(SaludModel karl,Integer i) {

        mAdapter.addSaludView(new SaludView(i,karl));
        mAdapter.notifyDataSetChanged();

    }
}
