package ofofo.services;

import ofofo.data.models.Entry;

public interface EntryServices {
    Entry createEntry(Entry entry);
    long noOfEntries();
    Entry getEntryById(long entryId);
    void deleteEntry(long entryId);
}
