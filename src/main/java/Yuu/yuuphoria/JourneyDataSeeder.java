package Yuu.yuuphoria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
@Profile("dev")
public class JourneyDataSeeder implements CommandLineRunner {

    @Autowired
    private JourneyRepository journeyRepository;

    @Override
    public void run(String... args) throws Exception {
        loadJourneyData();
    }

    private void loadJourneyData() {
        // 检查数据库是否已经有数据了，如果没有才添加
        if (journeyRepository.count() == 0) {

            System.out.println("Seeding Journey data...");

            // --- 创建第 1 篇文章 ---
            Journey post1 = new Journey();
            post1.setTitle("On Finding Focus");
            post1.setLanguage(null);
            post1.setContent("<p>In a world of constant notifications, finding a quiet space for deep work has become a luxury. This essay explores the techniques I've found helpful in reclaiming focus and fostering creativity amidst the noise.</p>");
            post1.setImages(List.of("/images/4.JPG"));

            // --- 创建第 2 篇文章 ---
            Journey post2 = new Journey();
            post2.setTitle("一次关于“极简”的思考");
            post2.setLanguage("zh-CN");
            post2.setContent("<p>“少即是多”，这句设计界的至理名言，在生活中同样适用。我们时常被过剩的信息、选择和物品所淹没，却忘了真正能带来内心平静的，往往是那些最纯粹、最本质的东西。</p>");
            post2.setImages(List.of("/images/1.JPG", "/images/2.JPG", "/images/3.JPG"));

            // --- 创建第 3 篇文章 ---
            Journey post3 = new Journey();
            post3.setTitle("Notes from a Mountain Hike");
            post3.setLanguage(null);
            post3.setContent("<p>The rhythm of walking, the crisp mountain air, the panoramic views—hiking is more than just exercise; it's a moving meditation. Each step is a reminder to be present in the moment.</p>");
            post3.setImages(List.of("/images/5.JPG"));

            // 6. 将所有文章打包成一个列表，一次性保存
            List<Journey> journeyPosts = List.of(post1, post2, post3);
            journeyRepository.saveAll(journeyPosts);

            System.out.println("Journey data has been seeded.");
        }
    }
}