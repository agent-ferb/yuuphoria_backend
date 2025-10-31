package Yuu.yuuphoria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import org.springframework.data.domain.Sort;

@Service
public class JourneyService {

    // 注入 (Autowired) Repository，让 Service 可以使用它
    @Autowired
    private JourneyRepository journeyRepository;

    /**
     * 获取所有的 Journey 帖子
     * @return 数据库中所有 Journey 实体的列表
     */
    public List<Journey> getAllJourneyPosts() {

        return journeyRepository.findAll(Sort.by(Sort.Direction.DESC, "postDate"));
    }

    // 添加更多方法
    // public Journey getJourneyPostById(Long id) { ... }
    // public Journey createJourneyPost(Journey newPost) { ... }
}