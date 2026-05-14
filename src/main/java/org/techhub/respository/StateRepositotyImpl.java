package org.techhub.respository;


import org.techhub.DBConfigure.DBConfig;
import org.techhub.model.StateModel;
import java.util.*;

public class StateRepositotyImpl extends DBConfig implements StateRepositoty {

	List<StateModel> statemdList;

	@Override
	public boolean isAddState(StateModel model) {

		try {

			stmt = conn.prepareStatement("insert into state (statename) values(?)");
			stmt.setString(1, model.getNameString());

			int value = stmt.executeUpdate();
			if (value > 0) {
				return true;

			} else {
				return false;
			}

		} catch (Exception e) {
			System.out.println("DB error " + e);

			return false;
		}

	}

	@Override
	public List<StateModel> getAllState() {
		try {
			statemdList = new ArrayList<StateModel>();

			stmt = conn.prepareStatement("select * from state order by stateid ");
			rs = stmt.executeQuery();
			while (rs.next()) {
				StateModel sm = new StateModel();
				sm.setId(rs.getInt(1));
				sm.setNameString(rs.getString(2));

				statemdList.add(sm);
			}
			return statemdList;

		} catch (Exception ex) {
			System.out.println("Error " + ex);
		}
		return null;
	}

	@Override
	public boolean isdeletebyid(int id) {
		try {
			stmt = conn.prepareStatement("delete from state where stateid =?  ");
			stmt.setInt(1, id);
			int value = stmt.executeUpdate();

			if (value > 0) {
				return true;
			} else {
				return false;
			}
		} catch (Exception ex) {
			System.out.println("Error is " + ex);
		}
		return false;
	}

	@Override
	public boolean isUpdateState(StateModel model) {
		try {
			stmt.getConnection().prepareStatement("update state set statename=? where stateid=?");
			stmt.setString(1,model.getNameString());
			stmt.setInt(2, model.getId());
			int value=stmt.executeUpdate();
			  if(value>0) {
				  return true;
			  }
			  else {
				  return false;
			  }
		}catch(Exception ex) {
			System.out.println("error" + ex);
			return false;
		}
		
	}

	@Override
	public List<StateModel> getAllStateByList(String name) {
		
		try {
			statemdList = new ArrayList<StateModel>();

			stmt = conn.prepareStatement("select * from state where statename like '%"+name+"%' and order by stateid ");
			rs = stmt.executeQuery();
			while (rs.next()) {
				StateModel sm = new StateModel();
				sm.setId(rs.getInt(1));
				sm.setNameString(rs.getString(2));

				statemdList.add(sm);
			}
			return statemdList;

		} catch (Exception ex) {
			System.out.println("Error " + ex);
		}
		return null;	
	}

}
