package ofofo.services;

import ofofo.data.models.Entry;
import ofofo.data.repositories.EntryRepository;
import ofofo.data.repositories.EntryRepositoryImpl;

public class EntryServicesImpl implements EntryServices {
    private final EntryRepository entryRepository = new EntryRepositoryImpl();

    @Override
    public Entry createEntry(Entry entry) {
        return entryRepository.save(entry);
    }

    @Override
    public long noOfEntries() {
        return entryRepository.count();
    }

    @Override
    public Entry getEntryById(long entryId) {
        return entryRepository.findById(entryId);
    }

    @Override
    public void deleteEntry(long entryId) {
        entryRepository.deleteById(entryId);
    }
}
