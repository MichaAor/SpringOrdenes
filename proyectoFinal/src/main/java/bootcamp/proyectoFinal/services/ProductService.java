package bootcamp.proyectoFinal.services;

import java.util.List;

import javax.transaction.Transactional;

import bootcamp.proyectoFinal.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import bootcamp.proyectoFinal.repositories.ProductRepository;

@Service
public class ProductService {


    @Autowired
    ProductRepository cR;

    public List<Product> getAllProd()
    {
        return  cR.findAll();
    }

    public Product saveProd(Product c){
        if(cR.getById(c.getCode()) == null)
            return  cR.save(c);
        else
            return  null;

    }

    @Transactional
    public Product updateProd(Product c, long code){
        if(cR.getById(code) != null) {
            c.setCode(code);
            return  cR.save(c);
        }
        else {
            return  null;
        }

    }

    public boolean deleteProd(long code){
        try {
            cR.deleteById(code);
            return  true;
        }
        catch (EmptyResultDataAccessException e){
            return false;
        }
    }
}
