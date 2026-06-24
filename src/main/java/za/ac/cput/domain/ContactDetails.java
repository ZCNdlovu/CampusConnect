package za.ac.cput.domain;

import jakarta.persistence.*;

@Entity
public class ContactDetails {
    @Id
    private Long contactId;
    private String cellNumber;
    private String alternativeNumber;
    private String emailAddress;
    private String whatsappNumber;
    private boolean isPrimary;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    protected ContactDetails() {}

    private ContactDetails(Builder builder) {
        this.contactId = builder.contactId;
        this.cellNumber = builder.cellNumber;
        this.alternativeNumber = builder.alternativeNumber;
        this.emailAddress = builder.emailAddress;
        this.whatsappNumber = builder.whatsappNumber;
        this.isPrimary = builder.isPrimary;
        this.student = builder.student;
    }

    // Getters
    public Long getContactId() { return contactId; }
    public String getCellNumber() { return cellNumber; }
    public String getAlternativeNumber() { return alternativeNumber; }
    public String getEmailAddress() { return emailAddress; }
    public String getWhatsappNumber() { return whatsappNumber; }
    public boolean isPrimary() { return isPrimary; }
    public Student getStudent() { return student; }

    @Override
    public String toString() {
        return "ContactDetails{" +
                "contactId=" + contactId +
                ", cellNumber='" + cellNumber + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                '}';
    }

    public static class Builder {
        private Long contactId;
        private String cellNumber;
        private String alternativeNumber;
        private String emailAddress;
        private String whatsappNumber;
        private boolean isPrimary = false;
        private Student student;

        public Builder setContactId(Long contactId) {
            this.contactId = contactId;
            return this;
        }

        public Builder setCellNumber(String cellNumber) {
            this.cellNumber = cellNumber;
            return this;
        }

        public Builder setAlternativeNumber(String alternativeNumber) {
            this.alternativeNumber = alternativeNumber;
            return this;
        }

        public Builder setEmailAddress(String emailAddress) {
            this.emailAddress = emailAddress;
            return this;
        }

        public Builder setWhatsappNumber(String whatsappNumber) {
            this.whatsappNumber = whatsappNumber;
            return this;
        }

        public Builder setIsPrimary(boolean isPrimary) {
            this.isPrimary = isPrimary;
            return this;
        }

        public Builder setStudent(Student student) {
            this.student = student;
            return this;
        }

        public Builder copy(ContactDetails contactDetails) {
            this.contactId = contactDetails.contactId;
            this.cellNumber = contactDetails.cellNumber;
            this.alternativeNumber = contactDetails.alternativeNumber;
            this.emailAddress = contactDetails.emailAddress;
            this.whatsappNumber = contactDetails.whatsappNumber;
            this.isPrimary = contactDetails.isPrimary;
            this.student = contactDetails.student;
            return this;
        }

        public ContactDetails build() {
            return new ContactDetails(this);
        }
    }
}