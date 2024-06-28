package com.pratham.journalApp.entity;


import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Document(collection = "journal_entries")
//@Data is Equivalent to @Getter @Setter @RequiredArgsConstructor @ToString @EqualsAndHashCode.
@Data
// In @Data RequiredArgsConstructor in it there is no NoArgsConstructor,
// this NoArgsConstructor required in deserialization.(deserialization)-> means json to pojo
@NoArgsConstructor
public class JournalEntry {
    @Id
    private ObjectId id;
    @NonNull
    private String title;

    private String content;

    private LocalDateTime date;

    // Using The Lombok annotation @Getter, @Setter it reduces to write getter, setter and constructor explicitly
/*    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }*/
}
