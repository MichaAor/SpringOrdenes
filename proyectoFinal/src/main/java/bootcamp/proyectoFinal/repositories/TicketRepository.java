package bootcamp.proyectoFinal.repositories;

import java.util.List;

import bootcamp.proyectoFinal.models.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

    @Query(value = "SELECT * FROM ticket WHERE dni = :dni", nativeQuery = true)
    public List<Ticket> getByDni(@Param("dni") String dni);
}
