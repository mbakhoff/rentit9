// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package esi.rentit9.soap;

import esi.rentit9.soap.PlantsAvailableRequest;
import java.util.Calendar;

privileged aspect PlantsAvailableRequest_Roo_JavaBean {
    
    public String PlantsAvailableRequest.getNameLike() {
        return this.nameLike;
    }
    
    public void PlantsAvailableRequest.setNameLike(String nameLike) {
        this.nameLike = nameLike;
    }
    
    public Calendar PlantsAvailableRequest.getStartDate() {
        return this.startDate;
    }
    
    public void PlantsAvailableRequest.setStartDate(Calendar startDate) {
        this.startDate = startDate;
    }
    
    public Calendar PlantsAvailableRequest.getEndDate() {
        return this.endDate;
    }
    
    public void PlantsAvailableRequest.setEndDate(Calendar endDate) {
        this.endDate = endDate;
    }
    
}
