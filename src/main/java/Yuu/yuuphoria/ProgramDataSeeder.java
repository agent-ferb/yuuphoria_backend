package Yuu.yuuphoria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
@Profile("dev")
public class ProgramDataSeeder implements CommandLineRunner {

    @Autowired
    private ProgramRepository programRepository;

    @Override
    public void run(String... args) throws Exception {
        // 检查数据库是否为空
        if (programRepository.count() == 0) {

            // 1. 创建第一个项目 (匹配你的 HTML)
            Program p1 = new Program(
                    "Image-to-Text Generation with Diffusion Models",
                    "This research pioneered image-to-text generation using custom stable diffusion techniques...",
                    "Jan 2024 - May 2024",
                    "Team Project",
                    "Name of Institution",
                    "In Progress",
                    "content",
                    "YOUR_GITHUB_LINK", // 替换成真实链接
                    "YOUR_PAPER_LINK", // 替换成真实链接
                    true // 链接可用
            );

            // 2. 创建第二个项目 (匹配你的 HTML)
            Program p2 = new Program(
                    "Real-time Crop Disease Detection System",
                    "Developing a lightweight, real-time system using React and FastAPI to help farmers detect common crop diseases...",
                    "Started June 2024",
                    "Personal Project",
                    "Name of Institution",
                    "In Progress",
                    "idea",
                    "#", // 暂无链接
                    null, // 没有论文链接
                    false // 链接不可用 (将显示 "coming soon")
            );

            // 3. 将它们保存到数据库
            programRepository.saveAll(List.of(p1, p2));
            System.out.println(">>> 数据库为空，已成功植入 2 个 Programs...");
        } else {
            System.out.println(">>> 数据库已有数据，跳过植入...");
        }
    }
}