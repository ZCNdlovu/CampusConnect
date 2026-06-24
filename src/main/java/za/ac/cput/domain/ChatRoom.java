package za.ac.cput.domain;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class ChatRoom {
    @Id
    private Long chatRoomId;
    private String name;
    private LocalDateTime createdAt;
    private LocalDateTime lastActivity;
    private boolean isActive;

    @ManyToOne
    @JoinColumn(name = "student1_id")
    private Student student1;

    @ManyToOne
    @JoinColumn(name = "student2_id")
    private Student student2;

    @OneToMany(mappedBy = "chatRoom")
    private List<Message> messages;

    protected ChatRoom() {}

    private ChatRoom(Builder builder) {
        this.chatRoomId = builder.chatRoomId;
        this.name = builder.name;
        this.createdAt = builder.createdAt;
        this.lastActivity = builder.lastActivity;
        this.isActive = builder.isActive;
        this.student1 = builder.student1;
        this.student2 = builder.student2;
        this.messages = builder.messages;
    }

    // Getters
    public Long getChatRoomId() { return chatRoomId; }
    public String getName() { return name; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getLastActivity() { return lastActivity; }
    public boolean isActive() { return isActive; }
    public Student getStudent1() { return student1; }
    public Student getStudent2() { return student2; }
    public List<Message> getMessages() { return messages; }

    @Override
    public String toString() {
        return "ChatRoom{" +
                "chatRoomId=" + chatRoomId +
                ", name='" + name + '\'' +
                ", isActive=" + isActive +
                '}';
    }

    public static class Builder {
        private Long chatRoomId;
        private String name;
        private LocalDateTime createdAt = LocalDateTime.now();
        private LocalDateTime lastActivity = LocalDateTime.now();
        private boolean isActive = true;
        private Student student1;
        private Student student2;
        private List<Message> messages;

        public Builder setChatRoomId(Long chatRoomId) {
            this.chatRoomId = chatRoomId;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setCreatedAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Builder setLastActivity(LocalDateTime lastActivity) {
            this.lastActivity = lastActivity;
            return this;
        }

        public Builder setIsActive(boolean isActive) {
            this.isActive = isActive;
            return this;
        }

        public Builder setStudent1(Student student1) {
            this.student1 = student1;
            return this;
        }

        public Builder setStudent2(Student student2) {
            this.student2 = student2;
            return this;
        }

        public Builder setMessages(List<Message> messages) {
            this.messages = messages;
            return this;
        }

        public Builder copy(ChatRoom chatRoom) {
            this.chatRoomId = chatRoom.chatRoomId;
            this.name = chatRoom.name;
            this.createdAt = chatRoom.createdAt;
            this.lastActivity = chatRoom.lastActivity;
            this.isActive = chatRoom.isActive;
            this.student1 = chatRoom.student1;
            this.student2 = chatRoom.student2;
            this.messages = chatRoom.messages;
            return this;
        }

        public ChatRoom build() {
            return new ChatRoom(this);
        }
    }
}