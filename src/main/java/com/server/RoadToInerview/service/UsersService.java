package com.server.RoadToInerview.service;

import com.server.RoadToInerview.domain.Users;
import com.server.RoadToInerview.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UsersService {
    @Autowired
    private UsersRepository usersRepository;

    @Transactional
    public Boolean signup(Users users){
        String email = users.getEmail();
        Users check_user = usersRepository.findByEmail(email);
        if( Objects.isNull(check_user)){
            System.out.println("여기");
            usersRepository.save(users);

        }
        else{
            return false;
        }
        return true;

    }
    @Transactional
    public Users login(String email, String password){

        Users users = usersRepository.findByEmailAndPassword(email,password);
        if (!Objects.isNull(users)){
            return users;
        }
        return null;
    }

    @Transactional
    public Boolean remove(Long id){
        usersRepository.deleteById(id);
        return true;
    }
}
