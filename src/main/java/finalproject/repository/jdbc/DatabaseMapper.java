package finalproject.repository.jdbc;

import finalproject.model.Evidence;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import finalproject.model.CriminalCase;
import finalproject.model.Detective;

import java.sql.ResultSet;
import java.sql.SQLException;
@Slf4j
public class DatabaseMapper {
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
            log.error("Something weird happened while processing " +  e.toString());
            throw new RuntimeException(e);
        }
    }
    public static Detective getDetective(ResultSet detectiveResultSet)  {
        try{
            Detective detective= new Detective();
            detective.setId(detectiveResultSet.getLong("id"));
            detective.setVersion(detectiveResultSet.getInt("version"));
            return detective;
        } catch (SQLException e){
            log.error("Something weird happened while processing " +  e.toString());
            throw new RuntimeException();
        }
    }
    public static Evidence getEvidence(ResultSet evidenceResultSet){
        try{
            Evidence evidence= new Evidence();
            evidence.setId(evidenceResultSet.getLong("id"));
            evidence.setVersion(evidenceResultSet.getInt("version"));
            return evidence;
        } catch (SQLException e){
            log.error("Something weird happened while processing " +  e.toString());
            throw new RuntimeException();
        }
    }
}

