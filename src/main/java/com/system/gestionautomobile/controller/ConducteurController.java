package com.system.gestionautomobile.controller;

import com.system.gestionautomobile.aspect.LogActivity;
import com.system.gestionautomobile.entity.Conducteur;
import com.system.gestionautomobile.service.Conducteur.ConducteurService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/conducteur")
@PreAuthorize("hasRole('ROLE_ADMIN')")
@AllArgsConstructor
public class ConducteurController {

    private ConducteurService conducteurService;

    @LogActivity
    @PostMapping
    public ResponseEntity<Conducteur> createConducteur(@Valid @RequestBody Conducteur conducteur){
        return new ResponseEntity<>(conducteurService.saveConducteur(conducteur), HttpStatus.CREATED);
    }


    @LogActivity
    @GetMapping("/clearDriversCache")
    public String clearDriversCache() {
        conducteurService.clearDriverCache();
        return "Cache cleared successfully";
    }



}
