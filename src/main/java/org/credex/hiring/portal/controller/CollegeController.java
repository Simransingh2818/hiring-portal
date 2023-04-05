package org.credex.hiring.portal.controller;

import org.credex.hiring.portal.dao.CollegeDao;
import org.credex.hiring.portal.dao.UserDao;
import org.credex.hiring.portal.model.Colleges;
import org.credex.hiring.portal.service.CollegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/colleges")
public class CollegeController {
    @Autowired
    private CollegeService collegeService;
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public Colleges createUser(@Validated @RequestBody Colleges colleges) {
        return collegeService.createCollege(colleges);

    }
    @GetMapping("/all")
    @CrossOrigin(origins = "http://localhost:4200")
    public List<Colleges> getAllCollege() {
        return collegeService.getAllCollege();
    }
    @PutMapping("/update")
    @CrossOrigin(origins = "http://localhost:4200")
    public Colleges updateCollege(@RequestBody Colleges colleges) {
        return collegeService.updateCollege(colleges);
    }
    @DeleteMapping("/delete/{collegeId}")
    @CrossOrigin(origins = "http://localhost:4200")
    public String deleteCollege(@PathVariable("collegeId") int collegeId) {
        return collegeService.deleteCollege(collegeId);
    }
}
