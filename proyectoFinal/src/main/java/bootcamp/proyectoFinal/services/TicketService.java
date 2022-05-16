package bootcamp.proyectoFinal.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import bootcamp.proyectoFinal.models.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import bootcamp.proyectoFinal.repositories.TicketRepository;

@Service
public class TicketService {

    @Autowired
    TicketRepository tR;

    public List<Ticket> getAllTicket(){
        return  tR.findAll();
    }

    public Ticket saveTicket(Ticket c){
        if(tR.getById(c.getTicketId()) == null){
            return  tR.save(c);
        }
        else
            return null;
    }

    public Optional<Ticket> getById(long id){
        return tR.findById(id);
    }

    @Transactional
    public Ticket updateTicket(Ticket c, long id){
        if(tR.getById(id)!=null) {
            c.setTicketId(id);
            return  tR.save(c);
        }
        else {
            return  null;
        }

    }

    public boolean deleteTicket(long id){
        try {
            tR.deleteById(id);
            return  true;
        }
        catch (EmptyResultDataAccessException e){
            return false;
        }

    }

    public List<Ticket> getByClient(String dni){
        return tR.getByDni(dni);
    }
}
