package org.credex.hiring.portal.DaoImpl;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.credex.hiring.portal.dao.CollegeDao;
import org.credex.hiring.portal.model.Colleges;
import org.credex.hiring.portal.model.Users;
import org.credex.hiring.portal.service.BeanUtility;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Repository
public class CollegeDaoImpl implements CollegeDao {
    @Autowired
    private SessionFactory sessionFactory;


    @Override
    public Colleges createCollege(Colleges colleges) {
        try {
            Session session = sessionFactory.getCurrentSession();
            session.save(colleges);
            session.flush();
            return colleges;
        } catch (Exception e) {
            throw new RuntimeException("Error creating college: " + e.getMessage(), e);
        }
    }

    @Override
    public Colleges updateCollege(Colleges colleges) {
        try {
            Session session = sessionFactory.getCurrentSession();
            Colleges oldCollegesRRec = session.get(Colleges.class, colleges.getCollegeId());
            BeanUtility.copyNonNullProperties(colleges, oldCollegesRRec);
            session.save(oldCollegesRRec);
            session.flush();
            return oldCollegesRRec;
        } catch (Exception e) {
            throw new RuntimeException("Error updating college: " + e.getMessage(), e);
        }
    }

    @Override
    public String deleteCollege(int collegeId) {
        try {
            Session session = sessionFactory.getCurrentSession();
            Object ob = (Object) session.load(Colleges.class, collegeId);
            session.delete(ob);
            session.flush();
            return "This is deleted";
        } catch (Exception e) {
            throw new RuntimeException("Error deleting college: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Colleges> getAllCollege() {
        try {
            Session session = sessionFactory.getCurrentSession();
            Query<Colleges> query = session.createQuery("FROM Colleges", Colleges.class);
            List<Colleges> colleges = query.getResultList();
            return colleges;
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving all colleges: " + e.getMessage(), e);
        }
    }

}
