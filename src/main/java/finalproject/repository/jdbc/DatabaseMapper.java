package finalproject.repository.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import finalproject.model.CriminalCase;
import finalproject.model.Detective;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseMapper {
    public static final Logger logger= LoggerFactory.getLogger(DatabaseMapper.class);
    public static CriminalCase getCriminalCase(ResultSet criminalCaseResultSet){
        try {
            CriminalCase criminalCase= new CriminalCase();
            criminalCase.setId(criminalCaseResultSet.getLong("id"));
            criminalCase.setNumber(criminalCaseResultSet.getString("number"));
            criminalCase.setVersion(criminalCaseResultSet.getInt("version"));
            criminalCase.setShortDescription(criminalCaseResultSet.getString("short_description"));
            criminalCase.setDetailedDescription(criminalCaseResultSet.getString("detailed_description"));
//            criminalCase.setNotes(criminalCaseResultSet.getString("notes"));
//            criminalCase.setCreatedAt(criminalCaseResultSet.getString("cre"));
//            criminalCase.setAssigned(criminalCaseResultSet.getInt("assigned"));
//            criminalCase.setId(criminalCaseResultSet.getLong("id");
//            criminalCase.setNumber(criminalCaseResultSet.getString("number"));
//            criminalCase.setVersion(criminalCaseResultSet.getInt("version"));
//            criminalCase.setAssigned(criminalCaseResultSet.getInt("assigned"));
            //
            return criminalCase;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
//    public static Detective getDetective(ResultSet detecResultSet){
//
//    }
}
