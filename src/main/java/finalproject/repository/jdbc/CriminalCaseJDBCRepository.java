package finalproject.repository.jdbc;

import finalproject.model.CriminalCase;
import finalproject.repository.ICriminalCaseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class CriminalCaseJDBCRepository implements ICriminalCaseRepository {


    private final static Logger logger = LoggerFactory.getLogger(CriminalCaseJDBCRepository.class);

    @Override
    public CriminalCase insertOrUpdate(CriminalCase criminalCase) {
        CriminalCaseJDBCRepository criminalCaseJDBCRepository= new CriminalCaseJDBCRepository();
        CriminalCase cr= criminalCaseJDBCRepository.findById(criminalCase.getId());
        try ( Connection con = DatabaseUtility.getConnection()) {
            if(cr==null){
            PreparedStatement stmt= con.prepareStatement("insert into criminal_case(id,version,number) " +
                    "values (?,?,?)");
            stmt.setLong(1,criminalCase.getId());
            stmt.setLong(2,criminalCase.getVersion());
            stmt.setString(3,criminalCase.getNumber());
            stmt.executeUpdate();
            }
            else{
                PreparedStatement stmt= con.prepareStatement("update criminal_case set version=?,number=? where id=?");
                stmt.setLong(1,criminalCase.getVersion());
                stmt.setString(2,criminalCase.getNumber());
                stmt.setLong(3,criminalCase.getId());
                stmt.executeUpdate();
            }

        } catch (Exception e) {
            logger.error(e.toString());
        }
        return criminalCase;
    }




    //    private Map<Long, CriminalCase> criminalCases= new HashMap<>();
    @Override
    public CriminalCase findById(Long id) {
        List<CriminalCase> criminalCases = new ArrayList<>();
        try (Connection con = DatabaseUtility.getConnection()) {
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM criminal_case WHERE id = ?");
            stmt.setLong(1,id);
            ResultSet rs= stmt.executeQuery();
            while (rs.next()) {
                CriminalCase criminalCase = DatabaseMapper.getCriminalCase(rs);
                logger.debug(criminalCase.toString());

                if(criminalCase != null) criminalCases.add(criminalCase);
            }

        } catch (Exception e) {
            logger.error(e.toString());
        }

        // end of try-with-resources
        if(criminalCases.size()>0) return criminalCases.get(0);
        else return null;
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
            }
        } catch (Exception ex) {
            logger.error(ex.toString());
        }
        return criminalCases;
    }

    @Override
    public void deleteById(Long id) {
        try {
            Connection con= DatabaseUtility.getConnection();
            PreparedStatement stmt = con.prepareStatement("delete FROM criminal_case where id =?");
            stmt.setLong(1,id);
            stmt.executeUpdate();

        } catch (Exception e) {
            logger.error(e.toString());
        }
    }
}
