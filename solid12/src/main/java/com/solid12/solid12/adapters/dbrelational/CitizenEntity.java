package com.solid12.solid12.adapters.dbrelational;

import com.solid12.solid12.domain.Citizen;
import com.solid12.solid12.domain.Document;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class CitizenEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;
    private String documentType;
    private String documentNumber;

    public CitizenEntity() {
    }

    public CitizenEntity(String name, String documentType, String documentNumber) {
        this.name = name;
        this.documentType = documentType;
        this.documentNumber = documentNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    @Override
    public String toString() {
        return "CitizenEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", documentType='" + documentType + '\'' +
                ", documentNumber='" + documentNumber + '\'' +
                '}';
    }

    public static CitizenEntity toEntity(Citizen citizen){

        return new CitizenEntity(citizen.getName(), citizen.getCitizenDocument().getDocumentType(), citizen.getCitizenDocument().getDocumentNumber()) ;
    }

    public static Citizen toCitizen(CitizenEntity citizenEntity){

        return new Citizen(citizenEntity.getName(), new Document(citizenEntity.getDocumentType(), citizenEntity.getDocumentNumber()), (int) citizenEntity.id);
    }
}