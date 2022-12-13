package com.server.RoadToInerview.service;

import com.server.RoadToInerview.configuration.JWTUtil;
import com.server.RoadToInerview.domain.Category.Categorys;
import com.server.RoadToInerview.domain.Category.CategorysPostForm;
import com.server.RoadToInerview.domain.users.Users;
import com.server.RoadToInerview.repository.CategorysRepository;
import com.server.RoadToInerview.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        categorys.setUsers_id(users);
        Categorys newCategorys = categorysRepository.save(categorys);
        return newCategorys;
    }
}
