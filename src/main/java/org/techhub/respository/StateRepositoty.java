package org.techhub.respository;

import org.techhub.model.StateModel;
import java.util.*;

public interface StateRepositoty {
	public boolean isAddState(StateModel model);

	public List<StateModel> getAllState();

	public boolean isdeletebyid(int id);
	
	public boolean isUpdateState(StateModel model);
	
	public List<StateModel> getAllStateByList(String name);
	

}
