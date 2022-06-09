package finalproject.model;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;
@Data
public abstract class AbstractEntity {
    private long id;
    private int version;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;


    public AbstractEntity(long id, int version, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.version = version;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    protected AbstractEntity() {
    }
}

