package com.server.RoadToInerview.service;

import com.server.RoadToInerview.domain.UserPutForm;
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

    public String checking(String email, String nickname){
        Users check_email = usersRepository.findByEmail(email);
        Users check_nickname = usersRepository.findByNickname(nickname);

        if( Objects.isNull(check_nickname) && Objects.isNull(check_email) ){
            return "no";
        }
        else if( !Objects.isNull(check_email)){
            return "email";
        }
        else if( !Objects.isNull(check_nickname)){
            return "nickname";
        }
        return null;
    }
    @Transactional
    public Users signup(Users users){
        Users newUsers;
        newUsers = usersRepository.save(users);
        return newUsers;
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

    @Transactional
    public Users modify(UserPutForm userPutForm){

        Users newUsers = usersRepository.findByEmail(userPutForm.getEmail());
        newUsers.setNickname(userPutForm.getNickname());
        newUsers.setPassword(userPutForm.getPassword());
        newUsers.setEmail(userPutForm.getEmail());
        usersRepository.save(newUsers);

        return newUsers;
    }
}
