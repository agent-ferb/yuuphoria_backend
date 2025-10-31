package Yuu.yuuphoria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/journey") // <-- New API path
@CrossOrigin(origins = "*")      // <-- Copied from your controller
public class JourneyController {

    // 依赖为 Service
    private final JourneyService journeyService;

    @Autowired
    public JourneyController(JourneyService journeyService) {
        this.journeyService = journeyService;
    }

    // 1. GET ALL (Read)
    //
    @GetMapping
    public List<Journey> getJourneys() {
        return journeyService.getAllJourneyPosts();
    }

    // 2. GET ONE BY ID (Read)
    @GetMapping("/{id}")
    public ResponseEntity<Journey> getJourneyById(@PathVariable Long id) {
        return ResponseEntity.ok(journeyService.getJourneyById(id));
    }

    // 3. CREATE NEW (Create)
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Journey createJourney(@RequestBody Journey newJourney) {
        return journeyService.createJourney(newJourney);
    }

    // 4. UPDATE EXISTING (Update)
    @PutMapping("/{id}")
    public ResponseEntity<Journey> updateJourney(
            @PathVariable Long id,
            @RequestBody Journey updatedData) {

        return ResponseEntity.ok(journeyService.updateJourney(id, updatedData));
    }

    // 5. DELETE (Delete)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJourney(@PathVariable Long id) {
        journeyService.deleteJourney(id);
        return ResponseEntity.noContent().build();
    }
}