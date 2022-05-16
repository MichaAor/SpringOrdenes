package bootcamp.proyectoFinal.services;

import java.util.*;

import bootcamp.proyectoFinal.models.MUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import bootcamp.proyectoFinal.repositories.UserRepository;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserRepository ur;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        Optional<MUser> optional = ur.findByUsername(username);

        if (optional.isEmpty()) {
            throw new UsernameNotFoundException(username);
        }else{
            MUser u = optional.get();
            Set<GrantedAuthority> set = new HashSet<>();
            set.add(new SimpleGrantedAuthority(ur.getRole(username)));
           return new User(u.getUsername(), u.getPassword(), set);
        }

    }

    public boolean saveUser(MUser u ){
        if(ur.getById(u.getUsername()) == null){
            ur.save(u);
            return true;
        }else{
            return  false;
        }

    }

    public List<MUser> getAllUser(){
        return ur.findAll();

    }

    public boolean updateUser(MUser u, String email){
        u.setUsername(email);
        if(ur.getById(u.getUsername()) != null){
            ur.save(u);
            return true;
        }else{
            return  false;
        }

    }
    public boolean deleteUser(String email) {
        try {
            ur.deleteById(email);
            return true;
        }catch (EmptyResultDataAccessException e) {
            return false;
        }
    }

    public MUser findByUsername(String username) {
        Optional<MUser> u = ur.findByUsername(username);
        if(!u.isEmpty()) {
            return u.get();
        }else
        return null;
    }
    
}
