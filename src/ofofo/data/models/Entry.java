package ofofo.data.models;

import java.time.LocalDate;

public class Entry {
    private long entryId;
    private String title;
    private String body;
    private LocalDate date = LocalDate.now();
    private String diaryId;

    public Entry(String diaryId, long entryId, String title, String body) {
        this.entryId = entryId;
        this.title = title;
        this.body = body;
        this.diaryId = diaryId;
    }

    public void setEntryId(long entryId) {
        this.entryId = entryId;
    }

    public long getEntryId() {
        return entryId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDiaryId() {
        return diaryId;
    }

    public void setDiaryId(String diaryId) {
        this.diaryId = diaryId;
    }
}
