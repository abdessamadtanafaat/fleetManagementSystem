package com.system.gestionautomobile.service.Permis;

import com.system.gestionautomobile.entity.Permis;
import com.system.gestionautomobile.entity.PermisType;

import java.util.List;
import java.util.Set;

public interface PermisService {
    public Permis savePermis(Permis permis);
    public Set<PermisType> savePermisTypes(Permis permis ,Set<PermisType> permisTypes);
}
