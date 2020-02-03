package ir.mctab.onlinebusticket.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class Trip {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    @Column(name = "destination", nullable = false)
    private String destination;

    @Column(name = "initialpoint", nullable = false)
    private String initialPoint;

    @Column(name = "date", nullable = false)
    private String date;

    @Column(name = "time", nullable = false)
    private String time;

    @Column(name = "capacity", nullable = false)
    private Long capacity;

    @OneToMany(mappedBy = "trip")
    private List<Ticket> tickets = new LinkedList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Trip)) return false;
        Trip trip = (Trip) o;
        return getId().equals(trip.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "Trip{" +
                "destination='" + destination + '\'' +
                ", initialPoint='" + initialPoint + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", capacity=" + capacity +
                '}';
    }
}
