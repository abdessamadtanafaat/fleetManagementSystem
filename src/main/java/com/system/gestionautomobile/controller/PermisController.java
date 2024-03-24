package com.system.gestionautomobile.controller;

import com.system.gestionautomobile.entity.PermisType;
import com.system.gestionautomobile.service.PermisService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/permis")
public class PermisController {

    private PermisService permisService;

}
