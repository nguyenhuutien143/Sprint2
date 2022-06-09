package finalproject.repository;

import java.util.List;

public interface IRepository<T>{
    T insertOrUpdate(T t);
    T findById(Long id);
    List<T> findAll();
    void deleteById(Long id);
}
