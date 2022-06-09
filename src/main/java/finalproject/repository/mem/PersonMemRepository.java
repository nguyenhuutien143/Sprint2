package finalproject.repository.mem;

import finalproject.model.Person;
import finalproject.repository.IPersonRepository;

import java.util.*;

public class PersonMemRepository implements IPersonRepository {
    private Map<Long, Person> personMap= new HashMap<>();
    private static PersonMemRepository instance= new PersonMemRepository();
    public static PersonMemRepository getInstance(){
        return instance;
    }

    @Override
    public Person insertOrUpdate(Person person) {
        personMap.put(person.getId(), person);
        return person;
    }

    @Override
    public Person findById(Long id) {
        Optional<Person> opt= Optional.ofNullable(personMap.get(id));
        if(opt.isPresent()) return (Person) opt.get();
        else return null;
    }

    @Override
    public List<Person> findAll() {
        return new ArrayList<>(personMap.values());
    }

    @Override
    public void deleteById(Long id) {
    personMap.remove(id);
    }
}
