package ofofo.data.repositories;

import ofofo.data.models.Entry;

public interface EntryRepository {
    long count();
    void delete(Entry entry);
    void deleteAll();
    void deleteById(long entryId);
    boolean existsById(long entryId);
    Entry findById(long entryId);
    Entry save(Entry entry);
}
