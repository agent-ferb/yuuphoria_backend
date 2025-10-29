package Yuu.yuuphoria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*; // 包含 @PostMapping, @RequestBody, @ResponseStatus
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/programs")
@CrossOrigin(origins = "*")
public class ProgramController {

    private final ProgramRepository programRepository;

    @Autowired
    public ProgramController(ProgramRepository programRepository) {
        this.programRepository = programRepository;
    }

    @GetMapping
    public List<Program> getPrograms() {
        return programRepository.findAll();
    }

    /**
     * 处理创建新 Program 的 POST 请求
     * @param newProgram Spring 会自动从请求体(RequestBody)的 JSON 创建这个对象
     * @return 返回刚刚创建并保存到数据库的 Program 对象 (包含自动生成的 ID)
     */
    @PostMapping // 告诉 Spring: 这是处理 POST 请求的方法
    @ResponseStatus(HttpStatus.CREATED) // 告诉前端: 如果成功，返回 HTTP 状态码 201 (Created)
    public Program createProgram(@RequestBody Program newProgram) {
        // 重要：确保传入的 Program 没有 ID，或者 ID 为 null，防止覆盖
        newProgram.setId(null); // 强制 ID 为 null，让数据库生成
        return programRepository.save(newProgram);
    }

    /**
     * 处理更新已存在 Program 的 PUT 请求
     * @param id          要更新的 Program 的 ID (从 URL 路径中提取)
     * @param updatedData 包含更新后数据的 Program 对象 (从请求体中提取)
     * @return 返回更新后的 Program 对象，或者如果 ID 不存在则返回 404 Not Found
     */
    @PutMapping("/{id}") // 监听 PUT /api/programs/{具体的ID}
    public ResponseEntity<Program> updateProgram(
            @PathVariable Long id,         // 从 URL 路径获取 ID
            @RequestBody Program updatedData) { // 从请求体获取更新的数据

        // 1. 尝试从数据库根据 ID 查找现有的 Program
        Optional<Program> existingProgramOptional = programRepository.findById(id);

        // 2. 检查是否找到了
        if (existingProgramOptional.isPresent()) {
            // 获取现有的 Program 对象
            Program existingProgram = existingProgramOptional.get();

            // 3. 用传入的新数据更新现有对象的字段
            existingProgram.setTitle(updatedData.getTitle());
            existingProgram.setDescription(updatedData.getDescription());
            existingProgram.setDate(updatedData.getDate());
            existingProgram.setTeamType(updatedData.getTeamType());
            existingProgram.setInstitution(updatedData.getInstitution());
            existingProgram.setStatus(updatedData.getStatus());
            existingProgram.setContentTitle(updatedData.getContentTitle());
            existingProgram.setGithubUrl(updatedData.getGithubUrl());
            existingProgram.setPaperUrl(updatedData.getPaperUrl());
            existingProgram.setLinksAvailable(updatedData.isLinksAvailable());
            // 注意：不更新 ID

            // 4. 保存更新后的对象回数据库
            Program savedProgram = programRepository.save(existingProgram);

            // 5. 返回 HTTP 状态码 200 (OK) 和更新后的对象
            return ResponseEntity.ok(savedProgram);

        } else {
            // 没找到！
            // 返回 HTTP 状态码 404 (Not Found)
            return ResponseEntity.notFound().build();
        }
    }
}