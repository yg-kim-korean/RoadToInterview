package com.server.RoadToInerview.controller;

import com.server.RoadToInerview.domain.Category.Categorys;
import com.server.RoadToInerview.domain.Category.CategorysPostForm;
import com.server.RoadToInerview.domain.ResponseForm;
import com.server.RoadToInerview.service.CategorysService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class CategorysController {
    private final CategorysService categorysService;

    @PostMapping("/categorys")
    public ResponseEntity<?> categorys(@RequestBody CategorysPostForm categorysPostForm, HttpServletRequest request){
        ResponseForm responseForm = new ResponseForm();
        String accessToken;
        try {
            accessToken = request.getHeader("authentication");

        } catch (Exception e) {
            responseForm.setMessage("카테고리 등록 : 로그인이 만료되었습니다.(accessToken)");
            return new ResponseEntity<>(responseForm, HttpStatus.UNAUTHORIZED);
        }
        Categorys categorys = categorysService.postCategory(categorysPostForm,accessToken);

        return new ResponseEntity<>(categorys, HttpStatus.OK);
    }
    @GetMapping("/categorys")
    public String getCategory(){
        return "asd";
    }
}
