package za.ac.cput.domain;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Document {
    @Id
    private Long documentId;
    private String filePath;
    private String fileName;
    private String fileType;
    private long fileSize;
    private LocalDateTime uploadDate;
    private String verificationNotes;
    private LocalDateTime verificationDate;
    private String verifiedBy;

    @Enumerated(EnumType.STRING)
    private DocumentType documentType;

    @Enumerated(EnumType.STRING)
    private VerificationStatus verificationStatus;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    protected Document() {}

    private Document(Builder builder) {
        this.documentId = builder.documentId;
        this.filePath = builder.filePath;
        this.fileName = builder.fileName;
        this.fileType = builder.fileType;
        this.fileSize = builder.fileSize;
        this.uploadDate = builder.uploadDate;
        this.verificationNotes = builder.verificationNotes;
        this.verificationDate = builder.verificationDate;
        this.verifiedBy = builder.verifiedBy;
        this.documentType = builder.documentType;
        this.verificationStatus = builder.verificationStatus;
        this.student = builder.student;
    }

    // Getters
    public Long getDocumentId() { return documentId; }
    public String getFilePath() { return filePath; }
    public String getFileName() { return fileName; }
    public String getFileType() { return fileType; }
    public long getFileSize() { return fileSize; }
    public LocalDateTime getUploadDate() { return uploadDate; }
    public String getVerificationNotes() { return verificationNotes; }
    public LocalDateTime getVerificationDate() { return verificationDate; }
    public String getVerifiedBy() { return verifiedBy; }
    public DocumentType getDocumentType() { return documentType; }
    public VerificationStatus getVerificationStatus() { return verificationStatus; }
    public Student getStudent() { return student; }

    public void setStudent(Student student) { this.student = student; }
    public void setVerificationStatus(VerificationStatus status) { this.verificationStatus = status; }

    public void verify(String verifiedBy, VerificationStatus status, String notes) {
        this.verifiedBy = verifiedBy;
        this.verificationStatus = status;
        this.verificationNotes = notes;
        this.verificationDate = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "Document{" +
                "documentId=" + documentId +
                ", fileName='" + fileName + '\'' +
                ", documentType=" + documentType +
                ", verificationStatus=" + verificationStatus +
                '}';
    }

    public static class Builder {
        private Long documentId;
        private String filePath;
        private String fileName;
        private String fileType;
        private long fileSize;
        private LocalDateTime uploadDate = LocalDateTime.now();
        private String verificationNotes;
        private LocalDateTime verificationDate;
        private String verifiedBy;
        private DocumentType documentType;
        private VerificationStatus verificationStatus = VerificationStatus.PENDING;
        private Student student;

        public Builder setDocumentId(Long documentId) {
            this.documentId = documentId;
            return this;
        }

        public Builder setFilePath(String filePath) {
            this.filePath = filePath;
            return this;
        }

        public Builder setFileName(String fileName) {
            this.fileName = fileName;
            return this;
        }

        public Builder setFileType(String fileType) {
            this.fileType = fileType;
            return this;
        }

        public Builder setFileSize(long fileSize) {
            this.fileSize = fileSize;
            return this;
        }

        public Builder setUploadDate(LocalDateTime uploadDate) {
            this.uploadDate = uploadDate;
            return this;
        }

        public Builder setVerificationNotes(String verificationNotes) {
            this.verificationNotes = verificationNotes;
            return this;
        }

        public Builder setVerificationDate(LocalDateTime verificationDate) {
            this.verificationDate = verificationDate;
            return this;
        }

        public Builder setVerifiedBy(String verifiedBy) {
            this.verifiedBy = verifiedBy;
            return this;
        }

        public Builder setDocumentType(DocumentType documentType) {
            this.documentType = documentType;
            return this;
        }

        public Builder setVerificationStatus(VerificationStatus verificationStatus) {
            this.verificationStatus = verificationStatus;
            return this;
        }

        public Builder setStudent(Student student) {
            this.student = student;
            return this;
        }

        public Builder copy(Document document) {
            this.documentId = document.documentId;
            this.filePath = document.filePath;
            this.fileName = document.fileName;
            this.fileType = document.fileType;
            this.fileSize = document.fileSize;
            this.uploadDate = document.uploadDate;
            this.verificationNotes = document.verificationNotes;
            this.verificationDate = document.verificationDate;
            this.verifiedBy = document.verifiedBy;
            this.documentType = document.documentType;
            this.verificationStatus = document.verificationStatus;
            this.student = document.student;
            return this;
        }

        public Document build() {
            return new Document(this);
        }
    }
}