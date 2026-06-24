package za.ac.cput.domain;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class ReceiptVerification {
    @Id
    private Long verificationId;
    private String verificationCode;
    private LocalDateTime verificationDate;
    private String verifiedBy;
    private boolean isValid;
    private String notes;

    @ManyToOne
    @JoinColumn(name = "receipt_id")
    private Receipt receipt;

    protected ReceiptVerification() {}

    private ReceiptVerification(Builder builder) {
        this.verificationId = builder.verificationId;
        this.verificationCode = builder.verificationCode;
        this.verificationDate = builder.verificationDate;
        this.verifiedBy = builder.verifiedBy;
        this.isValid = builder.isValid;
        this.notes = builder.notes;
        this.receipt = builder.receipt;
    }

    // Getters
    public Long getVerificationId() { return verificationId; }
    public String getVerificationCode() { return verificationCode; }
    public LocalDateTime getVerificationDate() { return verificationDate; }
    public String getVerifiedBy() { return verifiedBy; }
    public boolean isValid() { return isValid; }
    public String getNotes() { return notes; }
    public Receipt getReceipt() { return receipt; }

    @Override
    public String toString() {
        return "ReceiptVerification{" +
                "verificationId=" + verificationId +
                ", verificationCode='" + verificationCode + '\'' +
                ", isValid=" + isValid +
                '}';
    }

    public static class Builder {
        private Long verificationId;
        private String verificationCode;
        private LocalDateTime verificationDate = LocalDateTime.now();
        private String verifiedBy;
        private boolean isValid = false;
        private String notes;
        private Receipt receipt;

        public Builder setVerificationId(Long verificationId) {
            this.verificationId = verificationId;
            return this;
        }

        public Builder setVerificationCode(String verificationCode) {
            this.verificationCode = verificationCode;
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

        public Builder setIsValid(boolean isValid) {
            this.isValid = isValid;
            return this;
        }

        public Builder setNotes(String notes) {
            this.notes = notes;
            return this;
        }

        public Builder setReceipt(Receipt receipt) {
            this.receipt = receipt;
            return this;
        }

        public Builder copy(ReceiptVerification verification) {
            this.verificationId = verification.verificationId;
            this.verificationCode = verification.verificationCode;
            this.verificationDate = verification.verificationDate;
            this.verifiedBy = verification.verifiedBy;
            this.isValid = verification.isValid;
            this.notes = verification.notes;
            this.receipt = verification.receipt;
            return this;
        }

        public ReceiptVerification build() {
            return new ReceiptVerification(this);
        }
    }
}