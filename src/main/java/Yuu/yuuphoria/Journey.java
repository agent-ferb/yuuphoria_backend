package Yuu.yuuphoria;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import java.util.List;

@Entity
public class Journey {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    // "language" 字段 (用于 "zh-CN")
    private String language;

    @Column(columnDefinition = "TEXT")
    private String content;

    // 支持多张图片
    @ElementCollection
    private List<String> images;

    // --- Constructors, Getters, and Setters ---

    // Default constructor (required by JPA)
    public Journey() {
    }

    // Getters
    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    // "language" Getter/Setter
    public String getLanguage() {
        return language;
    }

    public String getContent() { // 9. Getter 重命名
        return content;
    }

    public List<String> getImages() { // 10. Getter 修改
        return images;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setContent(String content) { // 11. Setter 重命名
        this.content = content;
    }

    public void setImages(List<String> images) { // 12. Setter 修改
        this.images = images;
    }
}