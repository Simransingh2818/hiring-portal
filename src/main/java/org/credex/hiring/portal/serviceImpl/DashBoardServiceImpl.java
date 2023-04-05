package org.credex.hiring.portal.service;

import org.credex.hiring.portal.dao.DashBoardDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DashBoardServiceImpl implements DashBoardService {

    @Autowired
    private DashBoardDao dashBoardDao;

    @Override
    public Long TotalCollegeCount() {
        return dashBoardDao.TotalCollegeCount();
    }

    @Override
    public Long Students_Enrolled() {
        return dashBoardDao.Students_Enrolled();
    }

    @Override
    public Long Selected_Students() {
        return dashBoardDao.Selected_Students();
    }

}
