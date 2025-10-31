package Yuu.yuuphoria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/api/programs")
@CrossOrigin(origins = "*")
public class ProgramController {

    private final ProgramService programService;

    @Autowired
    public ProgramController(ProgramService programService) {
        this.programService = programService;
    }

    // 3. GET ALL
    @GetMapping
    public List<Program> getPrograms() {
        // 4. 把工作转交给 Service
        return programService.getAllPrograms();
    }

    // 5. GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Program> getProgramById(@PathVariable Long id) {
        // (Service 会自动处理 "Not Found")
        return ResponseEntity.ok(programService.getProgramById(id));
    }

    // 6. CREATE
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Program createProgram(@RequestBody Program newProgram) {
        return programService.createProgram(newProgram);
    }

    // 7. UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Program> updateProgram(
            @PathVariable Long id,
            @RequestBody Program updatedData) {

        return ResponseEntity.ok(programService.updateProgram(id, updatedData));
    }

    // 8. DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProgram(@PathVariable Long id) {
        programService.deleteProgram(id);
        return ResponseEntity.noContent().build();
    }
}