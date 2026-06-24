package za.ac.cput.domain;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class BookingConfirmation {
    @Id
    private String confirmationNumber;
    private String qrCode;
    private LocalDateTime confirmationTime;
    private String termsAndConditions;

    protected BookingConfirmation() {}

    private BookingConfirmation(Builder builder) {
        this.confirmationNumber = builder.confirmationNumber;
        this.qrCode = builder.qrCode;
        this.confirmationTime = builder.confirmationTime;
        this.termsAndConditions = builder.termsAndConditions;
    }

    // Getters
    public String getConfirmationNumber() { return confirmationNumber; }
    public String getQrCode() { return qrCode; }
    public LocalDateTime getConfirmationTime() { return confirmationTime; }
    public String getTermsAndConditions() { return termsAndConditions; }

    @Override
    public String toString() {
        return "BookingConfirmation{" +
                "confirmationNumber='" + confirmationNumber + '\'' +
                ", confirmationTime=" + confirmationTime +
                '}';
    }

    public static class Builder {
        private String confirmationNumber;
        private String qrCode;
        private LocalDateTime confirmationTime = LocalDateTime.now();
        private String termsAndConditions;

        public Builder setConfirmationNumber(String confirmationNumber) {
            this.confirmationNumber = confirmationNumber;
            return this;
        }

        public Builder setQrCode(String qrCode) {
            this.qrCode = qrCode;
            return this;
        }

        public Builder setConfirmationTime(LocalDateTime confirmationTime) {
            this.confirmationTime = confirmationTime;
            return this;
        }

        public Builder setTermsAndConditions(String termsAndConditions) {
            this.termsAndConditions = termsAndConditions;
            return this;
        }

        public Builder copy(BookingConfirmation bookingConfirmation) {
            this.confirmationNumber = bookingConfirmation.confirmationNumber;
            this.qrCode = bookingConfirmation.qrCode;
            this.confirmationTime = bookingConfirmation.confirmationTime;
            this.termsAndConditions = bookingConfirmation.termsAndConditions;
            return this;
        }

        public BookingConfirmation build() {
            return new BookingConfirmation(this);
        }
    }
}