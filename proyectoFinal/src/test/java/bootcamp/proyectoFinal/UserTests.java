package bootcamp.proyectoFinal;

import bootcamp.proyectoFinal.models.MUser;
import bootcamp.proyectoFinal.repositories.UserRepository;
import bootcamp.proyectoFinal.services.UserService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTests {

    @InjectMocks
    private UserService uS;

    @Mock
    private UserRepository uR;

    @Test
    public void saveIfNotExists()
    {
        MUser user = new MUser();
        user.setUsername("admin");

        when(uR.findByUsername("admin")).thenReturn(Optional.empty());
        when(uR.save(user)).thenReturn(user);
        boolean res = uS.saveUser(user);
        assertTrue(res);

    }
    @Test
    public void saveIfExists()
    {
        MUser user = new MUser();
        user.setUsername("admin");

        when(uR.getById("admin")).thenReturn(user);
        when(uR.save(user)).thenReturn(user);
        boolean res = uS.saveUser(user);
        assertFalse(res);

    }

    @Test
    public void updateIfExists() {
        MUser user = new MUser();
        user.setUsername("admin");
        when(uR.getById("admin")).thenReturn(user);
        when(uR.save(user)).thenReturn(user);
        assertTrue(uS.updateUser(user, "admin"));
    }
    @Test
    public void updateIfNotExists()
    {
        MUser user = new MUser();
        when(uR.getById("admin")).thenReturn(null);
        assertFalse(uS.updateUser(user, "admin"));
    }
    @Test
    public void deleteIfExists()
    {
        assertTrue(uS.deleteUser("admin"));
    }

    @Test
    public void deleteIfNotExists()
    {
        Mockito.doThrow(new EmptyResultDataAccessException(1)).when(uR).deleteById("admin");
        assertFalse(uS.deleteUser("admin"));

    }
}
