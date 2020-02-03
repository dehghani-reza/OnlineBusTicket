package ir.mctab.onlinebusticket.repositories;

import ir.mctab.onlinebusticket.entities.Ticket;
import ir.mctab.onlinebusticket.entities.User;
import org.hibernate.query.Query;

import java.util.List;

public class TicketDao extends CrudRepository<Ticket,Long> {

    @Override
    protected Class<Ticket> getEntityClass() {
        return Ticket.class;
    }

    public Ticket findByBuyer (String name , User user , Long trip_id){
        session.beginTransaction();
        Query<Ticket> query = session.createQuery("from Ticket where buyerName= :name and userId=:userID and tripId=:tripId");
        query.setParameter("name",name);
        query.setParameter("userID",user.getUserId());
        query.setParameter("tripId",trip_id);
        Ticket ticket = query.getSingleResult();
        session.getTransaction().commit();
        return ticket;
    }
    public List<Ticket> findByUser(User user){
        List<Ticket> tickets;
        session.beginTransaction();
        Query<Ticket> query = session.createQuery("from Ticket where userId=:userID");
        query.setParameter("userID",user.getUserId());
        tickets = query.list();
        session.getTransaction().commit();
        return tickets;
    }
}
