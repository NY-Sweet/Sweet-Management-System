package sweet.dev;

import java.time.LocalDate;

public class MessageSys {
    private String sender;
    private String receiver;
    private String content;
    private LocalDate date;
    private boolean read;

    public MessageSys(String sender, String receiver, String content, LocalDate date, boolean read) {
        this.sender = sender;
        this.receiver = receiver;
        this.content = content;
        this.date = date;
        this.read = read;
    }

    // Getters and setters
    public String getSender() { return sender; }
    public String getReceiver() { return receiver; }
    public String getContent() { return content; }
    public LocalDate getDate() { return date; }
    public void setRead(boolean read) { this.read = read; }
}
