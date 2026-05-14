package org.techhub.service;

import org.techhub.model.LocationModel;
import org.techhub.respository.LocationRepo;
import org.techhub.respository.LocationRepoImpl;

public class Locationserviceimpl implements LocationService{
	LocationRepo locationRepo= new LocationRepoImpl();
	@Override
	public boolean saveLocation(LocationModel locationModel) {
		
		return locationRepo.SaveLocation(locationModel);
	}
	

}
