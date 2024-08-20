package com.solid12.solid12.domain;

import org.apache.commons.lang3.Validate;

public class Citizen {

    private  String name;
    private  Document citizenDocument;
   private int id;

    public Citizen(String name, Document citizenDocument, int id) {
        Validate.notNull(name, "name must not be null");
        Validate.notNull(citizenDocument, "document must not be null");
        this.name = name;
        this.citizenDocument = citizenDocument;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Document getCitizenDocument() {
        return citizenDocument;
    }

    public void setCitizenDocument(Document citizenDocument) {
        this.citizenDocument = citizenDocument;
    }

    @Override
    public String toString() {
        return "Citizen{" +
                "name='" + name + '\'' +
                ", citizenDocument=" + citizenDocument +
                '}';
    }
}
