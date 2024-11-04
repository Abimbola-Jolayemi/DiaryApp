package ofofo.data.repositories;

import ofofo.data.models.Diary;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class DiaryRepositoryImpl implements DiaryRepository {
    private List<Diary> diaries = new ArrayList<Diary>();

    @Override
    public long count() {
        return diaries.size();
    }

    @Override
    public void delete(Diary diary) {
        for(Diary diaryElement: diaries){
            if(diaryElement.equals(diary)){
                diaries.remove(diary);
                return;
            }
        }
        throw new NoSuchElementException("Invalid diary");
    }

    @Override
    public void deleteAll() {
        if(count() != 0){
            diaries.clear();
            return;
        }
        throw new NoSuchElementException("Empty diary repository");
    }

    @Override
    public void deleteById(String diaryId) {
        for(Diary diary: diaries){
            if(diary.getUsername().equals(diaryId)){
                diaries.remove(diary);
                return;
            }
        }
        throw new NoSuchElementException("Invalid diary");
    }

    @Override
    public boolean existsById(String diaryId) {
        for(Diary diary: diaries){
            if(diary.getUsername().equals(diaryId)){
                return true;
            }
        }
        return false;
    }

    @Override
    public Diary findById(String diaryId) {
        for(Diary diary: diaries){
            if(diary.getUsername().equals(diaryId)){
                return diary;
            }
        }
        return null;
    }

    @Override
    public Diary save(Diary diary) {
        for(int index = 0; index < diaries.size(); index++){
            if(diaries.get(index).getUsername().equals(diary.getUsername())){
                diaries.set(index, diary);
                return diary;
            }
        }
        diaries.add(diary);
        return diary;
    }
}
