package bootcamp.proyectoFinal.controller;

import java.util.List;

import bootcamp.proyectoFinal.models.Client;
import bootcamp.proyectoFinal.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clients")
public class ClientController {
    @Autowired
    ClientService cS;

    @GetMapping
    public ResponseEntity<Object> getAll(){
        try{
            List<Client> clients = cS.getAllCls();
            if(clients.size()==0){
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Esta vacio, ves? no hay tickets aqui. Buena, ahora si hay uno pero no voy a asustarte. No es mi zona :)");
            }
            return ResponseEntity.status(200).body(clients);
        }catch (Exception e){
            return   ResponseEntity.status(500).body("Error.");
        }

    }

    @PostMapping("/saveCls")
    public ResponseEntity<Object> save(@RequestBody Client client){
        try{
            Client c = cS.saveCls(client);
            return ResponseEntity.status(200).body(c);
        }catch (Exception e){
            return   ResponseEntity.status(500).body("Error.");
        }

    }

    @DeleteMapping("/deleteProd/{dni}")
    public ResponseEntity<Object> delete(@PathVariable("dni") String dni){
        try{
            boolean f = cS.delete(dni);
            if(f){
                return ResponseEntity.status(200).body("Success.");
            }else{
                return ResponseEntity.status(204).body("DNI Not found.");
            }
        }catch (Exception e) {
            return   ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error");
        }
        
    }

    @PutMapping("/updateCls/{dni}")
    public ResponseEntity<Object> update(@PathVariable("dni") String dni, @RequestBody Client client)
    {
        Client c = cS.updateCls(client, dni);
        if(c != null){
            return ResponseEntity.status(200).body("Success");
        }
        else{
            return ResponseEntity.status(204).body("Client Not Found");
        }
    }

}
