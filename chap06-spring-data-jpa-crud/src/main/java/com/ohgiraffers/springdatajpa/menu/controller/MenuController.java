package com.ohgiraffers.springdatajpa.menu.controller;

import com.ohgiraffers.springdatajpa.common.Pagenation;
import com.ohgiraffers.springdatajpa.common.PagingButton;
import com.ohgiraffers.springdatajpa.menu.model.dto.CategoryDTO;
import com.ohgiraffers.springdatajpa.menu.model.dto.MenuDTO;
import com.ohgiraffers.springdatajpa.menu.model.service.MenuService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/menu")
@RequiredArgsConstructor    // 필드에 final 키워드가 붙은 녀석들을 // 자동으로 생성자 주입을 해준다
@Slf4j     // Lombok 라이브러리에서 제공하는 로깅 관련 어노테이션
public class MenuController {

    private final MenuService menuService;

    @GetMapping("/{menuCode}")
    public String findMenuByPathVariable(@PathVariable int menuCode, Model model) {

        MenuDTO resultMenu = menuService.findMenuByMenuCode(menuCode);

        model.addAttribute("result", resultMenu);

        return "menu/detail";
    }

    @GetMapping("/list")
    public String findAllMenu(Model model, @PageableDefault Pageable pageable) {
          // 페이징 처리 하지 않은 findAll()
//        List<MenuDTO> menuList = menuService.findMenuList();
//
//        model.addAttribute("menus", menuList);

        log.info("pageable : {}", pageable);

        Page<MenuDTO> menuList = menuService.findMenuListByPaging(pageable);

        log.info("조회한 내용 목록 : {}" , menuList.getContent());
        log.info("총 페이지 수 : {}", menuList.getTotalPages());
        log.info("총 메뉴의 수 : {}", menuList.getTotalElements());
        log.info("해당 페이지에 표현 될 요소의 수 : {}", menuList.getSize());
        log.info("첫 페이지 여부 : {}",  menuList.isFirst());
        log.info("마지막 페이지 여부 : {}",  menuList.isLast());
        log.info("정렬 방식 : {}", menuList.getSort());
        log.info("여러 페이지 중 현재 인덱스 : {}", menuList.getNumber());

        PagingButton pagingButton = Pagenation.getPagingInfo(menuList);
        model.addAttribute("menus", menuList);
        model.addAttribute("paging", pagingButton);

        return "menu/list";
    }

    @GetMapping("/querymethod")
    public void queryMethodPage() {}


    @GetMapping("/search")
    public String findByMenuPrice(@RequestParam int menuPrice, Model model) {

        List<MenuDTO> menuList = menuService.findByMenuPrice(menuPrice);

        model.addAttribute("menuList", menuList);
        model.addAttribute("price", menuPrice);

        return "menu/searchResult";

    }

    @GetMapping("/regist")
    public void registPage() {}

    @GetMapping(value = "/category", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public List<CategoryDTO> findCategoryList() {
        // return 구문이 view 지정하는 것이 아닌, Data 를 리턴한다.
        return menuService.findAllCategory();
    }

    @PostMapping("/regist")
    public String registMenu(@ModelAttribute MenuDTO newMenu) {

        System.out.println("view 에서 전달 받은 newMenu = " + newMenu);

        menuService.registNewMenu(newMenu);

        return "redirect:/menu/list";
    }

    @GetMapping("/modify")
    public void modifyPage() {}

    @PostMapping("/modify")
    public String modifyMenu(@ModelAttribute MenuDTO modifyMenu) {

        System.out.println("수정 할 메뉴 정보 객체 = " + modifyMenu);

        menuService.modifyMenu(modifyMenu);

        return "redirect:/menu/" + modifyMenu.getMenuCode();
    }

    @GetMapping("/delete")
    public void deletePage() {}

    @PostMapping("/delete")
    public String deleteMenu(@RequestParam int menuCode) {

        menuService.deleteMenu(menuCode);

        return "redirect:/menu/list";
    }

}
