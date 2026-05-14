package org.techhub.service;

import java.util.List;

import org.techhub.model.CityModel;
import org.techhub.model.StateModel;
import org.techhub.respository.*;

public class StateServiceImpl implements StateService {   

	StateRepositoty stateRepo = new StateRepositotyImpl();

	@Override
	public boolean isAddState(StateModel model) {

		return stateRepo.isAddState(model);

	}

	@Override
	public List<StateModel> getAllState() {

		return stateRepo.getAllState();
	}

	@Override
	public boolean isDeleteStateById(int stateId) {

		return stateRepo.isdeletebyid(stateId);

	}

	@Override
	public boolean isUpdateState(StateModel model) {
		
		return stateRepo.isUpdateState(model);
	}

	@Override
	public List<StateModel> getAllStateByList(String name) {
		return  stateRepo.getAllStateByList(name);
	}

	@Override
	public List<CityModel> getCitiesByState(int stateId) {
		// TODO Auto-generated method stub
		return null;
	}
}
