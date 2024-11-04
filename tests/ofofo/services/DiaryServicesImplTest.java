package ofofo.services;

import ofofo.data.models.Diary;
import ofofo.data.models.Entry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DiaryServicesImplTest {

    private DiaryServices diaryServices;
    @BeforeEach
    public void setUp() {
        diaryServices = new DiaryServicesImpl();
    }

    @Test
    public void testCreateDiary() {
        assertEquals(0, diaryServices.countDiaries());
        Diary diary = diaryServices.register("Abimbola", "0000");
        assertEquals(1, diaryServices.countDiaries());
    }

    @Test
    public void testThatTwoDiariesAreCreated_oneIsDeleted_noOfDiariesIsOne() {
        Diary diary = diaryServices.register("Abimbola", "0000");
        Diary diary2 = diaryServices.register("Abimbola2", "0002");
        assertEquals(2, diaryServices.countDiaries());
        diaryServices.deleteDiary("Abimbola");
        assertEquals(1, diaryServices.countDiaries());
    }

    @Test
    public void testThatADiaryServicesCanLoginAUser_noEntryIsAddedToNewDiary_noOfEntriesForNewDiaryIsZero() {
        Diary diary = diaryServices.register("Abimbola", "0000");
        diaryServices.login("Abimbola", "0000");
        assertEquals(0, diaryServices.countEntries("Abimbola"));
    }

    @Test
    public void testThatDiaryCannotCreateEntriesForUserwhoIsNotLoggedIn() {
        Entry entry = new Entry("Abimbola", 1, "New Title", "New body");
        assertThrows(IllegalStateException.class, () -> diaryServices.createEntry("Abimbola", entry));
    }

    @Test
    public void testThatADiaryServicesCanLoginAUser_OneIsAddedToNewDiary_noOfEntriesForNewDiaryIsOne() {
        Diary diary = diaryServices.register("Abimbola", "0000");
        diaryServices.login("Abimbola", "0000");
        assertEquals(0, diaryServices.countEntries("Abimbola"));
        Entry entry = new Entry("Abimbola", 1, "New Title", "New body");
        diaryServices.createEntry("Abimbola", entry);
        assertEquals(1, diaryServices.countEntries("Abimbola"));
    }

    @Test
    public void testThatTwoUsersCreateTwoEntriesEach_NoOfEntriesPerUserIsTwo(){
        Diary diary = diaryServices.register("Abimbola", "0000");
        diaryServices.login("Abimbola", "0000");
        Diary diary2 = diaryServices.register("Jolayemi", "1111");
        diaryServices.login("Jolayemi", "1111");
        assertEquals(0, diaryServices.countEntries("Abimbola"));
        assertEquals(0, diaryServices.countEntries("Jolayemi"));

        Entry entry = new Entry("Abimbola", 1, "New Title", "New body");
        diaryServices.createEntry("Abimbola", entry);
        Entry entry2 = new Entry("Abimbola", 2, "Abimbola's second Title", "Another body");
        diaryServices.createEntry("Abimbola", entry2);
        assertEquals(2, diaryServices.countEntries("Abimbola"));

        Entry entry3 = new Entry("Jolayemi", 1, "Jolayemi's Title", "Jolayemi's body");
        diaryServices.createEntry("Jolayemi", entry3);

        Entry entry4 = new Entry("Jolayemi", 2, "Jolayemi's second Title", "Another body");
        diaryServices.createEntry("Jolayemi", entry4);
        assertEquals(2, diaryServices.countEntries("Jolayemi"));
    }

    @Test
    public void testThatDiaryCannotLogInAUserThatIsNotRegistered(){
        assertThrows(IllegalArgumentException.class, () -> diaryServices.login("Jolayemi", "9999"));
    }

    @Test
    public void testLogOutDiary() {
        Diary diary = diaryServices.register("Abimbola", "password");
        diaryServices.login("Abimbola", "password");
        assertTrue(diary.isLoggedIn());
        diaryServices.logOut("Abimbola");
        Diary updatedDiary = diaryServices.findDiaryById("Abimbola");
        assertFalse(updatedDiary.isLoggedIn());
    }

    @Test
    public void testThatDiaryCannotLogOutAgainAfterDiaryIsLoggedIn() {
        Diary diary = diaryServices.register("Abimbola", "password");
        assertThrows(IllegalStateException.class, () -> diaryServices.logOut("Abimbola"));
    }

    @Test
    public void testThatUserCannotCreateEntryWhenLoggedOut(){
        Diary diary = diaryServices.register("Abimbola", "password");
        diaryServices.login("Abimbola", "password");
        diaryServices.logOut("Abimbola");
        Entry entry = new Entry("Abimbola", 1, "New Title", "New body");
        assertThrows(IllegalStateException.class, () -> diaryServices.createEntry("Abimbola", entry));
    }
}
