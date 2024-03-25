package com.system.gestionautomobile.service;

import com.system.gestionautomobile.entity.PermisCategorie;
import com.system.gestionautomobile.entity.VehiculeType;

public class VehiculeToPermisCategorieService{
    public static PermisCategorie getPermisCategorie(VehiculeType vehiculeType){
        switch(vehiculeType){
            case CAR :
               return PermisCategorie.B;
            case TRUCK:
                return PermisCategorie.C;
            case FOURGONNETTE:
                return PermisCategorie.D ;
            default :
                return null ;
        }


    }
}
