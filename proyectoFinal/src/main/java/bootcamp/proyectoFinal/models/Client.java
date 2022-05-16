package bootcamp.proyectoFinal.models;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Client {

    @Id
    private String dni;
    private String fName;
    private String lName;

    public Client(String dni)
    {
        this.dni = dni;
    }
}
