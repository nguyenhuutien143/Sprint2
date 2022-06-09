package finalproject.repository.jdbc;

import finalproject.model.CriminalCase;
import finalproject.repository.ICriminalCaseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class CriminalCaseJDBCRepository implements ICriminalCaseRepository {


    private final static Logger logger = LoggerFactory.getLogger(CriminalCaseJDBCRepository.class);

    @Override
    public CriminalCase insertOrUpdate(CriminalCase criminalCase) {
        return null;
    }

    @Override
    public CriminalCase findById(Long id) {
        return null;
    }

    @Override
    public List<CriminalCase> findAll() {
        List<CriminalCase> criminalCases = new ArrayList<>();
        try (Connection con = DatabaseUtility.getConnection();
             PreparedStatement stmt = con.prepareStatement("SELECT * FROM criminal_case");
             ResultSet rs = stmt.executeQuery ()) {

            while (rs.next()) {
                CriminalCase criminalCase = DatabaseMapper.getCriminalCase(rs);
                logger.debug(criminalCase.toString());

                if(criminalCase != null) criminalCases.add(criminalCase);
            } // end of while
//        } catch (SQLException e) {
//            logger.error(e.toString());
        } catch (Exception ex) {
            logger.error(ex.toString());
        }
        // end of try-with-resources
        return criminalCases;
    }

    @Override
    public void deleteById(Long id) {

    }
}
