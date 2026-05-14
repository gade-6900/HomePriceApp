package org.techhub.respository;

import java.util.List;
import org.techhub.model.CityModel;

public interface CityRepo {

    public boolean isAddNewCity(CityModel cityModel);

   // public List<CityModel> getallcitybystateId(int stateId);

	List<CityModel> getAllCityByStateId(int stateId);

}