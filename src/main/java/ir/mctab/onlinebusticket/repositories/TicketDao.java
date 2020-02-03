package ir.mctab.onlinebusticket.repositories;

import ir.mctab.onlinebusticket.entities.Ticket;
import ir.mctab.onlinebusticket.entities.User;
import org.hibernate.query.Query;

public class TicketDao extends CrudRepository<Ticket,Long> {

    @Override
    protected Class<Ticket> getEntityClass() {
        return Ticket.class;
    }

    public Ticket findByBuyer (String name , User User , Long trip_id){
        session.beginTransaction();
        Query<Ticket> query = session.createQuery("from Ticket where buyerName= :name and userId=:userID and tripId=:tripId");
        query.setParameter("name",name);
        query.setParameter("userID",User.getUserId());
        query.setParameter("tripId",trip_id);
        Ticket ticket = query.getSingleResult();
        return ticket;
    }
}
