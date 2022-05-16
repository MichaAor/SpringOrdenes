package bootcamp.proyectoFinal.models.Requests;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class TicketRequest {

    private String client;
    
    private List<TicketDetailsRequest> ticketDetails;
}