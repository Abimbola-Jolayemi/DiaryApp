package ofofo.data.repositories;

import ofofo.data.models.Diary;
import ofofo.data.models.Entry;

public interface DiaryRepository {
    long count();
    void delete(Diary diary);
    void deleteAll();
    void deleteById(String diaryId);
    boolean existsById(String diaryId);
    Diary findById(String diaryId);
    Diary save(Diary diary);
}
