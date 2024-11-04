package ofofo.data.repositories;

import ofofo.data.models.Diary;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class DiaryRepositoryImplTest {
    private DiaryRepository diaryRepository;

    @BeforeEach
    public void setUp() {
        diaryRepository = new DiaryRepositoryImpl();
    }

    @Test
    public void testThatDiaryRepositoryIsEmpty(){
        assertEquals(0, diaryRepository.count());
    }

    @Test
    public void testThatDiaryRepositoryIsNotEmpty_oneDiaryIsAdded(){
        Diary diary = new Diary("Abimbola", "0000");
        diaryRepository.save(diary);
        assertEquals(1, diaryRepository.count());
    }

    @Test
    public void testThatDiaryRepositoryIsNotEmpty_twoDiariesAreAdded(){
        Diary diary1 = new Diary("Abimbola", "0000");
        Diary diary2 = new Diary("Jolayemi", "1111");
        diaryRepository.save(diary1);
        diaryRepository.save(diary2);
        assertEquals(2, diaryRepository.count());
    }

    @Test
    public void testThatAnExistingDiaryCanBeUpdated_UserNameOrPasswordChanges(){
        Diary diary1 = new Diary("Abimbola", "0000");
        Diary diary2 = new Diary("Jolayemi", "1111");
        diaryRepository.save(diary1);
        diaryRepository.save(diary2);
        assertEquals(2, diaryRepository.count());
        diary1.setUsername("Comfort");
        diary1.setPassword("123");
        diaryRepository.save(diary1);
        assertEquals(2, diaryRepository.count());
        assertEquals("Comfort", diary1.getUsername());
        assertEquals("123", diary1.getPassword());
    }

    @Test
    public void testThatDiaryRepositoryHasOneDiary_TwoDiariesAreAdded_OneDiaryIsDeleted(){
        Diary diary1 = new Diary("Abimbola", "0000");
        diaryRepository.save(diary1);
        Diary diary2 = new Diary("Jolayemi", "1111");
        diaryRepository.save(diary2);
        assertEquals(2, diaryRepository.count());
        diaryRepository.delete(diary1);
        assertEquals(1, diaryRepository.count());
    }

    @Test
    public void testThatDiaryRepositoryCannotDeletedInexistingDiary(){
        Diary diary1 = new Diary("Abimbola", "0000");
        diaryRepository.save(diary1);
        Diary diary2 = new Diary("Jolayemi", "1111");
        diaryRepository.save(diary2);
        assertEquals(2, diaryRepository.count());
        Diary diary4 = new Diary("UnsavedDiary", "UnsavedDiary");
        assertThrows(NoSuchElementException.class, () -> diaryRepository.delete(diary4));
    }

    @Test
    public void testThatDiaryRepositoryCanDeleteAllDiaries(){
        Diary diary1 = new Diary("Abimbola", "0000");
        diaryRepository.save(diary1);
        Diary diary2 = new Diary("Jolayemi", "1111");
        diaryRepository.save(diary2);
        assertEquals(2, diaryRepository.count());
        diaryRepository.deleteAll();
        assertEquals(0, diaryRepository.count());
    }

    @Test
    public void testThatIfDiaryRepositoryIsEmpty_RepositoryCannotDeleteDiary(){
        assertThrows(NoSuchElementException.class, () -> diaryRepository.deleteAll());
    }

    @Test
    public void testThatDiaryRepositoryCanDeleteDiaryByDiaryId(){
        Diary diary1 = new Diary("Abimbola", "0000");
        diaryRepository.save(diary1);
        Diary diary2 = new Diary("Jolayemi", "1111");
        diaryRepository.save(diary2);
        assertEquals(2, diaryRepository.count());
        diaryRepository.deleteById("Abimbola");
        assertEquals(1, diaryRepository.count());
    }

    @Test
    public void testThatDiaryRepositoryCannotDeleteInexistingDiaryId(){
        Diary diary1 = new Diary("Abimbola", "0000");
        diaryRepository.save(diary1);
        Diary diary2 = new Diary("Jolayemi", "1111");
        diaryRepository.save(diary2);
        assertEquals(2, diaryRepository.count());
        Diary diary3 = new Diary("UnsavedDiary", "UnsavedDiary");
        assertThrows(NoSuchElementException.class, () -> diaryRepository.deleteById("UnsavedDiary"));
    }

    @Test
    public void testThatDiaryRepositoryCanCheckIfADiaryExists(){
        Diary diary1 = new Diary("Abimbola", "0000");
        diaryRepository.save(diary1);
        Diary diary2 = new Diary("Jolayemi", "1111");
        diaryRepository.save(diary2);
        assertEquals(2, diaryRepository.count());
        assertTrue(diaryRepository.existsById("Abimbola"));
    }

    @Test
    public void testThatDiaryRepositoryCanCheckIfADeletedDiaryStillExist(){
        Diary diary1 = new Diary("Abimbola", "0000");
        diaryRepository.save(diary1);
        Diary diary2 = new Diary("Jolayemi", "1111");
        diaryRepository.save(diary2);
        assertEquals(2, diaryRepository.count());
        diaryRepository.deleteById("Abimbola");
        assertFalse(diaryRepository.existsById("Abimbola"));
    }

    @Test
    public void testThatRepositoryCanFindADiaryByItsId(){
        Diary diary1 = new Diary("Abimbola", "0000");
        diaryRepository.save(diary1);
        Diary diary2 = new Diary("Jolayemi", "1111");
        diaryRepository.save(diary2);
        assertEquals(2, diaryRepository.count());
        assertEquals(diary1, diaryRepository.findById("Abimbola"));
    }

    @Test
    public void testThatRepositoryCannotFindADeletedDiary_deletedDiaryDoesNotExist(){
        Diary diary1 = new Diary("Abimbola", "0000");
        diaryRepository.save(diary1);
        Diary diary2 = new Diary("Jolayemi", "1111");
        diaryRepository.save(diary2);
        assertEquals(2, diaryRepository.count());
        diaryRepository.deleteById("Abimbola");
        assertNull(diaryRepository.findById("Abimbola"));
    }
}