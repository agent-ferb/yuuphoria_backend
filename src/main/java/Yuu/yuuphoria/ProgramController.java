package Yuu.yuuphoria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus; // 新增导入
import org.springframework.web.bind.annotation.*; // 新增导入 (包含 @PostMapping, @RequestBody, @ResponseStatus)

import java.util.List;

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
        // 让 Repository 把这个新 Program 保存到数据库
        // save() 方法会自动处理 ID 的生成，并返回带有 ID 的最终对象
        return programRepository.save(newProgram);
    }
}