package org.techhub.respository;

import java.sql.PreparedStatement;

import org.techhub.DBConfigure.DBConfig;
import org.techhub.model.LocationModel;

public class LocationRepoImpl extends DBConfig implements LocationRepo {

	@Override
	public boolean SaveLocation(LocationModel location) {
		try {
			String sqlString = "insert into location (locname,ctid) values(?, ?)";
			PreparedStatement pStatement = conn.prepareStatement(sqlString);
			pStatement.setString(1, location.getLocnameString());
			pStatement.setInt(2,location.getCtid());
			int row =pStatement.executeUpdate();
			return row>0;
			
			
		}catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
		
		
	}
	

}
