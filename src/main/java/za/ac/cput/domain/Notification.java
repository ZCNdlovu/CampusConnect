package za.ac.cput.domain;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Notification {
    @Id
    private Long notificationId;
    private String title;
    private String message;
    private String link;
    private boolean isRead;
    private LocalDateTime createdDate;
    private LocalDateTime readDate;
    private boolean isActive;

    @Enumerated(EnumType.STRING)
    private NotificationType type;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    protected Notification() {}

    private Notification(Builder builder) {
        this.notificationId = builder.notificationId;
        this.title = builder.title;
        this.message = builder.message;
        this.link = builder.link;
        this.isRead = builder.isRead;
        this.createdDate = builder.createdDate;
        this.readDate = builder.readDate;
        this.isActive = builder.isActive;
        this.type = builder.type;
        this.student = builder.student;
    }

    // Getters
    public Long getNotificationId() { return notificationId; }
    public String getTitle() { return title; }
    public String getMessage() { return message; }
    public String getLink() { return link; }
    public boolean isRead() { return isRead; }
    public LocalDateTime getCreatedDate() { return createdDate; }
    public LocalDateTime getReadDate() { return readDate; }
    public boolean isActive() { return isActive; }
    public NotificationType getType() { return type; }
    public Student getStudent() { return student; }

    public void markAsRead() {
        this.isRead = true;
        this.readDate = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "Notification{" +
                "notificationId=" + notificationId +
                ", title='" + title + '\'' +
                ", type=" + type +
                ", isRead=" + isRead +
                '}';
    }

    public static class Builder {
        private Long notificationId;
        private String title;
        private String message;
        private String link;
        private boolean isRead = false;
        private LocalDateTime createdDate = LocalDateTime.now();
        private LocalDateTime readDate;
        private boolean isActive = true;
        private NotificationType type;
        private Student student;

        public Builder setNotificationId(Long notificationId) {
            this.notificationId = notificationId;
            return this;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setMessage(String message) {
            this.message = message;
            return this;
        }

        public Builder setLink(String link) {
            this.link = link;
            return this;
        }

        public Builder setIsRead(boolean isRead) {
            this.isRead = isRead;
            return this;
        }

        public Builder setCreatedDate(LocalDateTime createdDate) {
            this.createdDate = createdDate;
            return this;
        }

        public Builder setReadDate(LocalDateTime readDate) {
            this.readDate = readDate;
            return this;
        }

        public Builder setIsActive(boolean isActive) {
            this.isActive = isActive;
            return this;
        }

        public Builder setType(NotificationType type) {
            this.type = type;
            return this;
        }

        public Builder setStudent(Student student) {
            this.student = student;
            return this;
        }

        public Builder copy(Notification notification) {
            this.notificationId = notification.notificationId;
            this.title = notification.title;
            this.message = notification.message;
            this.link = notification.link;
            this.isRead = notification.isRead;
            this.createdDate = notification.createdDate;
            this.readDate = notification.readDate;
            this.isActive = notification.isActive;
            this.type = notification.type;
            this.student = notification.student;
            return this;
        }

        public Notification build() {
            return new Notification(this);
        }
    }
}