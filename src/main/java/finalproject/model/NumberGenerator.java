package finalproject.model;

import java.util.Random;

public class NumberGenerator {
    private Random RANK;
    private String UPPER;
    private String DIGITS;

    public Random getRANK() {
        return RANK;
    }

    public void setRANK(Random RANK) {
        this.RANK = RANK;
    }

    public String getUPPER() {
        return UPPER;
    }

    public void setUPPER(String UPPER) {
        this.UPPER = UPPER;
    }

    public String getDIGITS() {
        return DIGITS;
    }

    public void setDIGITS(String DIGITS) {
        this.DIGITS = DIGITS;
    }

    public NumberGenerator(Random RANK, String UPPER, String DIGITS) {
        this.RANK = RANK;
        this.UPPER = UPPER;
        this.DIGITS = DIGITS;
    }
}
