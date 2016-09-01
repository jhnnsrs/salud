package com.example.jhnns.saludo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jhnns on 31.08.2016.
 */

public abstract class SaludModel {

    public abstract SaludBundle getAutoCompleteBundle();

    public List<SaludBundle> getAllAutocomplete(){
            // SEARCHES THROUGH ALL MODELS THAT HAVE BEEN REGISTERED IN THE
            // ABSTRACT AUTOCOMPLETED MODELS METHOD
            // SO IF BASE MODEL PATIENT:
            //  LIST CONTAINS ALL PERSONALIA AUTOCOMPLETE
            //                    LIST OF SYMPTOMS AUTOCOMPLETE
            // JUST GOES ONE DEPTH DOWN
            return auto_getFromList(getAutoCompletedModels());

    }

    public SaludBundle auto_getFromDetail(SaludModel model){
        return model.getAutoCompleteBundle();

    }

    public List<SaludBundle> auto_getFromList(List<SaludModel> models){
        List<SaludBundle> autocompletelist = new ArrayList<>();
        for (SaludModel model:models )
            {
                autocompletelist.add(model.getAutoCompleteBundle());
        }
        return autocompletelist;
    }

    public void registerWith(SaludData data){
        for (SaludModel model:getAutoCompletedModels()
             ) {
                data.registerModel(model);

        }
    }

    public abstract List<SaludModel> getAutoCompletedModels();

}

