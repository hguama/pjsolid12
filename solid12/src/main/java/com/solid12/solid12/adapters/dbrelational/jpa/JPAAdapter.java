package com.solid12.solid12.adapters.dbrelational.jpa;

import com.solid12.solid12.adapters.dbrelational.CitizenEntity;
import com.solid12.solid12.adapters.dbrelational.ICitizenRepository;
import com.solid12.solid12.controller.exception_handler.CitizenAlreadyExist;
import com.solid12.solid12.domain.Citizen;
import com.solid12.solid12.domain.ICitizenManagement;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("JPAService")
public class JPAAdapter implements ICitizenManagement {
    ICitizenRepository iCitizenRepository;

    public JPAAdapter(ICitizenRepository iCitizenRepository) {
        this.iCitizenRepository = iCitizenRepository;
    }

    @Override
    public Citizen createCitizen(Citizen citizen) {

        if (iCitizenRepository.findByDocumentNumber(citizen.getCitizenDocument()
                                                           .getDocumentNumber())
                              .isPresent()) {
            throw new CitizenAlreadyExist();
        }

        CitizenEntity entity = iCitizenRepository.save(CitizenEntity.toEntity(citizen));
        return CitizenEntity.toCitizen(entity);
    }

    @Override
    public Citizen findCitizenById(Long id) {
        return CitizenEntity.toCitizen(iCitizenRepository.findById(id)
                                                         .get());
    }

    @Override
    public int updateCitizen(Citizen citizen) {
        Optional<CitizenEntity> citizenEntityFound = iCitizenRepository.findById(Long.valueOf(citizen.getId()));

        if (iCitizenRepository.findById(Long.valueOf(citizen.getId()))
                              .isPresent()) {

            CitizenEntity citizenEntityToUpdate = citizenEntityFound.get();
            citizenEntityToUpdate.setName(citizen.getName());
            citizenEntityToUpdate.setDocumentType(citizen.getCitizenDocument()
                                                         .getDocumentType());
            citizenEntityToUpdate.setDocumentNumber(citizen.getCitizenDocument()
                                                           .getDocumentNumber());

            iCitizenRepository.save(citizenEntityToUpdate);

            return 1;
        }

        return 0;
    }

    @Override
    public int deleteCitizen(int idCitizen) {

        if (iCitizenRepository.existsById((long) idCitizen)) {
            iCitizenRepository.deleteById(Long.valueOf(idCitizen));
            return 1;
        }

        return 0;
    }

    @Override
    public List<Citizen> getCitizens() {

        return iCitizenRepository.findAll()
                                 .stream()
                                 .map(CitizenEntity::toCitizen)
                                 .toList();

    }
}
