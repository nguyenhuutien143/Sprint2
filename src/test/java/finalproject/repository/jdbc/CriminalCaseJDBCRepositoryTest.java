package finalproject.repository.jdbc;

import finalproject.model.CriminalCase;
import finalproject.repository.ICriminalCaseRepository;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CriminalCaseJDBCRepositoryTest {

    @Test
    void findAll() {
        //A1
        ICriminalCaseRepository criminalCaseRepository = new CriminalCaseJDBCRepository();

        //A2
        List<CriminalCase> criminalCases = criminalCaseRepository.findAll();

        //A3
        System.out.println(criminalCases);
    }

}