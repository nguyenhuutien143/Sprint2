package finalproject.repository.jdbc;

import finalproject.model.Evidence;
import finalproject.repository.IEvidenceRepository;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
@Slf4j

public class EvidenceJDBCRepository implements IEvidenceRepository {
    @Override
    public Evidence insertOrUpdate(Evidence evidence) {
        EvidenceJDBCRepository evidenceJDBCRepository= new EvidenceJDBCRepository();
        Evidence evidence1= evidenceJDBCRepository.findById(evidence.getId());
        try(Connection con= DatabaseUtility.getConnection()){
            if(evidence1==null){
                PreparedStatement stmt= con.prepareStatement("insert into evidence(id,version) values (?,?)");
                stmt.setLong(1,evidence.getId());
                stmt.setLong(2,evidence.getVersion());
                stmt.executeUpdate();
            }
            else{
                PreparedStatement stmt= con.prepareStatement("upate evidence set version=? where id=?");
                stmt.setLong(1,evidence.getVersion());
                stmt.setLong(2,evidence.getId());
                stmt.executeUpdate();

            }
        } catch (Exception e){
            log.error(e.toString());
        }

        return evidence;
    }

    @Override
    public Evidence findById(Long id) {
        List<Evidence> evidenceList= new ArrayList<>();
        try(Connection con= DatabaseUtility.getConnection()){
        PreparedStatement stmt= con.prepareStatement("select * from evidence where id=?");
        stmt.setLong(1,id);
            ResultSet rs=stmt.executeQuery();
            while(rs.next()){
                Evidence e= DatabaseMapper.getEvidence(rs);
                log.debug(e.toString());
                if(e!=null) evidenceList.add(e);
            }
        }catch(Exception e){
            log.error(e.toString());
        }
//        Evidence e= evidenceList.get(0);
        if(evidenceList.size()>0) return evidenceList.get(0);
        else return null;
    }

    @Override
    public List<Evidence> findAll() {
        List<Evidence> evidenceList= new ArrayList<>();
        try(Connection con= DatabaseUtility.getConnection()){
            PreparedStatement stmt= con.prepareStatement("select * from evidence");
            ResultSet rs= stmt.executeQuery();
            while (rs.next()){
                Evidence evidence= DatabaseMapper.getEvidence(rs);
                log.debug(evidence.toString());
                if(evidence!=null)
                    evidenceList.add(evidence);
            }

        }catch(Exception e){
            log.error(e.toString());
        }
        return evidenceList;
    }

    @Override
    public void deleteById(Long id) {
    try(Connection con= DatabaseUtility.getConnection()){
        PreparedStatement stmt= con.prepareStatement("delete from evidence where id=?");
        stmt.setLong(1,id);
        stmt.executeUpdate();
    }catch (Exception e){
        log.error(e.toString());
    }
    }
}
