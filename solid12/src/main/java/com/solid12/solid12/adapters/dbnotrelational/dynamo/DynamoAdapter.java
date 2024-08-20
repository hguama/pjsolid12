package com.solid12.solid12.adapters.dbnotrelational.dynamo;

import com.solid12.solid12.domain.Citizen;
import com.solid12.solid12.domain.ICitizenManagement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.core.pagination.sync.SdkIterable;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.model.GetItemEnhancedRequest;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

import java.util.ArrayList;
import java.util.List;

@Repository("DYNAMOService")
public class DynamoAdapter implements ICitizenManagement {

    private final DynamoDbClient dynamoDbClient;
    private final DynamoDbEnhancedClient dynamoDbEnhancedClient;
    private final DynamoDbTable<CitizenEntityDynamo> citizenTable;


    @Autowired
    public DynamoAdapter(DynamoDbClient dynamoDbClient, DynamoDbEnhancedClient dynamoDbEnhancedClient, DynamoDbTable<CitizenEntityDynamo> citizenTable) {
        this.dynamoDbClient = dynamoDbClient;
        this.dynamoDbEnhancedClient = dynamoDbEnhancedClient;
        this.citizenTable = citizenTable;
    }


    @Override
    public Citizen createCitizen(Citizen citizen) {

        if (findById(citizen.getCitizenDocument()
                            .getDocumentNumber())) return null;

        CitizenEntityDynamo newCitizen = CitizenEntityDynamo.toEntityDynamo(citizen);
        citizenTable.putItem(newCitizen);

        return citizen;

    }


    @Override
    public int updateCitizen(Citizen citizen) {

        if (!findById(citizen.getCitizenDocument()
                             .getDocumentNumber())) {
            return 0;
        }

        CitizenEntityDynamo itemToUpdate = CitizenEntityDynamo.toEntityDynamo(citizen);
        citizenTable.updateItem(itemToUpdate);

        return 1;
    }

    @Override
    public int deleteCitizen(int idCitizen) {
        if (findById(String.valueOf(idCitizen))) {

            CitizenEntityDynamo citizenToDelete = new CitizenEntityDynamo("", "", String.valueOf(idCitizen));
            citizenTable.deleteItem(citizenToDelete);

            return 1;
        }

        return 0;
    }

    @Override
    public List<Citizen> getCitizens() {

        List<Citizen> citizens = new ArrayList<>();
        SdkIterable<CitizenEntityDynamo> entitiesDynamo = citizenTable.scan()
                                                                      .items();

        return entitiesDynamo.stream()
                             .map(CitizenEntityDynamo::toCitizen)
                             .toList();
    }

    private boolean findById(String idCitizen) {

        Key key = Key.builder()
                     .partitionValue(idCitizen)
                     .build();

        CitizenEntityDynamo item = citizenTable.getItem(GetItemEnhancedRequest.builder()
                                                                              .key(key)
                                                                              .build());

        if (item != null) {
            return true;
        }

        return false;
    }


}
