package Yuu.yuuphoria;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "programs")
public class Program {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    @Column(columnDefinition = "TEXT")
    private String description;

    private String date; // "Jan 2024 - May 2024"
    private String teamType; // "Team Project"
    private String institution;
    private String status; // "In Progress"
    private String contentTitle; // "content" 或 "idea"
    private String githubUrl;
    private String paperUrl;
    private boolean linksAvailable; // true 或 false

    @CreationTimestamp
    @Column(nullable = false, updatable = false) // 确保它不为空，且创建后不能修改
    private LocalDateTime createdAt;

    // --- 构造函数 ---
    public Program() {
    }

    // 方便植入数据的构造函数
    public Program(String title, String description, String date, String teamType, String institution, String status, String contentTitle, String githubUrl, String paperUrl, boolean linksAvailable) {
        this.title = title;
        this.description = description;
        this.date = date;
        this.teamType = teamType;
        this.institution = institution;
        this.status = status;
        this.contentTitle = contentTitle;
        this.githubUrl = githubUrl;
        this.paperUrl = paperUrl;
        this.linksAvailable = linksAvailable;
    }

    // --- Getters and Setters (JPA 需要它们) ---
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    // (IntelliJ IDEA 自动生成所有这些)
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }
    public String getTeamType() { return teamType; }
    public void setTeamType(String teamType) { this.teamType = teamType; }
    public String getInstitution() { return institution; }
    public void setInstitution(String institution) { this.institution = institution; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getContentTitle() { return contentTitle; }
    public void setContentTitle(String contentTitle) { this.contentTitle = contentTitle; }
    public String getGithubUrl() { return githubUrl; }
    public void setGithubUrl(String githubUrl) { this.githubUrl = githubUrl; }
    public String getPaperUrl() { return paperUrl; }
    public void setPaperUrl(String paperUrl) { this.paperUrl = paperUrl; }
    public boolean isLinksAvailable() { return linksAvailable; }
    public void setLinksAvailable(boolean linksAvailable) { this.linksAvailable = linksAvailable; }
}