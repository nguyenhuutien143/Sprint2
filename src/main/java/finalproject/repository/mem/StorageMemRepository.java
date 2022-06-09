package finalproject.repository.mem;

import finalproject.model.Storage;
import finalproject.repository.IStorageRepository;

import java.util.*;

public class StorageMemRepository implements IStorageRepository {
    private Map<Long,Storage> storageMap= new HashMap<>();
    private static StorageMemRepository instance= new StorageMemRepository();
    public static StorageMemRepository getInstance(){
        return instance;
    }
    @Override
    public Storage insertOrUpdate(Storage storage) {
        storageMap.put(storage.getId(), storage);
        return storage;
    }

    @Override
    public Storage findById(Long id) {
        Optional<Storage> opt= Optional.ofNullable(storageMap.get(id));
        if(opt.isPresent()) return (Storage) opt.get();
        else return null;
    }

    @Override
    public List<Storage> findAll() {
        return new ArrayList<>(storageMap.values());
    }

    @Override
    public void deleteById(Long id) {
    storageMap.remove(id);
    }
}
