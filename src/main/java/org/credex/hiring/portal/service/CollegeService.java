package org.credex.hiring.portal.service;

import org.credex.hiring.portal.model.Colleges;

import java.util.List;

public interface CollegeService {

    Colleges createCollege(Colleges college);

    Colleges updateCollege(Colleges college);

    String deleteCollege(int collegeId);

    List<Colleges> getAllCollege();

}
