package org.credex.hiring.portal.controller;

import org.credex.hiring.portal.dao.DashBoardDao;
import org.credex.hiring.portal.service.DashBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/Dashboard")
public class DashBoardController {

    @Autowired
    private DashBoardService dashBoardService;
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/getCollegeCount")
    public Long TotalCollegeCount() {
        return dashBoardService.TotalCollegeCount();
    }
    @GetMapping("/studentsEnrolled")
    @CrossOrigin(origins = "http://localhost:4200")
    public Long Students_Enrolled() {
        return dashBoardService.Students_Enrolled();
    }

    @GetMapping("/SelectedStudents")
    @CrossOrigin(origins = "http://localhost:4200")
    public Long Selected_Students() {
        return dashBoardService.Selected_Students();
    }
}

