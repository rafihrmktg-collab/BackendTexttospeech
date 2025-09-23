package com.example.tts;
import javax.persistence.*;

@Entity
@Table(name="text_entity")
public class TextEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String text;

    public TextEntity() {}
    public TextEntity(String text) { this.text = text; }
    public Long getId() { return id; }
    public String getText() { return text; }
    public void setText(String text) { this.text = text; }
}
