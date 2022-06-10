package finalproject.model;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;
import finalproject.util.CaseStatus;
import finalproject.util.CaseType;

public class CriminalCase extends AbstractEntity{
    private String number;
    private CaseType type;
    private String shortDescription;
    private String detailedDescription;
    private CaseStatus status;

    public CriminalCase(long id, int version, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        super(id, version, createdAt, modifiedAt);
    }

    public String getDetailedDescription() {
        return detailedDescription;
    }

    public void setDetailedDescription(String detailedDescription) {
        this.detailedDescription = detailedDescription;
    }


    private String notes;
    private Set<Evidence> evidenceSet;
    private Detective leadInvestigator;
    private Set<Detective> assigned;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public CaseType getType() {
        return type;
    }

    public void setType(CaseType type) {
        this.type = type;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public CaseStatus getStatus() {
        return status;
    }

    public void setStatus(CaseStatus status) {
        this.status = status;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Set<Evidence> getEvidenceSet() {
        return evidenceSet;
    }

    public void setEvidenceSet(Set<Evidence> evidenceSet) {
        this.evidenceSet = evidenceSet;
    }

    public Detective getLeadInvestigator() {
        return leadInvestigator;
    }

    public void setLeadInvestigator(Detective leadInvestigator) {
        this.leadInvestigator = leadInvestigator;
    }

    public Set<Detective> getAssigned() {
        return assigned;
    }

    public void setAssigned(Set<Detective> assigned) {
        this.assigned = assigned;
    }

    public CriminalCase() {

    }

    public CriminalCase(long id, int version, LocalDateTime createdAt, LocalDateTime modifiedAt, String number,
                        CaseType type, String shortDescription,String detailedDescription, CaseStatus status, String notes, Set<Evidence> evidenceSet, Detective leadInvestigator, Set<Detective> assigned) {
        super(id, version, createdAt, modifiedAt);
        this.number = number;
        this.type = type;
        this.shortDescription = shortDescription;
        this.detailedDescription=detailedDescription;
        this.status = status;
        this.notes = notes;
        this.evidenceSet = evidenceSet;
        this.leadInvestigator = leadInvestigator;
        this.assigned = assigned;
    }

    @Override
    public String toString() {
        return "CriminalCase{" +
                "number='" + number + '\'' +
                ", type=" + type +
                ", shortDescription='" + shortDescription + '\'' +
                ", status=" + status +
                ", notes='" + notes + '\'' +
                ", evidenceSet=" + evidenceSet +
                ", leadInvestigator=" + leadInvestigator +
                ", assigned=" + assigned +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        CriminalCase that = (CriminalCase) o;
        return Objects.equals(number, that.number) && type == that.type && Objects.equals(shortDescription, that.shortDescription) && status == that.status && Objects.equals(notes, that.notes) && Objects.equals(evidenceSet, that.evidenceSet) && Objects.equals(leadInvestigator, that.leadInvestigator) && Objects.equals(assigned, that.assigned);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), number, type, shortDescription, status, notes, evidenceSet, leadInvestigator, assigned);
    }
}
