package org.credex.hiring.portal.DaoImpl;

import org.credex.hiring.portal.dao.CampusDao;
import org.credex.hiring.portal.model.Users;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.HashMap;
import java.util.Map;


@Repository
public class CampusDaoImpl implements CampusDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Map<String, Long> getCounts() {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();

        Map<String, Long> countsMap = new HashMap<>();
        try {
            // Count the number of registered students
            CriteriaQuery<Long> cqRegisteredStudents = cb.createQuery(Long.class);
            Root<Users> rootRegisteredStudents = cqRegisteredStudents.from(Users.class);
            cqRegisteredStudents.select(cb.count(rootRegisteredStudents.get("userId")));
            Long registeredStudentsCount = session.createQuery(cqRegisteredStudents).getSingleResult();
            countsMap.put("registeredStudentCount", registeredStudentsCount);

            // Count the number of selected students
            CriteriaQuery<Long> cqSelectedStudents = cb.createQuery(Long.class);
            Root<Users> rootSelectedStudents = cqSelectedStudents.from(Users.class);
            cqSelectedStudents.select(cb.count(rootSelectedStudents.get("userId")))
                    .where(cb.equal(rootSelectedStudents.get("status"), true));
            Long selectedStudentsCount = session.createQuery(cqSelectedStudents).getSingleResult();
            countsMap.put("selectedStudentCount", selectedStudentsCount);

        } catch (Exception ex) {
            // Handle any exceptions that may occur during the database query process
            System.out.println("An error occurred while fetching the student counts: " + ex.getMessage());
        }
        return countsMap;
    }
}
