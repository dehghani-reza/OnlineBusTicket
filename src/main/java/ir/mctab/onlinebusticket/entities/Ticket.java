package ir.mctab.onlinebusticket.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Table(name = "tickets")
@Entity
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ticketId")
    private Long ticketId;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne
    @JoinColumn(name = "tripId")
    private Trip trip;

    @Column(nullable = false)
    private String buyerName;

    @Enumerated(EnumType.STRING)
    private Gender buyerGender;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ticket)) return false;
        Ticket ticket = (Ticket) o;
        return getTicketId().equals(ticket.getTicketId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTicketId());
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "ticketId='" + ticketId + '\'' +
                ", trip=" + trip +
                ", buyerName='" + buyerName + '\'' +
                '}';
    }
}
