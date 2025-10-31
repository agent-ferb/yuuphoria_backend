package Yuu.yuuphoria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

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

    /**
     *  GET ONE BY ID (Read)
     * 按 ID 查找一个帖子，如果找不到则抛出 404 异常
     */
    public Journey getJourneyById(Long id) {
        return journeyRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Journey post not found"));
    }

    /**
     * CREATE NEW (Create)
     */
    public Journey createJourney(Journey newJourney) {
        // 确保 ID 为 null，以便数据库自动生成
        newJourney.setId(null);
        return journeyRepository.save(newJourney);
    }

    /**
     * UPDATE EXISTING (Update)
     */
    public Journey updateJourney(Long id, Journey updatedData) {
        // 1. 先按 ID 查找现有的帖子
        Journey existingJourney = getJourneyById(id); // 复用 getById 方法，自动处理 404

        // 2. 使用新数据更新字段
        existingJourney.setTitle(updatedData.getTitle());
        existingJourney.setContent(updatedData.getContent());
        existingJourney.setLanguage(updatedData.getLanguage());
        existingJourney.setImages(updatedData.getImages());
        // 我们不更新 postDate，因为它是在创建时设置的

        // 3. 保存更新后的实体
        return journeyRepository.save(existingJourney);
    }

    /**
     * 5. DELETE (Delete)
     */
    public void deleteJourney(Long id) {
        // 1. 检查它是否存在
        if (!journeyRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Journey post not found");
        }
        // 2. 存在则删除
        journeyRepository.deleteById(id);
    }
}