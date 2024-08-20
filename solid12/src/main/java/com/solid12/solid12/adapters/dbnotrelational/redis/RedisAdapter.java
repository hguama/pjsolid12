package com.solid12.solid12.adapters.dbnotrelational.redis;

import com.solid12.solid12.domain.Citizen;
import com.solid12.solid12.domain.ICitizenManagement;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository("REDISService")
public class RedisAdapter implements ICitizenManagement {

    ICitizenRepositoryRedis iCitizenRepositoryRedis;


    public RedisAdapter(ICitizenRepositoryRedis iCitizenRepositoryRedis) {
        this.iCitizenRepositoryRedis = iCitizenRepositoryRedis;
    }

    @Override
    public Citizen createCitizen(Citizen citizen) {

        if (iCitizenRepositoryRedis.findById(String.valueOf(citizen.getCitizenDocument()
                                                                   .getDocumentNumber()))
                                   .isPresent()) return null;

        CitizenEntityRedis citizenEntityRedis = iCitizenRepositoryRedis.save(CitizenEntityRedis.toEntityRedis(citizen));

        return CitizenEntityRedis.toCitizen(citizenEntityRedis);

    }

    @Override
    public int updateCitizen(Citizen citizen) {
        if (iCitizenRepositoryRedis.findById(String.valueOf(citizen.getCitizenDocument()
                                                                   .getDocumentNumber()))
                                   .isPresent()) {

            CitizenEntityRedis citizenEntityRedis = iCitizenRepositoryRedis.save(CitizenEntityRedis.toEntityRedis(citizen));
            return 1;
        }

        return 0;
    }

    @Override
    public int deleteCitizen(int idCitizen) {
        if (iCitizenRepositoryRedis.findById(String.valueOf(idCitizen))
                                   .isPresent()) {

            iCitizenRepositoryRedis.deleteById(String.valueOf(idCitizen));
            return 1;
        }

        return 0;
    }

    @Override
    public List<Citizen> getCitizens() {

        List<Citizen> citizens = new ArrayList<>();

        iCitizenRepositoryRedis.findAll()
                               .forEach(citizenEntityRedis -> {
                                   Citizen citizen = CitizenEntityRedis.toCitizen(citizenEntityRedis);
                                   citizens.add(citizen);
                               });

        return citizens;
    }
}
