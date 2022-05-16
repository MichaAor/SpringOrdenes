package bootcamp.proyectoFinal;


import bootcamp.proyectoFinal.models.Product;
import bootcamp.proyectoFinal.repositories.ProductRepository;
import bootcamp.proyectoFinal.services.ProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.when;
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductsTests {

    @InjectMocks
    private ProductService pS;

    @Mock
    private ProductRepository pR;

    @Test
    public void createProductIfNotExists()
    {
        Product product = new Product();
        product.setCode(1);
        when(pR.getById(1L)).thenReturn(null);
        when(pR.save(product)).thenReturn(product);
        assertEquals(pS.saveProd(product), product);
    }
    @Test
    public void createProductIfExists()
    {
        Product product = new Product();
        product.setCode(1);
        when(pR.getById(1L)).thenReturn(product);
        assertNull(pS.saveProd(product));
    }

    @Test
    public void updateProductIfExists()
    {
        Product product = new Product();
        when(pR.getById(1L)).thenReturn(product);
        when(pR.save(product)).thenReturn(product);
        assertEquals(pS.updateProd(product, 1), product);
    }
    @Test
    public void updateProductIfNotExists()
    {
        Product product = new Product();
        when(pR.getById(1L)).thenReturn(null);
        assertNull(pS.updateProd(product, 1));
    }

    @Test
    public void deleteProductIfExists()
    {
        assertTrue(pS.deleteProd(1L));
    }
    @Test
    public void deleteProductIfNotExists()
    {
        Mockito.doThrow(new EmptyResultDataAccessException(1)).when(pR).deleteById(1L);
        assertFalse(pS.deleteProd(1));

    }

}
