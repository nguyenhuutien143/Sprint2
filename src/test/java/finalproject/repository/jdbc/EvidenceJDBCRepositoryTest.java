package finalproject.repository.jdbc;

import com.zaxxer.hikari.util.ConcurrentBag;
import finalproject.model.Evidence;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
@Slf4j
class EvidenceJDBCRepositoryTest {
    @BeforeAll
    public static void initData(){
        log.info("delete all data from db");
        try(Connection con= DatabaseUtility.getConnection()){
            PreparedStatement stmt= con.prepareStatement("delete from evidence");
            stmt.executeUpdate();
        }catch (Exception e){
            log.error(e.toString());
        }
    }
    @BeforeEach
    public void initData4EachTest(){
        log.info("init data before each test");
        EvidenceJDBCRepository evidenceJDBCRepository= new EvidenceJDBCRepository();
        Evidence e1= new Evidence(1, 1, LocalDateTime.now(), LocalDateTime.now(), null,
                null, "1", "item name", "notes", true, null) ;
        Evidence e2= new Evidence(2, 1, LocalDateTime.now(), LocalDateTime.now(), null,
                null, "1", "item name", "notes", true, null) ;
        Evidence e3= new Evidence(3, 1, LocalDateTime.now(), LocalDateTime.now(), null,
                null, "1", "item name", "notes", true, null) ;
        Evidence e4= new Evidence(4, 1, LocalDateTime.now(), LocalDateTime.now(), null,
                null, "1", "item name", "notes", true, null) ;
        evidenceJDBCRepository.insertOrUpdate(e1);
        evidenceJDBCRepository.insertOrUpdate(e2);
        evidenceJDBCRepository.insertOrUpdate(e3);
        evidenceJDBCRepository.insertOrUpdate(e4);
    }

    @Test
    void insertOrUpdate() {
        EvidenceJDBCRepository evidenceJDBCRepository= new EvidenceJDBCRepository();
        Evidence e5= new Evidence(5, 1, LocalDateTime.now(), LocalDateTime.now(), null,
                null, "1", "item name", "notes", true, null) ;

        evidenceJDBCRepository.insertOrUpdate(e5);

        assertEquals(5, evidenceJDBCRepository.findAll().size());
    }

    @Test
    void findById() {
        EvidenceJDBCRepository evidenceJDBCRepository= new EvidenceJDBCRepository();

        assertEquals(1,evidenceJDBCRepository.findById(1L).getId());

    }

    @Test
    void findAll() {
        EvidenceJDBCRepository evidenceJDBCRepository= new EvidenceJDBCRepository();

        assertEquals(4, evidenceJDBCRepository.findAll().size());
    }

    @Test
    void deleteById() {
        EvidenceJDBCRepository evidenceJDBCRepository= new EvidenceJDBCRepository();

        evidenceJDBCRepository.deleteById(1L);

        assertEquals(3, evidenceJDBCRepository.findAll().size());
    }

//    @AfterEach
    @AfterEach
    public void afterEachTest(){
        try(Connection con= DatabaseUtility.getConnection()){
            PreparedStatement stmt= con.prepareStatement("delete from evidence");
            stmt.executeUpdate();
        }catch (Exception e){
            log.error(e.toString());
        }
    }
    @AfterAll
    public static void afterAll(){
        log.info("clean data");
    }
}