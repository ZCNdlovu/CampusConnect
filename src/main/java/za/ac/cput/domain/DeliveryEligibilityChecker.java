package za.ac.cput.domain;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class DeliveryEligibilityChecker {
    @Id
    private Long checkerId;
    private String studentNumber;
    private String address;
    private boolean isEligible;
    private LocalDateTime checkDate;
    private String notes;

    @Enumerated(EnumType.STRING)
    private AccommodationType accommodationType;

    @Enumerated(EnumType.STRING)
    private DeliveryEligibility eligibilityResult;

    protected DeliveryEligibilityChecker() {}

    private DeliveryEligibilityChecker(Builder builder) {
        this.checkerId = builder.checkerId;
        this.studentNumber = builder.studentNumber;
        this.address = builder.address;
        this.isEligible = builder.isEligible;
        this.checkDate = builder.checkDate;
        this.notes = builder.notes;
        this.accommodationType = builder.accommodationType;
        this.eligibilityResult = builder.eligibilityResult;
    }

    // Getters
    public Long getCheckerId() { return checkerId; }
    public String getStudentNumber() { return studentNumber; }
    public String getAddress() { return address; }
    public boolean isEligible() { return isEligible; }
    public LocalDateTime getCheckDate() { return checkDate; }
    public String getNotes() { return notes; }
    public AccommodationType getAccommodationType() { return accommodationType; }
    public DeliveryEligibility getEligibilityResult() { return eligibilityResult; }

    @Override
    public String toString() {
        return "DeliveryEligibilityChecker{" +
                "checkerId=" + checkerId +
                ", studentNumber='" + studentNumber + '\'' +
                ", isEligible=" + isEligible +
                ", eligibilityResult=" + eligibilityResult +
                '}';
    }

    public static class Builder {
        private Long checkerId;
        private String studentNumber;
        private String address;
        private boolean isEligible = false;
        private LocalDateTime checkDate = LocalDateTime.now();
        private String notes;
        private AccommodationType accommodationType;
        private DeliveryEligibility eligibilityResult = DeliveryEligibility.NOT_ELIGIBLE;

        public Builder setCheckerId(Long checkerId) {
            this.checkerId = checkerId;
            return this;
        }

        public Builder setStudentNumber(String studentNumber) {
            this.studentNumber = studentNumber;
            return this;
        }

        public Builder setAddress(String address) {
            this.address = address;
            return this;
        }

        public Builder setIsEligible(boolean isEligible) {
            this.isEligible = isEligible;
            return this;
        }

        public Builder setCheckDate(LocalDateTime checkDate) {
            this.checkDate = checkDate;
            return this;
        }

        public Builder setNotes(String notes) {
            this.notes = notes;
            return this;
        }

        public Builder setAccommodationType(AccommodationType accommodationType) {
            this.accommodationType = accommodationType;
            return this;
        }

        public Builder setEligibilityResult(DeliveryEligibility eligibilityResult) {
            this.eligibilityResult = eligibilityResult;
            return this;
        }

        public Builder copy(DeliveryEligibilityChecker checker) {
            this.checkerId = checker.checkerId;
            this.studentNumber = checker.studentNumber;
            this.address = checker.address;
            this.isEligible = checker.isEligible;
            this.checkDate = checker.checkDate;
            this.notes = checker.notes;
            this.accommodationType = checker.accommodationType;
            this.eligibilityResult = checker.eligibilityResult;
            return this;
        }

        public DeliveryEligibilityChecker build() {
            return new DeliveryEligibilityChecker(this);
        }
    }
}