package za.ac.cput.domain;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Message {
    @Id
    private Long messageId;
    private String content;
    private LocalDateTime sentDate;
    private LocalDateTime readDate;
    private boolean isRead;
    private boolean isActive;
    private Long replyToMessageId;

    @ManyToOne
    @JoinColumn(name = "sender_id")
    private Student sender;

    @ManyToOne
    @JoinColumn(name = "receiver_id")
    private Student receiver;

    @ManyToOne
    @JoinColumn(name = "chat_room_id")
    private ChatRoom chatRoom;

    protected Message() {}

    private Message(Builder builder) {
        this.messageId = builder.messageId;
        this.content = builder.content;
        this.sentDate = builder.sentDate;
        this.readDate = builder.readDate;
        this.isRead = builder.isRead;
        this.isActive = builder.isActive;
        this.replyToMessageId = builder.replyToMessageId;
        this.sender = builder.sender;
        this.receiver = builder.receiver;
        this.chatRoom = builder.chatRoom;
    }

    // Getters
    public Long getMessageId() { return messageId; }
    public String getContent() { return content; }
    public LocalDateTime getSentDate() { return sentDate; }
    public LocalDateTime getReadDate() { return readDate; }
    public boolean isRead() { return isRead; }
    public boolean isActive() { return isActive; }
    public Long getReplyToMessageId() { return replyToMessageId; }
    public Student getSender() { return sender; }
    public Student getReceiver() { return receiver; }
    public ChatRoom getChatRoom() { return chatRoom; }

    public void setSender(Student sender) { this.sender = sender; }

    public void markAsRead() {
        this.isRead = true;
        this.readDate = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "Message{" +
                "messageId=" + messageId +
                ", content='" + content + '\'' +
                ", isRead=" + isRead +
                '}';
    }

    public static class Builder {
        private Long messageId;
        private String content;
        private LocalDateTime sentDate = LocalDateTime.now();
        private LocalDateTime readDate;
        private boolean isRead = false;
        private boolean isActive = true;
        private Long replyToMessageId;
        private Student sender;
        private Student receiver;
        private ChatRoom chatRoom;

        public Builder setMessageId(Long messageId) {
            this.messageId = messageId;
            return this;
        }

        public Builder setContent(String content) {
            this.content = content;
            return this;
        }

        public Builder setSentDate(LocalDateTime sentDate) {
            this.sentDate = sentDate;
            return this;
        }

        public Builder setReadDate(LocalDateTime readDate) {
            this.readDate = readDate;
            return this;
        }

        public Builder setIsRead(boolean isRead) {
            this.isRead = isRead;
            return this;
        }

        public Builder setIsActive(boolean isActive) {
            this.isActive = isActive;
            return this;
        }

        public Builder setReplyToMessageId(Long replyToMessageId) {
            this.replyToMessageId = replyToMessageId;
            return this;
        }

        public Builder setSender(Student sender) {
            this.sender = sender;
            return this;
        }

        public Builder setReceiver(Student receiver) {
            this.receiver = receiver;
            return this;
        }

        public Builder setChatRoom(ChatRoom chatRoom) {
            this.chatRoom = chatRoom;
            return this;
        }

        public Builder copy(Message message) {
            this.messageId = message.messageId;
            this.content = message.content;
            this.sentDate = message.sentDate;
            this.readDate = message.readDate;
            this.isRead = message.isRead;
            this.isActive = message.isActive;
            this.replyToMessageId = message.replyToMessageId;
            this.sender = message.sender;
            this.receiver = message.receiver;
            this.chatRoom = message.chatRoom;
            return this;
        }

        public Message build() {
            return new Message(this);
        }
    }
}