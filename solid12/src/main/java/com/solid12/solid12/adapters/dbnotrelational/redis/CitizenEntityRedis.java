package com.solid12.solid12.adapters.dbnotrelational.redis;

import com.solid12.solid12.domain.Citizen;
import com.solid12.solid12.domain.Document;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@RedisHash("CitizenEntityRedis")
 class CitizenEntityRedis implements Serializable {

    @Id
    private String documentNumber;
    private String documentType;
    private String name;

    public CitizenEntityRedis() {
    }

    public CitizenEntityRedis(String name, String documentType, String documentNumber) {
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

    public static CitizenEntityRedis toEntityRedis(Citizen citizen){

        return new CitizenEntityRedis(citizen.getName(), citizen.getCitizenDocument().getDocumentType(), citizen.getCitizenDocument().getDocumentNumber()) ;
    }

    public static Citizen toCitizen(CitizenEntityRedis citizenEntityRedis){

        return new Citizen(citizenEntityRedis.getName(), new Document(citizenEntityRedis.getDocumentType(), citizenEntityRedis.getDocumentNumber()), 0);
    }
}