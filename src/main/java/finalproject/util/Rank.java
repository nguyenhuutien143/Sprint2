package finalproject.util;

public enum Rank {
    ACTIVE, SUSPENDED, VACATION,UNDER_INVESTIGATION, RETIRED;
    private int code;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
