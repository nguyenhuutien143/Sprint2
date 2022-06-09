package finalproject.repository.mem;

import finalproject.model.CriminalCase;
import finalproject.repository.ICriminalCaseRepository;

import java.util.*;

public class CriminalCaseMemRepository implements ICriminalCaseRepository {
    private Map<Long, CriminalCase> criminalCases= new HashMap<>();
    private static CriminalCaseMemRepository instance= new CriminalCaseMemRepository();

    public static CriminalCaseMemRepository getInstance() {
        return instance;
    }

    @Override
    public CriminalCase insertOrUpdate(CriminalCase criminalCase) {
        criminalCases.put(criminalCase.getId(), criminalCase);
        return criminalCase;
    }

    @Override
    public CriminalCase findById(Long id) {
        Optional<CriminalCase> opt= Optional.ofNullable(criminalCases.get(id));
        if(opt.isPresent()) return (CriminalCase) opt.get();
        else return null;
    }

    @Override
    public List<CriminalCase> findAll() {
        return new ArrayList<>(criminalCases.values());

    }

    @Override
    public void deleteById(Long id) {
        criminalCases.remove(id);
    }
}
