package za.ac.cput.domain;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class VerificationLog {
    @Id
    private Long logId;
    private LocalDateTime verificationDate;
    private String notes;
    private String verifiedBy;

    @Enumerated(EnumType.STRING)
    private VerificationStatus previousStatus;

    @Enumerated(EnumType.STRING)
    private VerificationStatus newStatus;

    @ManyToOne
    @JoinColumn(name = "document_id")
    private Document document;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    protected VerificationLog() {}

    private VerificationLog(Builder builder) {
        this.logId = builder.logId;
        this.verificationDate = builder.verificationDate;
        this.notes = builder.notes;
        this.verifiedBy = builder.verifiedBy;
        this.previousStatus = builder.previousStatus;
        this.newStatus = builder.newStatus;
        this.document = builder.document;
        this.student = builder.student;
    }

    // Getters
    public Long getLogId() { return logId; }
    public LocalDateTime getVerificationDate() { return verificationDate; }
    public String getNotes() { return notes; }
    public String getVerifiedBy() { return verifiedBy; }
    public VerificationStatus getPreviousStatus() { return previousStatus; }
    public VerificationStatus getNewStatus() { return newStatus; }
    public Document getDocument() { return document; }
    public Student getStudent() { return student; }

    @Override
    public String toString() {
        return "VerificationLog{" +
                "logId=" + logId +
                ", verifiedBy='" + verifiedBy + '\'' +
                ", previousStatus=" + previousStatus +
                ", newStatus=" + newStatus +
                '}';
    }

    public static class Builder {
        private Long logId;
        private LocalDateTime verificationDate = LocalDateTime.now();
        private String notes;
        private String verifiedBy;
        private VerificationStatus previousStatus;
        private VerificationStatus newStatus;
        private Document document;
        private Student student;

        public Builder setLogId(Long logId) {
            this.logId = logId;
            return this;
        }

        public Builder setVerificationDate(LocalDateTime verificationDate) {
            this.verificationDate = verificationDate;
            return this;
        }

        public Builder setNotes(String notes) {
            this.notes = notes;
            return this;
        }

        public Builder setVerifiedBy(String verifiedBy) {
            this.verifiedBy = verifiedBy;
            return this;
        }

        public Builder setPreviousStatus(VerificationStatus previousStatus) {
            this.previousStatus = previousStatus;
            return this;
        }

        public Builder setNewStatus(VerificationStatus newStatus) {
            this.newStatus = newStatus;
            return this;
        }

        public Builder setDocument(Document document) {
            this.document = document;
            return this;
        }

        public Builder setStudent(Student student) {
            this.student = student;
            return this;
        }

        public Builder copy(VerificationLog log) {
            this.logId = log.logId;
            this.verificationDate = log.verificationDate;
            this.notes = log.notes;
            this.verifiedBy = log.verifiedBy;
            this.previousStatus = log.previousStatus;
            this.newStatus = log.newStatus;
            this.document = log.document;
            this.student = log.student;
            return this;
        }

        public VerificationLog build() {
            return new VerificationLog(this);
        }
    }
}