package ofofo.data.models;

public class Diary {
    private String username;
    private String password;
    private long lastEntryCount = 0;
    private boolean isLocked = true;
    private boolean isLoggedIn;

    public Diary(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        isLoggedIn = loggedIn;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getLastEntryCount() {
        return lastEntryCount;
    }

    public void setLastEntryCount(long lastEntryCount) {
        this.lastEntryCount = lastEntryCount;
    }

    public boolean isLocked() {
        return isLocked;
    }

    public void setLocked(boolean locked) {
        isLocked = locked;
    }
}
