package finalproject.repository.jdbc;

import finalproject.model.CriminalCase;
import finalproject.repository.ICriminalCaseRepository;
import finalproject.util.CaseStatus;
import finalproject.util.CaseType;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@Slf4j
class CriminalCaseJDBCRepositoryTest {
    @BeforeAll
    public static void InitData(){
        log.info("Xoa data ton tai tu trc neu co");
        CriminalCaseJDBCRepository criminalCaseJDBCRepository= new CriminalCaseJDBCRepository();
        try(Connection con= DatabaseUtility.getConnection()) {
            PreparedStatement stmt= con.prepareStatement("delete from criminal_case");
            stmt.executeUpdate();
        } catch (Exception e) {
            log.error(e.toString());
        }
    }
    @BeforeEach
    public void InitData4EachTest(){
        CriminalCaseJDBCRepository criminalCaseJDBCRepository= new CriminalCaseJDBCRepository();
        log.info("init data before each test");
        CriminalCase c1= new CriminalCase(1, 1, LocalDateTime.now(), LocalDateTime.now(), "1",
                CaseType.FELONY,"shortDescription","detailed des", CaseStatus.CLOSED, "notes",
                null, null, null) ;
        CriminalCase c2= new CriminalCase(2, 1, LocalDateTime.now(), LocalDateTime.now(), "1",
                CaseType.FELONY,"shortDescription","detailed des", CaseStatus.CLOSED, "notes",
                null, null, null) ;
        CriminalCase c3= new CriminalCase(3, 1, LocalDateTime.now(), LocalDateTime.now(), "1",
                CaseType.FELONY,"shortDescription","detailed des", CaseStatus.CLOSED, "notes",
                null, null, null) ;
        criminalCaseJDBCRepository.insertOrUpdate(c1);
        criminalCaseJDBCRepository.insertOrUpdate(c2);
        criminalCaseJDBCRepository.insertOrUpdate(c3);
    }
    @Test
    void findAll() {
        CriminalCaseJDBCRepository criminalCaseJDBCRepository= new CriminalCaseJDBCRepository();

        assertEquals(3,criminalCaseJDBCRepository.findAll().size());
    }

    @Test
    void findById() {
        ICriminalCaseRepository criminalCaseRepository = new CriminalCaseJDBCRepository();

        assertEquals(1, criminalCaseRepository.findById(1L).getId());
    }

    @Test
    void deleteById() {
        CriminalCaseJDBCRepository criminalCaseJDBCRepository= new CriminalCaseJDBCRepository();

        criminalCaseJDBCRepository.deleteById(3L);

        assertEquals(2, criminalCaseJDBCRepository.findAll().size());
    }

    @Test
    void insertOrUpdate() {
        CriminalCaseJDBCRepository criminalCaseJDBCRepository= new CriminalCaseJDBCRepository();
        CriminalCase c4= new CriminalCase(4, 1, LocalDateTime.now(), LocalDateTime.now(), "1",
                CaseType.FELONY,"shortDescription","detailed des", CaseStatus.CLOSED, "notes",
                null, null, null) ;


        criminalCaseJDBCRepository.insertOrUpdate(c4);

        assertEquals(4,criminalCaseJDBCRepository.findAll().size());
    }
    @AfterEach
    public void clearDataEachTest() {
        log.info("clearDataEachTest");
        CriminalCaseJDBCRepository criminalCaseJDBCRepository= new CriminalCaseJDBCRepository();
        try(Connection con= DatabaseUtility.getConnection()) {
            PreparedStatement stmt= con.prepareStatement("delete from criminal_case");
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


