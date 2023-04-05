package org.credex.hiring.portal.serviceImpl;

import org.credex.hiring.portal.dao.CollegeDao;
import org.credex.hiring.portal.model.Colleges;
import org.credex.hiring.portal.service.CollegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CollegeServiceImpl implements CollegeService {

    @Autowired
    private CollegeDao collegeDao;

    @Override
    @Transactional
    public Colleges createCollege(Colleges college) throws DataAccessException {
        return collegeDao.createCollege(college);
    }

    @Override
    @Transactional
    public Colleges updateCollege(Colleges college) throws DataAccessException {
        return collegeDao.updateCollege(college);
    }

    @Override
    @Transactional
    public String deleteCollege(int collegeId) throws DataAccessException {
        return collegeDao.deleteCollege(collegeId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Colleges> getAllCollege() throws DataAccessException {
        return collegeDao.getAllCollege();
    }

}
