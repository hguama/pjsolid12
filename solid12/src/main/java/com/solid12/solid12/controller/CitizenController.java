package com.solid12.solid12.controller;

import com.solid12.solid12.controller.exception_handler.CitizenNotFoundException;
import com.solid12.solid12.domain.Citizen;
import com.solid12.solid12.service.CitizenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CitizenController {
    CitizenService citizenService;

    @Autowired
    public CitizenController(CitizenService citizenService) {
        this.citizenService = citizenService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createCitizen(@RequestBody Citizen citizen) {

        Citizen citizenCreated = citizenService.createCitizen(citizen);

        return ResponseEntity.ok(new Response("200", "Citizen created sucessfully", citizenCreated, null));
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateCitizen(@RequestBody Citizen citizen) {

        if (citizenService.updateCitizen(citizen) == 0)
            throw new CitizenNotFoundException("ID: " + citizen.getId() + " not found");

        return ResponseEntity.ok(new Response("200", "Citizen updated", citizen, null));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteCitizen(@RequestBody Citizen citizen) {
        int recordsDeleted = citizenService.deleteCitizen(citizen.getId());

        if (recordsDeleted > 0)
            return new ResponseEntity<>(new Response("200", "operation success", recordsDeleted + " records deleted. Record deleted: " + citizen.getId(), null), HttpStatus.OK);

        return new ResponseEntity(new Response("404", "operation failed", null, recordsDeleted + " records deleted. Record not found: " + citizen.getId()), HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCitizenById(@PathVariable("id") Integer idCitizen) {
        int recordsDeleted = citizenService.deleteCitizen(idCitizen);

        if (recordsDeleted > 0)
            return new ResponseEntity<>(new Response("200", "operation success. Record deleted: " + idCitizen, null, null), HttpStatus.OK);

        return new ResponseEntity(new Response("400", "operation failed. ", null, "Record not found: " + idCitizen), HttpStatus.NOT_FOUND);
    }

    @GetMapping("/getall")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(new Response("200", "Operation success", citizenService.getCitizens(), null));
    }

    @GetMapping("/findid/{id}")
    public ResponseEntity<?> findCitizenById(@PathVariable("id") String id) {
        return ResponseEntity.ok(new Response("200", "Operation success", citizenService.getCitizens(), null));
    }

}
