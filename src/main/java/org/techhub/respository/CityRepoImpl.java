package org.techhub.respository;

import java.util.ArrayList;
import java.util.List;

import org.techhub.DBConfigure.DBConfig;
import org.techhub.model.CityModel;

public class CityRepoImpl extends DBConfig implements CityRepo {

    @Override
    public boolean isAddNewCity(CityModel cityModel) {

        try {
            stmt = conn.prepareStatement(
                "INSERT INTO city (cityname, stateid) VALUES (?, ?)"
            );

            stmt.setString(1, cityModel.getCityName());
            stmt.setInt(2, cityModel.getStateId());

            int result = stmt.executeUpdate();

            return result > 0;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return false;
    }

    @Override
    public List<CityModel> getAllCityByStateId(int stateId) {

        List<CityModel> cityList = new ArrayList<>();

        try {
            stmt = conn.prepareStatement(
                "SELECT * FROM city WHERE stateid = ?"
            );

            stmt.setInt(1, stateId);

            rs = stmt.executeQuery();

            while (rs.next()) {

                CityModel city = new CityModel();

                city.setCityId(rs.getInt("ctid"));
                city.setCityName(rs.getString("cityname"));
                city.setStateId(rs.getInt("stateid"));

                cityList.add(city);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return cityList;
    }

	
}