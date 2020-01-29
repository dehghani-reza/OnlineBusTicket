package ir.mctab.onlinebusticket.repositories;

import ir.mctab.onlinebusticket.entities.Trip;
import org.hibernate.query.Query;

import javax.xml.crypto.dsig.spec.TransformParameterSpec;
import java.util.List;

public class TripDAO extends CrudRepository<Trip,Long> {
    @Override
    protected Class<Trip> getEntityClass() {
        return Trip.class;
    }

    public List<Trip> load(String date , String initPoint , String destination){
        List<Trip> tripList;
        session.beginTransaction();
        Query<Trip> query = session.createQuery("from Trip where date=:date and destination=:destination and initialPoint=:initpint");
        query.setParameter("date",date);
        query.setParameter("destination",destination);
        query.setParameter("initpint",initPoint);
        tripList = query.list();
        session.getTransaction().commit();
        return tripList;
    }
}
