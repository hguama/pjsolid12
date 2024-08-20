package com.solid12.solid12.adapters.dbnotrelational.dynamo;

import com.solid12.solid12.domain.Citizen;
import com.solid12.solid12.domain.Document;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

@DynamoDbBean
public class CitizenEntityDynamo {

    private String name;
    private String documentType;
    private String documentNumber;

    public CitizenEntityDynamo() {
    }

    public CitizenEntityDynamo(String name, String documentType, String documentNumber) {
        this.name = name;
        this.documentType = documentType;
        this.documentNumber = documentNumber;
    }

    @DynamoDbPartitionKey
    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
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

    public static CitizenEntityDynamo toEntityDynamo(Citizen citizen){

        return new CitizenEntityDynamo(citizen.getName(), citizen.getCitizenDocument().getDocumentType(), citizen.getCitizenDocument().getDocumentNumber()) ;
    }

    public static Citizen toCitizen(CitizenEntityDynamo citizenEntityDynamo){

        return new Citizen(citizenEntityDynamo.getName(), new Document(citizenEntityDynamo.getDocumentType(), citizenEntityDynamo.getDocumentNumber()), 0);
    }
}

