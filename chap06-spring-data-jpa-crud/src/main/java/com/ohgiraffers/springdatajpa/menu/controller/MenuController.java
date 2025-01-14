package com.ohgiraffers.springdatajpa.menu.controller;

import com.ohgiraffers.springdatajpa.common.Pagenation;
import com.ohgiraffers.springdatajpa.common.PagingButton;
import com.ohgiraffers.springdatajpa.menu.model.dto.CategoryDTO;
import com.ohgiraffers.springdatajpa.menu.model.dto.MenuDTO;
import com.ohgiraffers.springdatajpa.menu.service.MenuService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/menu")
@RequiredArgsConstructor //필드에 final 키워드가 붙은 녀석을을 자동으로 생성자 주입을 해준다!
@Slf4j //Lombok 라이브러리에서 제공하는 로깅 관련 어노테이션
public class MenuController {


    private final MenuService menuService;

;
// ================================================================================================================

    //특정 메뉴 조회 버튼
    @GetMapping("/{menuCode}")
    public String findMenuByPathVariable(@PathVariable int menuCode, Model model) {

        MenuDTO resultMenu = menuService.findMenuByMenuCode(menuCode);

        model.addAttribute("result", resultMenu);

        return "menu/detail";
    }

//=================================================================================================================

    //전제 메뉴 조회 버튼 페이징 처리
    @GetMapping("/list")
    public String findAllMenu(Model model, @PageableDefault Pageable pageable ){
          // 페이징 처리하지 않은 findAll()
//        List<MenuDTO> menuList = menuService.findMenuList();
//        model.addAttribute("menus",menuList);

        log.info("pageable : {} " , pageable);

       Page<MenuDTO> menuList = menuService.findMenuListByPaging (pageable);

//       log.info("조회한 내용 목록: {}" , menuList.getContent());
//       log.info("총 페이지수 : {}", menuList.getTotalPages());
//       log.info("총 메뉴의 수 : {}" , menuList.getTotalElements());
//       log.info("해당 페이지에 표현 될 요소의 수 {} " , menuList.getSize() );
//       log.info("첫 페이지 여부 : {} " ,  menuList.isFirst());
//       log.info("마지막 페이지 여부 : {} " ,menuList.isLast());
//       log.info("정렬 방식 : {}" , menuList.getSort());
//       log.info("여러 페이지 중 현재 인덱스 : {} " ,menuList.getNumber());

        PagingButton pagingButton = Pagenation.getPagingInfo(menuList);
        model.addAttribute("menus" , menuList);
        model.addAttribute("paging" , pagingButton);

        return "menu/list";
    }

//===============================================================================================================


    @GetMapping("/querymethod")
    public void queryMethodPage() {}


    @GetMapping("/search")
    public String findByMenuPrice(@RequestParam int menuPrice, Model model) {

        List<MenuDTO> menuList = menuService.findByMenuPrice(menuPrice);

        model.addAttribute("menuList", menuList);
        model.addAttribute("price", menuPrice);

        return "menu/searchResult";

    }

//=====================================================================================================================

    // 카테고리 검색 드롭바

    @GetMapping("/regist")
    public String registPage(Model model) {
        List<CategoryDTO> categories = menuService.findAllCategory();
        model.addAttribute("categories", categories);
        return "menu/regist";  // Thymeleaf 템플릿 경로
    }

    @GetMapping(value = "/category", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public List<CategoryDTO> findCategoryList() {

        List<CategoryDTO> category =  menuService.findAllCategory();

        // return 구문이 view 지정하는 것이 아닌, Data 를 리턴한다.

        return category;
    }


//======================================================================================================================

    //신규 메뉴 저장
    @PostMapping("/save")
    public String saveMenu(@ModelAttribute MenuDTO menuDTO ){
        menuService.saveMenu(menuDTO);
        return "redirect:/menu/list"; // 저장 후 등록 페이지로 리다이렉트
    }

//=====================================================================================================================

    // 삭제 API
    @DeleteMapping("/delete/{menuCode}")
    public ResponseEntity<Void>deleteMenu(@PathVariable int menuCode){
        menuService.deleteMenu(menuCode);
        return ResponseEntity.ok().build();
    }


//===================================================================================================================

  @GetMapping("/modify")
    public void modifyPage(){}



 @PostMapping("/modify")
 public String modifyMenu(@ModelAttribute MenuDTO modifyMenu){

     System.out.println("수정할 메뉴 정보 객체 = " + modifyMenu);

     menuService.modifyMenu(modifyMenu);

     return "redirect:/menu/" + modifyMenu.getMenuCode();
 }



}
