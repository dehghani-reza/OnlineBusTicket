package ir.mctab.onlinebusticket.repositories;

import ir.mctab.onlinebusticket.entities.Ticket;

public class TicketDao extends CrudRepository<Ticket,Long> {

    @Override
    protected Class<Ticket> getEntityClass() {
        return Ticket.class;
    }

}
