package org.techhub.service;

import java.util.List;

import org.techhub.model.CityModel;
import org.techhub.model.StateModel;

public interface StateService {

	
	public boolean isAddState(StateModel model);
	public List<StateModel> getAllState();
	
	public boolean isDeleteStateById(int stateId);
	
	public boolean isUpdateState(StateModel model);
	public List<StateModel> getAllStateByList(String name);
	public List<CityModel> getCitiesByState(int stateId);
}
