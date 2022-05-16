package bootcamp.proyectoFinal.models.Requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TicketDetailsRequest {
    private long prodCode;
    private int ammountProducts;
}
