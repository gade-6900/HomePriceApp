package org.techhub.service;

import java.util.List;

import org.techhub.model.CityModel;

public interface CityService {
    public boolean isAddNewCity(CityModel cityModel);

	public List<CityModel> getAllCities();
	public List<CityModel> getallcitybystateId(int stateId);
	
	
}