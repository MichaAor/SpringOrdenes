package bootcamp.proyectoFinal.services;

import java.util.List;

import javax.transaction.Transactional;

import bootcamp.proyectoFinal.models.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import bootcamp.proyectoFinal.repositories.ClientRepository;

@Service
public class ClientService {

    @Autowired
    ClientRepository cR;

    public List<Client> getAllCls(){
        return  cR.findAll();
    }

    public Client saveCls(Client c){
        if(cR.getById(c.getDni()) == null){

            return  cR.save(c);
        }
        else {
            return  null;
        }
    }

    @Transactional
    public Client updateCls(Client c, String dni){
        if(cR.getById(dni) != null)
        {
            c.setDni(dni);
            return  cR.save(c);
        }
        else {
            return  null;
        }

    }

    public Client getClientByDni(String dni){
        return cR.getByDni(dni);
    }

    public boolean delete(String dni){
        try {
            cR.deleteById(dni);
            return  true;
        }
        catch (EmptyResultDataAccessException e) {
            return false;
        }

    }
}
