package ofofo.services;

import ofofo.data.models.Diary;
import ofofo.data.models.Entry;
import ofofo.data.repositories.DiaryRepository;
import ofofo.data.repositories.DiaryRepositoryImpl;

import java.util.List;

public class DiaryServicesImpl implements DiaryServices {
    private final EntryServices entryServices;
    private final DiaryRepository diaryRepository;
    private boolean isLoggedIn;
    private String currentUserDiaryId;

    public DiaryServicesImpl(EntryServices entryServices, DiaryRepository diaryRepository) {
        this.entryServices = entryServices;
        this.diaryRepository = diaryRepository;
        this.isLoggedIn = false;
    }


    public DiaryServicesImpl() {
        this(new EntryServicesImpl(), new DiaryRepositoryImpl());
    }

    @Override
    public Diary register(String username, String Password) {
        if(diaryRepository.findById(username) == null) {
            return diaryRepository.save(new Diary(username, Password));
        } else {
            throw new IllegalArgumentException("Username already exists");
        }
    }

    @Override
    public Diary findDiaryById(String diaryId) {
        Diary diary = diaryRepository.findById(diaryId);
        return diary;
    }

    @Override
    public Diary login(String username, String password) {
        Diary diary = diaryRepository.findById(username);
        if (diary != null) {
            if (diary.getPassword().equals(password)) {
                diary.setLocked(false);
                diary.setLoggedIn(true);
                diaryRepository.save(diary);
                return diary;
            } else {
                throw new IllegalArgumentException("Wrong password!!!");
            }
        } else {
            throw new IllegalArgumentException("User not found!!!");
        }
    }

    @Override
    public void logOut(String username) {
        Diary diary = diaryRepository.findById(username);
        if (diary == null) {
            throw new IllegalArgumentException("User not found!!!");
        }
        if (!diary.isLoggedIn()) {
            throw new IllegalStateException("User is already logged out");
        }
        diary.setLoggedIn(false);
        diaryRepository.save(diary);
    }



    @Override
    public void deleteDiary(String diaryId) {
        diaryRepository.deleteById(diaryId);
    }

    @Override
    public long countDiaries() {
        return diaryRepository.count();
    }

    @Override
    public Entry createEntry(String diaryId, Entry entry) {
        Diary diary = diaryRepository.findById(diaryId);
        if (diary == null || !diary.isLoggedIn()) {
            throw new IllegalStateException("User not logged in or unauthorized to create entries in this diary");
        }

        diary.setLastEntryCount(diary.getLastEntryCount() + 1);
        long entryId = diary.getLastEntryCount();
        entry.setEntryId(entryId);
        entry.setDiaryId(diaryId);

        diaryRepository.save(diary);
        return entryServices.createEntry(entry);
    }

    @Override
    public long countEntries(String diaryId){
        Diary diary = diaryRepository.findById(diaryId);
        if(diary == null) {
            throw new IllegalArgumentException("Diary not found");
        }
        return diary.getLastEntryCount();
    }
}
