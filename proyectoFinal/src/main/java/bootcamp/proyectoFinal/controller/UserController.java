package bootcamp.proyectoFinal.controller;

import bootcamp.proyectoFinal.models.MUser;
import bootcamp.proyectoFinal.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService uS;

    @GetMapping
    public ResponseEntity getAll()
    {
        List<MUser> users = uS.getAllUser();
        if(!users.isEmpty())
        {
           return ResponseEntity.status(200).body(users);
        }
        else {
            return ResponseEntity.status(204).body(users);
        }
    }

    @PostMapping("/saveCls")
    public ResponseEntity save(@RequestBody MUser user)
    {
        boolean flag = uS.saveUser(user);
        if(flag)
            return  ResponseEntity.status(200).body("Success.");
        else
            return  ResponseEntity.status(400).body("Error.");
    }

    @PutMapping("/updateCls/{email}")
    public ResponseEntity update(@RequestBody MUser user, @PathVariable("email") String email)
    {
        boolean flag = uS.updateUser(user, email);
        if(flag)
            return  ResponseEntity.status(200).body("Success.");
        else
            return  ResponseEntity.status(400).body("Error.");
    }

}
