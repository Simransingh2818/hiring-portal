package org.credex.hiring.portal.serviceImpl;
import org.credex.hiring.portal.dao.CampusDao;
import org.credex.hiring.portal.service.ChartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;


@Service
public class ChartServiceImpl implements ChartService {
    @Autowired
    private CampusDao campusDao;


    @Override
    @Transactional
    public Map<String, Long> getCounts() {
        return campusDao.getCounts();
    }



}
