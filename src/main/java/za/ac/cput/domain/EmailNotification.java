package za.ac.cput.domain;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class EmailNotification {
    @Id
    private Long emailId;
    private String recipientEmail;
    private String subject;
    private String body;
    private LocalDateTime sentDate;
    private boolean isSent;
    private String attachmentUrl;

    @Enumerated(EnumType.STRING)
    private NotificationType notificationType;

    protected EmailNotification() {}

    private EmailNotification(Builder builder) {
        this.emailId = builder.emailId;
        this.recipientEmail = builder.recipientEmail;
        this.subject = builder.subject;
        this.body = builder.body;
        this.sentDate = builder.sentDate;
        this.isSent = builder.isSent;
        this.attachmentUrl = builder.attachmentUrl;
        this.notificationType = builder.notificationType;
    }

    // Getters
    public Long getEmailId() { return emailId; }
    public String getRecipientEmail() { return recipientEmail; }
    public String getSubject() { return subject; }
    public String getBody() { return body; }
    public LocalDateTime getSentDate() { return sentDate; }
    public boolean isSent() { return isSent; }
    public String getAttachmentUrl() { return attachmentUrl; }
    public NotificationType getNotificationType() { return notificationType; }

    public void markAsSent() {
        this.isSent = true;
        this.sentDate = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "EmailNotification{" +
                "emailId=" + emailId +
                ", recipientEmail='" + recipientEmail + '\'' +
                ", subject='" + subject + '\'' +
                ", isSent=" + isSent +
                '}';
    }

    public static class Builder {
        private Long emailId;
        private String recipientEmail;
        private String subject;
        private String body;
        private LocalDateTime sentDate = LocalDateTime.now();
        private boolean isSent = false;
        private String attachmentUrl;
        private NotificationType notificationType;

        public Builder setEmailId(Long emailId) {
            this.emailId = emailId;
            return this;
        }

        public Builder setRecipientEmail(String recipientEmail) {
            this.recipientEmail = recipientEmail;
            return this;
        }

        public Builder setSubject(String subject) {
            this.subject = subject;
            return this;
        }

        public Builder setBody(String body) {
            this.body = body;
            return this;
        }

        public Builder setSentDate(LocalDateTime sentDate) {
            this.sentDate = sentDate;
            return this;
        }

        public Builder setIsSent(boolean isSent) {
            this.isSent = isSent;
            return this;
        }

        public Builder setAttachmentUrl(String attachmentUrl) {
            this.attachmentUrl = attachmentUrl;
            return this;
        }

        public Builder setNotificationType(NotificationType notificationType) {
            this.notificationType = notificationType;
            return this;
        }

        public Builder copy(EmailNotification email) {
            this.emailId = email.emailId;
            this.recipientEmail = email.recipientEmail;
            this.subject = email.subject;
            this.body = email.body;
            this.sentDate = email.sentDate;
            this.isSent = email.isSent;
            this.attachmentUrl = email.attachmentUrl;
            this.notificationType = email.notificationType;
            return this;
        }

        public EmailNotification build() {
            return new EmailNotification(this);
        }
    }
}