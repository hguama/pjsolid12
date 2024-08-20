package com.solid12.solid12.domain;


import java.util.List;


public interface ICitizenManagement {

    Citizen createCitizen(Citizen citizen);
    int updateCitizen(Citizen citizen);
    int deleteCitizen(int idCitizen);

    default Citizen findCitizenById(Long id) {
        return null;
    }

    List<Citizen> getCitizens();
}
