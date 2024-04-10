package com.system.gestionautomobile.controller;

import com.system.gestionautomobile.service.Permis.PermisService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@PreAuthorize("hasRole('ROLE_ADMIN')")
@RequestMapping("/permits")
public class PermitController {

    private PermisService permisService;

}
