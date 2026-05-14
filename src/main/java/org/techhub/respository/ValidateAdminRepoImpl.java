package org.techhub.respository;

import java.util.Optional;

import org.techhub.DBConfigure.DBConfig;
import org.techhub.model.AdminLoginModel;

public class ValidateAdminRepoImpl extends DBConfig implements ValidateAdminRepo {

	@Override
	public Optional<AdminLoginModel> validateAdmin(AdminLoginModel model) {

	    try {
	        stmt = conn.prepareStatement(
	            "SELECT * FROM adminlogin WHERE username=? AND password=?"
	        );

	        stmt.setString(1, model.getUsername());
	        stmt.setString(2, model.getPassword());

	        rs = stmt.executeQuery();

	        if (rs.next()) {
	            return Optional.of(model);  
	        }

	    } catch (Exception ex) {
	        System.out.println("Error: " + ex);
	    }

	    return Optional.empty();  
	}
    }
