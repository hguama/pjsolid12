package com.solid12.solid12.service;

import com.solid12.solid12.domain.Citizen;
import com.solid12.solid12.domain.ICitizenManagement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CitizenService {

    ICitizenManagement iCitizenManagement;

    @Autowired
    public CitizenService(@Value("${spring.adapter.service}") String serviceBeanName, ApplicationContext context) {
        this.iCitizenManagement = (ICitizenManagement) context.getBean(serviceBeanName);
    }

    public Citizen createCitizen(Citizen citizen) {
        return iCitizenManagement.createCitizen(citizen);

    }

    public Citizen findCitizenById(String id) {
        return iCitizenManagement.findCitizenById(Long.valueOf(id));

    }

    public int updateCitizen(Citizen citizen) {

        return iCitizenManagement.updateCitizen(citizen);
    }

    public int deleteCitizen(int idCitizen) {

        return iCitizenManagement.deleteCitizen(idCitizen);
    }

    public List<Citizen> getCitizens() {
        return iCitizenManagement.getCitizens();
    }


}
