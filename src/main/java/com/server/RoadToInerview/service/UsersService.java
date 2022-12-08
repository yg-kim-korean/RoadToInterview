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
    public String signup(Users users){
        Users check_email = usersRepository.findByEmail(users.getEmail());
        Users check_nickname = usersRepository.findByNickname(users.getNickname());
        if( Objects.isNull(check_nickname) && Objects.isNull(check_email) ){
            usersRepository.save(users);
        }
        else if( !Objects.isNull(check_nickname)){
            return "nickname";
        }
        else if( !Objects.isNull(check_email)){
            return "email";
        }
        return "created";

    }
    @Transactional
    public Users login(String email, String password){

        Users users = usersRepository.findUsersByEmailAndPassword(email,password);
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
