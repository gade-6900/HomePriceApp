package org.techhub.service;

import java.util.List;
import org.techhub.model.CityModel;
import org.techhub.respository.CityRepo;
import org.techhub.respository.CityRepoImpl;

public class CityServiceImpl implements CityService {

    CityRepo cityRepo = new CityRepoImpl();

    @Override
    public boolean isAddNewCity(CityModel cityModel) {
        return cityRepo.isAddNewCity(cityModel);
    }

    @Override
    public List<CityModel> getAllCities() {
        return null;
    }

    @Override
    public List<CityModel> getallcitybystateId(int stateId) {
        return cityRepo.getAllCityByStateId(stateId);
    }
}