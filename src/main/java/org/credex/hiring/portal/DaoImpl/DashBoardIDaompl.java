package org.credex.hiring.portal.DaoImpl;

import org.credex.hiring.portal.dao.DashBoardDao;
import org.credex.hiring.portal.model.Colleges;
import org.credex.hiring.portal.model.Users;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Repository
public class DashBoardIDaompl implements DashBoardDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Long TotalCollegeCount()  {
        try {
            Session session = sessionFactory.getCurrentSession();
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Long> cqCollegeCount = cb.createQuery(Long.class);
            Root<Colleges> rootCollegeCount = cqCollegeCount.from(Colleges.class);
            cqCollegeCount.select(cb.count(rootCollegeCount.get("collegeId")));
            Long TotalCollegeCount = session.createQuery(cqCollegeCount).getSingleResult();
            return TotalCollegeCount;
        } catch (Exception e) {
            throw new RuntimeException("Error in getting total college count: " + e.getMessage());
        }
    }

    @Override
    public Long Students_Enrolled() {
        try {
            Session session = sessionFactory.getCurrentSession();
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Long> cqStudentsEnrolled = cb.createQuery(Long.class);
            Root<Users> rootStudentsEnrolled = cqStudentsEnrolled.from(Users.class);
            cqStudentsEnrolled.select(cb.count(rootStudentsEnrolled.get("userId")))
                    .where(cb.equal(rootStudentsEnrolled.get("roleId"), 3));
            Long StudentsEnrolled = session.createQuery(cqStudentsEnrolled).getSingleResult();
            return StudentsEnrolled;
        } catch (Exception e) {
            throw new RuntimeException("Error in getting number of students enrolled: " + e.getMessage());
        }
    }

    @Override
    public Long Selected_Students() {
        try {
            Session session = sessionFactory.getCurrentSession();
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Long> cqSelectedStudents = cb.createQuery(Long.class);
            Root<Users> rootSelectedStudents = cqSelectedStudents.from(Users.class);
            cqSelectedStudents.select(cb.count(rootSelectedStudents.get("userId")))
                    .where(cb.equal(rootSelectedStudents.get("status"), true));
            Long selectedStudentsCount = session.createQuery(cqSelectedStudents).getSingleResult();
            return selectedStudentsCount;
        } catch (Exception e) {
            throw new RuntimeException("Error in getting number of selected students: " + e.getMessage());
        }
    }
}
