package bootcamp.proyectoFinal.repositories;

import bootcamp.proyectoFinal.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
