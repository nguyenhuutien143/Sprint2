package finalproject.model;

import finalproject.util.TrackAction;

import java.time.LocalDateTime;

public class TrackEntry extends AbstractEntity{
    private LocalDateTime date;
    private Evidence evidence;
    private Detective detective;
    private TrackAction action;
    private String reason;

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Evidence getEvidence() {
        return evidence;
    }

    public void setEvidence(Evidence evidence) {
        this.evidence = evidence;
    }

    public Detective getDetective() {
        return detective;
    }

    public void setDetective(Detective detective) {
        this.detective = detective;
    }

    public TrackAction getAction() {
        return action;
    }

    public void setAction(TrackAction action) {
        this.action = action;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public TrackEntry(long id, int version, LocalDateTime createdAt, LocalDateTime modifiedAt, LocalDateTime date,
                      Evidence evidence, Detective detective, TrackAction action, String reason) {
        super(id, version, createdAt, modifiedAt);
        this.date = date;
        this.evidence = evidence;
        this.detective = detective;
        this.action = action;
        this.reason = reason;
    }
}
