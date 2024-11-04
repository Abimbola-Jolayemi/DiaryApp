package ofofo.data.repositories;

import ofofo.data.models.Entry;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class EntryRepositoryImpl implements EntryRepository {
    private List<Entry> entries = new ArrayList<Entry>();

    @Override
    public long count() {
        return entries.size();
    }

    @Override
    public void delete(Entry entry) {
        for(int index = 0; index < entries.size(); index++) {
            if(entries.get(index).equals(entry)) {
                entries.remove(index);
                return;
            }
        }
        throw new NoSuchElementException();
    }

    @Override
    public void deleteAll() {
        if(count() != 0){
            entries.clear();
            return;
        }
        throw new NoSuchElementException("Empty Repository");
    }

    @Override
    public void deleteById(long entryId) {
        for(int index = 0; index < entries.size(); index++) {
            if(entries.get(index).getEntryId() == entryId) {
                entries.remove(index);
                return;
            }
        }
        throw new IllegalArgumentException("Element not found");
    }

    @Override
    public boolean existsById(long entryId) {
        for (Entry entry : entries) {
            if(entry.getEntryId() == entryId) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Entry findById(long entryId) {
        for (Entry entry : entries) {
            if(entry.getEntryId() == entryId){
                return entry;
            }
        }
        throw new NoSuchElementException("Element not found");
    }

    @Override
    public Entry save(Entry entry) {
        for (int index = 0; index < entries.size(); index++) {
            if (entries.get(index).getEntryId() == entry.getEntryId()) {
                entries.set(index, entry);
                return entry;
            }
        }

        entries.add(entry);
        return entry;
    }

}
