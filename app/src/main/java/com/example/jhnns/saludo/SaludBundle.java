package com.example.jhnns.saludo;

/**
 * Created by jhnns on 31.08.2016.
 */
public class SaludBundle {
    public String autocomplete;
    public Integer id;
    public SaludModel reference;
    public Scope scope;

    public SaludBundle(String autocomplete, Integer id, SaludModel reference, Scope scope) {
        this.autocomplete = autocomplete;
        this.id = id;
        this.scope  = scope;
        this.reference = reference;
    }
}
