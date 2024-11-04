package ofofo.services;

import ofofo.data.models.Diary;
import ofofo.data.models.Entry;

import java.util.List;

public interface DiaryServices {
    Diary register(String username, String password);
    Diary findDiaryById(String diaryId);
    Diary login(String username, String password);
    void logOut(String username);
    void deleteDiary(String diaryId);
    long countDiaries();


    Entry createEntry(String diaryId, Entry entry);
    long countEntries(String diaryId);

}
