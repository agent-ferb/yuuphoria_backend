package Yuu.yuuphoria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ProgramService {

    @Autowired
    private ProgramRepository programRepository;

    /**
     * 1. GET ALL (Read)
     * 获取所有 Program, 按创建时间 (createdAt) 倒序排列
     */
    public List<Program> getAllPrograms() {
        // 使用 "createdAt" 字段进行倒序排序
        return programRepository.findAll(Sort.by(Sort.Direction.DESC, "createdAt"));
    }

    /**
     * 2. GET ONE BY ID (Read)
     * 按 ID 查找一个 Program，如果找不到则抛出 404 异常
     */
    public Program getProgramById(Long id) {
        return programRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Program not found"));
    }

    /**
     * 3. CREATE NEW (Create)
     */
    public Program createProgram(Program newProgram) {
        newProgram.setId(null); // 确保 ID 为 null
        return programRepository.save(newProgram);
    }

    /**
     * 4. UPDATE EXISTING (Update)
     */
    public Program updateProgram(Long id, Program updatedData) {
        // 1. 先按 ID 查找现有的
        Program existingProgram = getProgramById(id); // 复用 getById, 自动处理 404

        // 2. 用新数据更新所有字段 (从你旧的 Controller 复制过来的)
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
        // 不更新 createdAt

        // 3. 保存更新后的实体
        return programRepository.save(existingProgram);
    }

    /**
     * 5. DELETE (Delete)
     */
    public void deleteProgram(Long id) {
        if (!programRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Program not found");
        }
        programRepository.deleteById(id);
    }
}