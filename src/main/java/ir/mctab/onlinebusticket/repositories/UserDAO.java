package ir.mctab.onlinebusticket.repositories;

import ir.mctab.onlinebusticket.core.hibernate.HibernateUtil;
import ir.mctab.onlinebusticket.entities.User;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.Queue;

public class UserDAO extends CrudRepository<User,Long>{

    @Override
    protected Class<User> getEntityClass() {
        return User.class;
    }

    public User loadByEmailPass(String username , String password) throws Exception {
        session.beginTransaction();
        Query<User> query = session.createQuery("from User where email =:email and password=:password");
        User user;
        query.setParameter("email",username);
        query.setParameter("password",password);
        user = query.getSingleResult();
        session.getTransaction().commit();
        return user;
    }
}
