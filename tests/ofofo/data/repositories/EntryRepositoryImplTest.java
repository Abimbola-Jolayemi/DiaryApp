package ofofo.data.repositories;

import ofofo.data.models.Entry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class EntryRepositoryImplTest {
    private EntryRepository entryRepository;

    @BeforeEach
    public void startWith() {
        entryRepository = new EntryRepositoryImpl();
    }

    @Test
    public void testThatEntryRepositoryIsEmpty_noEntryHasBeenAdded() {
        assertEquals(0, entryRepository.count());
    }

    @Test
    public void testThatEntryRepositoryIsNotEmpty_entryHasBeenAdded() {
        assertEquals(0, entryRepository.count());
        Entry entry = new Entry("Abimbola", 1, "Title1", "Body1");
        entryRepository.save(entry);
        assertEquals(1, entryRepository.count());
    }

    @Test
    public void testThatEntryRepositoryIsNotEmpty_TwoEntriesHasBeenAdded() {
        Entry entry = new Entry("Abimbola", 1, "Title1", "Body1");
        entryRepository.save(entry);
        Entry entry2 = new Entry("Abimbola",2,  "Title1", "Body1");
        entryRepository.save(entry2);
        assertEquals(2, entryRepository.count());
    }

    @Test
    public void testThatAnEntryInTheRepositoryCanBeUpdated() {
        Entry entry = new Entry("Abimbola", 1, "Title1", "Body1");
        entryRepository.save(entry);
        Entry entry2 = new Entry("Abimbola",2,  "Title1", "Body1");
        entryRepository.save(entry2);
        assertEquals(2, entryRepository.count());
        entry.setTitle("New Title1");
        entry.setBody("New Body1");
        entryRepository.save(entry);
        assertEquals(2, entryRepository.count());
        assertEquals("New Title1", entry.getTitle());
    }

    @Test
    public void testThatNoOfEntriesIsOne_twoEntriesAreAdded_OneIsDeleted() {
        Entry entry = new Entry("Abimbola",  1, "Title1", "Body1");
        entryRepository.save(entry);
        Entry entry2 = new Entry("Abimbola", 2,   "Title1", "Body1");
        entryRepository.save(entry2);
        assertEquals(2, entryRepository.count());
        entryRepository.delete(entry);
        assertEquals(1, entryRepository.count());
    }

    @Test
    public void testThatNoOfEntriesIsOne_ThreeEntriesAreAdded_TwoEntriesAreDeleted() {
        Entry entry = new Entry("Abimbola",  1, "Title1", "Body1");
        entryRepository.save(entry);
        Entry entry2 = new Entry("Abimbola", 2, "Title1", "Body1");
        entryRepository.save(entry2);
        Entry entry3 = new Entry("Abimbola", 3,  "Title1", "Body1");
        entryRepository.save(entry3);
        assertEquals(3, entryRepository.count());
        entryRepository.delete(entry);
        assertEquals(2, entryRepository.count());
        entryRepository.delete(entry2);
        assertEquals(1, entryRepository.count());
    }

    @Test
    public void testThatEntryRepositoryCannotDeleteAnEntryThatIsNotSavedInTheRepository() {
        Entry entry = new Entry("Abimbola", 1, "Title1", "Body1");
        entryRepository.save(entry);
        Entry entry2 = new Entry("Abimbola", 2, "Title1", "Body1");
        entryRepository.save(entry2);
        Entry entry3 = new Entry("Abimbola", 3, "Title1", "Body1");
        entryRepository.save(entry3);
        assertEquals(3, entryRepository.count());
        entryRepository.delete(entry);
        assertEquals(2, entryRepository.count());
        Entry entry4 = new Entry("Abimbola", 4, "Title4", "Body4");
        assertThrows(NoSuchElementException.class, () -> entryRepository.delete(entry4));
    }


    @Test
    public void testThatRepositoryCanFindEntryById(){
        Entry entry = new Entry("Abimbola",  1, "Title1", "Body1");
        entryRepository.save(entry);
        Entry entry2 = new Entry("Abimbola", 2,   "Title1", "Body1");
        entryRepository.save(entry2);
        Entry entry3 = new Entry("Abimbola", 3,  "Title1", "Body1");
        entryRepository.save(entry3);
        assertEquals(entry3, entryRepository.findById(3));
    }

    @Test
    public void testThatRepositoryCannotFindEntryOfAnInexistingId(){
        Entry entry = new Entry("Abimbola",  1, "Title1", "Body1");
        entryRepository.save(entry);
        Entry entry2 = new Entry("Abimbola", 2,   "Title1", "Body1");
        entryRepository.save(entry2);
        Entry entry3 = new Entry("Abimbola", 3,  "Title1", "Body1");
        assertThrows(NoSuchElementException.class, () -> entryRepository.findById(3));
    }

    @Test
    public void testThatRepositoryCanCheckIfAnEntryExists(){
        Entry entry = new Entry("Abimbola",  1, "Title1", "Body1");
        entryRepository.save(entry);
        Entry entry2 = new Entry("Abimbola", 2,   "Title1", "Body1");
        entryRepository.save(entry2);
        Entry entry3 = new Entry("Abimbola", 3,  "Title1", "Body1");
        entryRepository.save(entry3);
        assertTrue(entryRepository.existsById(2));
        assertFalse(entryRepository.existsById(10));
    }

    @Test
    public void testThatRepositoryCanDeleteById_threeEntriesAreAdded_twoEntriesAreDeleted() {
        Entry entry = new Entry("Abimbola",  1, "Title1", "Body1");
        entryRepository.save(entry);
        Entry entry2 = new Entry("Abimbola", 2,   "Title1", "Body1");
        entryRepository.save(entry2);
        Entry entry3 = new Entry("Abimbola", 3,  "Title1", "Body1");
        entryRepository.save(entry3);
        entryRepository.deleteById(2);
        assertEquals(2, entryRepository.count());
        entryRepository.deleteById(3);
        assertEquals(1, entryRepository.count());
    }

    @Test
    public void testThatRepositoryCannotDeleteAnInexistingID(){
        Entry entry = new Entry("Abimbola",  1, "Title1", "Body1");
        entryRepository.save(entry);
        assertThrows(IllegalArgumentException.class, () -> entryRepository.deleteById(3));
    }

    @Test
    public void testThatRepositoryCannotFindEntryOfADeletedId(){
        Entry entry = new Entry("Abimbola",  1, "Title1", "Body1");
        entryRepository.save(entry);
        Entry entry2 = new Entry("Abimbola", 2, "Title1", "Body1");
        entryRepository.save(entry2);
        Entry entry3 = new Entry("Abimbola", 3,  "Title1", "Body1");
        entryRepository.save(entry3);
        assertEquals(3, entryRepository.count());
        entryRepository.delete(entry);
        assertEquals(2, entryRepository.count());
        assertThrows(NoSuchElementException.class, () -> entryRepository.findById(1));
    }

    @Test
    public void testThatRepositoryCanDeleteAllEntries(){
        Entry entry = new Entry("Abimbola",  1, "Title1", "Body1");
        entryRepository.save(entry);
        Entry entry2 = new Entry("Abimbola", 2,   "Title1", "Body1");
        entryRepository.save(entry2);
        Entry entry3 = new Entry("Abimbola", 3,  "Title1", "Body1");
        entryRepository.save(entry3);
        assertEquals(3, entryRepository.count());
        entryRepository.deleteAll();
        assertEquals(0, entryRepository.count());
    }

    @Test
    public void testThatAnEmptyEntryRepositoryCannotDeleteAnyEntry(){
        assertThrows(NoSuchElementException.class, () -> entryRepository.deleteAll());
    }
}