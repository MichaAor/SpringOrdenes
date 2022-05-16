package bootcamp.proyectoFinal.models;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import bootcamp.proyectoFinal.models.Requests.TicketDetailsRequest;
import bootcamp.proyectoFinal.models.Requests.TicketRequest;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {
    public Ticket(TicketRequest ticket) {
        this.client = new Client(ticket.getClient());
        this.ticketDetails = new LinkedList<>();
        for (TicketDetailsRequest t : ticket.getTicketDetails()) {
            ticketDetails.add(new TicketDetails(this,t.getProdCode(),t.getAmmountProducts()));
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ticketId;

    @ManyToOne
    @JoinColumn(name = "dni")
    private Client client;
    
    @JsonManagedReference
    @OneToMany(mappedBy = "ticket", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<TicketDetails> ticketDetails;
}
