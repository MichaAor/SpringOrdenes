package bootcamp.proyectoFinal;


import bootcamp.proyectoFinal.models.Requests.TicketDetailsRequest;
import bootcamp.proyectoFinal.models.Requests.TicketRequest;
import bootcamp.proyectoFinal.models.Ticket;
import bootcamp.proyectoFinal.repositories.TicketRepository;
import bootcamp.proyectoFinal.services.TicketService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
@RunWith(SpringRunner.class)
@SpringBootTest
public class TicketTests {

    @InjectMocks
    private TicketService tS;

    @Mock
    private TicketRepository tR;

    @Test
    public void createIfNotExists()
    {
        TicketRequest ticketRequest = new TicketRequest("1", new ArrayList<TicketDetailsRequest>());
        ticketRequest.getTicketDetails().add(new TicketDetailsRequest(1, 10));
        Ticket ticket = new Ticket(ticketRequest);

        when(tR.getById(1L)).thenReturn(null);
        when(tR.save(ticket)).thenReturn(ticket);
        assertEquals(tS.saveTicket(ticket), ticket);
    }

    @Test
    public void createIfExists()
    {
        TicketRequest ticketRequest = new TicketRequest("1", new ArrayList<TicketDetailsRequest>());
        ticketRequest.getTicketDetails().add(new TicketDetailsRequest(1, 10));
        Ticket ticket = new Ticket(ticketRequest);
        when(tR.getById(1L)).thenReturn(ticket);
        assertNull(tS.saveTicket(ticket));
    }

    @Test
    public void getDataIfExists()
    {
        TicketRequest ticketRequest = new TicketRequest("1", new ArrayList<TicketDetailsRequest>());
        ticketRequest.getTicketDetails().add(new TicketDetailsRequest(1, 10));
        Ticket ticket = new Ticket(ticketRequest);
        when(tR.findById(1L)).thenReturn(Optional.of(ticket));
        assertFalse(tS.getById(1L).isEmpty());
    }
    @Test
    public void getDataIfNotExists()
    {
        when(tR.findById(1L)).thenReturn(Optional.empty());
        assertTrue(tS.getById(1L).isEmpty());
    }
}
