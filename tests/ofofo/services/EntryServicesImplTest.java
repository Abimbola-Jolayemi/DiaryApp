package ofofo.services;

import ofofo.data.models.Entry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EntryServicesImplTest {
    private EntryServices entryServices;

    @BeforeEach
    public void setUp() {
        entryServices = new EntryServicesImpl();
    }

    @Test
    public void testThatEntryServicesCanCreateEntries(){
        Entry entry1 = new Entry("Abimbola", 1, "New title", "new body");
        entryServices.createEntry(entry1);
        assertEquals(1, entryServices.noOfEntries());
    }

    @Test
    public void testThatEntryServicesCanUpdateEntries(){
        Entry entry1 = new Entry("Abimbola", 1, "New title", "new body");
        entryServices.createEntry(entry1);
        assertEquals("New title", entry1.getTitle());
        entry1.setTitle("New title2");
        entry1.setBody("New body2");
        assertEquals(1, entryServices.noOfEntries());
        assertEquals("New title2", entry1.getTitle());
    }

    @Test
    public void testThatEntryServicesCanGetAnEntries(){
        Entry entry1 = new Entry("Abimbola", 1, "New title", "new body");
        entryServices.createEntry(entry1);
        assertEquals(1, entryServices.noOfEntries());
        Entry entry2 = new Entry("Jolayemi", 2, "New title", "new body");
        entryServices.createEntry(entry2);
        assertEquals(2, entryServices.noOfEntries());
        assertEquals(entry2, entryServices.getEntryById(2));
    }

    @Test
    public void testThatEntryServicesCanDeleteAnEntry(){
        Entry entry1 = new Entry("Abimbola", 1, "New title", "new body");
        entryServices.createEntry(entry1);
        assertEquals(1, entryServices.noOfEntries());

        Entry entry2 = new Entry("Jolayemi", 2, "New title", "new body");
        entryServices.createEntry(entry2);
        assertEquals(2, entryServices.noOfEntries());

        entryServices.deleteEntry(2);
        assertEquals(1, entryServices.noOfEntries());
    }

}