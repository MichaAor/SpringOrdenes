package bootcamp.proyectoFinal.models;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TicketDetails {

    public TicketDetails(Ticket ticket, long prodCode, int ammountProducts) {
        this.ticket=ticket;
        this.product = new Product(prodCode);
        this.ammountProducts = ammountProducts;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "ammount_products")
    private int ammountProducts;

    @ManyToOne
    @JoinColumn(name = "product_code")
    private Product product;

    

    @ManyToOne
    @JoinColumn(name = "ticket_id")
    @JsonBackReference
    private Ticket ticket;
}
