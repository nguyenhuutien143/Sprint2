package finalproject.repository.jdbc;

import finalproject.model.CriminalCase;
import finalproject.model.Detective;
import finalproject.util.CaseStatus;
import finalproject.util.CaseType;
import finalproject.util.EmploymentStatus;
import finalproject.util.Rank;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
@Slf4j
class DetectiveJDBCRepositoryTest {

    @BeforeAll
    public static void InitData(){

        log.info("xoa data tu trc neu co");
        try(Connection con= DatabaseUtility.getConnection()) {
            PreparedStatement stmt= con.prepareStatement("delete from detective");
            stmt.executeUpdate();
        } catch (Exception e) {
            log.error(e.toString());
        }
    }
    @BeforeEach
    public void InitData4EachTest(){
        DetectiveJDBCRepository detectiveJDBCRepository= new DetectiveJDBCRepository();
        log.info("init data before each test");
        Detective d1= new Detective(1 , 1, LocalDateTime.now(), LocalDateTime.now(), null,
                null, Rank.ACTIVE, true, EmploymentStatus.ACTIVE,null, null);
        Detective d2= new Detective(2 , 1, LocalDateTime.now(), LocalDateTime.now(), null,
                null, Rank.ACTIVE, true, EmploymentStatus.ACTIVE,null, null);

        detectiveJDBCRepository.insertOrUpdate(d1);
        detectiveJDBCRepository.insertOrUpdate(d2);
    }
    @Test
    void insertOrUpdate() {
        DetectiveJDBCRepository detectiveJDBCRepository= new DetectiveJDBCRepository();
        Detective d1= new Detective(3 , 1, LocalDateTime.now(), LocalDateTime.now(), null,
                null, Rank.ACTIVE, true, EmploymentStatus.ACTIVE,null, null);

        detectiveJDBCRepository.insertOrUpdate(d1);

        assertEquals(3,detectiveJDBCRepository.findAll().size());
    }

    @Test
    void findById() {
        DetectiveJDBCRepository detectiveJDBCRepository= new DetectiveJDBCRepository();

        assertEquals(1,detectiveJDBCRepository.findById(1L).getId());
    }

    @Test
    void findAll() {
        DetectiveJDBCRepository detectiveJDBCRepository= new DetectiveJDBCRepository();

        assertEquals(2, detectiveJDBCRepository.findAll().size());
    }

    @Test
    void deleteById() {
        DetectiveJDBCRepository detectiveJDBCRepository= new DetectiveJDBCRepository();

        detectiveJDBCRepository.deleteById(1L);

        assertEquals(1, detectiveJDBCRepository.findAll().size());
    }
    @AfterEach
    public void clearDataEachTest() {
        log.info("clearDataEachTest");
        try(Connection con= DatabaseUtility.getConnection()) {
            PreparedStatement stmt= con.prepareStatement("delete from detective");
            stmt.executeUpdate();
        } catch (Exception e) {
            log.error(e.toString());
        }
    }

    @AfterAll
    public static void clearTest(){
        log.info("clearTest");

    }
}