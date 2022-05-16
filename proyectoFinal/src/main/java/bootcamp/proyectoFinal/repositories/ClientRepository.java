package bootcamp.proyectoFinal.repositories;

import bootcamp.proyectoFinal.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, String> {

    public Client getByDni(String dni);
}
