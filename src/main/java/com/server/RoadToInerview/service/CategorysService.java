package com.server.RoadToInerview.service;

import com.server.RoadToInerview.configuration.JWTUtil;
import com.server.RoadToInerview.domain.Categorys.Categorys;
import com.server.RoadToInerview.domain.Categorys.CategorysPostForm;
import com.server.RoadToInerview.domain.users.Users;
import com.server.RoadToInerview.repository.CategorysRepository;
import com.server.RoadToInerview.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategorysService {
    @Autowired
    CategorysRepository categorysRepository;
    @Autowired
    UsersRepository usersRepository;
    @Transactional
    public Categorys postCategory(CategorysPostForm categorysPostForm, String accessToken){
        Users users = usersRepository.findByEmail(JWTUtil.verifyAccess(accessToken).getUsername());
        Categorys categorys = new Categorys();
        categorys.setCategory(categorysPostForm.getCategory());
        categorys.setCategorys_id(categorysPostForm.getCategorys_id());
        categorys.setUsersId(users);
        Categorys newCategorys = categorysRepository.save(categorys);
        return newCategorys;
    }
    @Transactional
    public List<Categorys> getCategorys(){
        List<Categorys> categorys= categorysRepository.findAll();
        System.out.println(categorys);
        return categorys;
    }
}
