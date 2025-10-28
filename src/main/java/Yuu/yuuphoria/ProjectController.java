package Yuu.yuuphoria;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 这是一个 API "控制器".
 * @RestController 告诉 Spring: "这个类是用来处理 API 请求的，请把它的返回值自动转成 JSON"
 */
@RestController

/**
 * @RequestMapping 告诉 Spring: "这个类里的所有 API 都在 /api/projects 路径下"
 */
@RequestMapping("/api/projects")

/**
 * @CrossOrigin 告诉 Spring: "允许所有来源(origins)的前端访问我"
 * 这是*极其重要*的一步，否则你的 Cloudflare 前端会因为 CORS 策略而被阻止
 */
@CrossOrigin(origins = "*")

public class ProjectController {

    @GetMapping
    public List<Project> getProjects() {
        Project p1 = new Project(
                1L,
                "我的第一个网站",
                "这是用 HTML 和 CSS 做的，用来展示我的个人信息。",
                "https://images.unsplash.com/photo-1551288049-bebda4e38f71"
        );

        Project p2 = new Project(
                2L,
                "AI 聊天机器人",
                "一个使用 LLM 模型实现的智能问答助手。",
                "https://images.unsplash.com/photo-1526628953301-3e589a6a8b74"
        );

        return List.of(p1, p2);
    }
}

record Project(
        Long id,
        String title,
        String description,
        String imageUrl
) {}