package finalproject.repository.jdbc;

import finalproject.model.CriminalCase;
import finalproject.model.Detective;
import finalproject.repository.IDetectiveRepository;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
@Slf4j
public class DetectiveJDBCRepository implements IDetectiveRepository {

    @Override
    public Detective insertOrUpdate(Detective detective) {
        DetectiveJDBCRepository detectiveJDBCRepository= new DetectiveJDBCRepository();
        Detective de= detectiveJDBCRepository.findById(detective.getId());
        try ( Connection con = DatabaseUtility.getConnection()) {
            if(de==null){
                PreparedStatement stmt= con.prepareStatement("insert into detective(id,version) " +
                        "values (?,?)");
                stmt.setLong(1,detective.getId());
                stmt.setLong(2,detective.getVersion());
                stmt.executeUpdate();
            }
            else{
                PreparedStatement stmt= con.prepareStatement("update detective set version=? where id=?");
                stmt.setLong(1,detective.getVersion());
                stmt.setLong(2,detective.getId());
                stmt.executeUpdate();
            }

        } catch (Exception e) {
            log.error(e.toString());
        }
        return detective;
    }

    @Override
    public Detective findById(Long id) {
        List<Detective> detectives = new ArrayList<>();
        try (Connection con = DatabaseUtility.getConnection()) {
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM detective WHERE id = ?");
            stmt.setLong(1,id);
            ResultSet rs= stmt.executeQuery();
            while (rs.next()) {
                Detective detective = DatabaseMapper.getDetective(rs);
                log.debug(detective.toString());

                if(detective != null) detectives.add(detective);
            }

        } catch (Exception e) {
            log.error(e.toString());
        }

        // end of try-with-resources
        if(detectives.size()>0) return detectives.get(0);
        else return null;
    }

    @Override
    public List<Detective> findAll() {
        List<Detective> detectives = new ArrayList<>();
        try (Connection con = DatabaseUtility.getConnection();
             PreparedStatement stmt = con.prepareStatement("SELECT * FROM detective");
             ResultSet rs = stmt.executeQuery ()) {

            while (rs.next()) {
                Detective detective = DatabaseMapper.getDetective(rs);
                log.debug(detective.toString());

                if(detective != null) detectives.add(detective);
            }
        } catch (Exception ex) {
            log.error(ex.toString());
        }
        return detectives;
    }

    @Override
    public void deleteById(Long id) {
        try {
            Connection con= DatabaseUtility.getConnection();
            PreparedStatement stmt = con.prepareStatement("delete FROM detective where id =?");
            stmt.setLong(1,id);
            stmt.executeUpdate();

        } catch (Exception e) {
            log.error(e.toString());
        }
    }
}
