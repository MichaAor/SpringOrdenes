package bootcamp.proyectoFinal;


import bootcamp.proyectoFinal.models.Client;
import bootcamp.proyectoFinal.repositories.ClientRepository;
import bootcamp.proyectoFinal.services.ClientService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.junit4.SpringRunner;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ClientTests {
    @InjectMocks
    private ClientService cS;

    @Mock
    private ClientRepository cR;

    @Test
    public void saveIfNotExists()
    {
        Client client = new Client();
        client.setDni("1");

        when(cR.getByDni("1")).thenReturn(null);
        when(cR.save(client)).thenReturn(client);
        Client result = cS.saveCls(client);
        assertEquals(result, client);

    }

    @Test
    public void saveIfExists()
    {
        Client client = new Client();
        client.setDni("1");
        when(cR.getByDni("1")).thenReturn(client);
        assertNull(cS.saveCls(client));
    }

    @Test
    public void getDataIfExists()
    {
       when(cR.getByDni("1")).thenReturn(new Client());
        Client client = cS.getClientByDni("1");
        assertNotNull(client);
    }

    @Test
    public void getDataIfNotExists()
    {
        when(cR.getByDni("1")).thenReturn(null);
        Client client = cS.getClientByDni("1");
        assertNull(client);
    }

    @Test
    public void deleteClientIfExists()
    {
        assertTrue(cS.delete("1"));
    }

    @Test
    public void deleteClientIfNotExists()
    {
        Mockito.doThrow(new EmptyResultDataAccessException(1)).when(cR).deleteById("1");
        assertFalse(cS.delete("1"));

    }

}
