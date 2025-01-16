package com.ohgiraffers.setion02.springdatajpa.menu.service;

import com.ohgiraffers.setion02.springdatajpa.menu.entity.Category;
import com.ohgiraffers.setion02.springdatajpa.menu.entity.Menu;
import com.ohgiraffers.setion02.springdatajpa.menu.model.dao.CategoryRepository;
import com.ohgiraffers.setion02.springdatajpa.menu.model.dao.MenuRepository;
import com.ohgiraffers.setion02.springdatajpa.menu.model.dto.CategoryDTO;
import com.ohgiraffers.setion02.springdatajpa.menu.model.dto.MenuDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MenuService {

    private final MenuRepository repository;
    // Bean 으로 등록한 modelMapper 의존성 주입.
    private final ModelMapper modelMapper;
    private final CategoryRepository categoryRepository;



//=====================================================================================================================

    /* 1. 메뉴 코드로 특정 메뉴 조회하기 */
    public MenuDTO findMenuByMenuCode(int menuCode) {

        Menu fondMenu = repository.findById(menuCode)
                                        // 잘 못된 파라미터가 넘겨왔을 때 예외처리 필수
                                        .orElseThrow(IllegalArgumentException::new);
        //entity -> dto
        //map(변환 대상, 변환 할 타입)
        return modelMapper.map(fondMenu, MenuDTO.class);
    }

//===================================================================================================================


    /*페이징 처리하지 않는 메뉴 리스트 조회하기*/
    public List<MenuDTO> findMenuList() {

          // 정렬 없은 findALL
//        List<Menu> menuList = repository.findAll();

        List<Menu> menuList = repository.findAll(Sort.by("menuPrice").descending());

        //stream : 컬렉션(List 등등) 데이터를 처리하기 위해 나열
        //map :  스트림화 된 데이터를 꺼내 매핑 및 변환
        return menuList.stream().map(menu -> modelMapper.map(menu, MenuDTO.class))
                // collect 스트림화 된 데이터를 다시 리스트로 변환
                .collect(Collectors.toList());
    }


    /*페이징 처리를 한 메누 전체 조회*/
    public Page<MenuDTO> findMenuListByPaging(Pageable pageable) {
        pageable = PageRequest.of(
                pageable.getPageNumber()<=0? 0: pageable.getPageNumber() -1,
                pageable.getPageSize(),
                Sort.by("menuCode").ascending()
        );
        Page<Menu> menuList = repository.findAll(pageable);

        return menuList.map( menu -> modelMapper.map(menu, MenuDTO.class));
    }


//======================================================================================================================

    // Price 를 초과하는 쿼리메소드 버튼
    public List<MenuDTO> findByMenuPrice(int menuPrice) {

        List<Menu> menuList = repository.findByMenuPriceGreaterThanOrderByMenuPrice(menuPrice);

        return menuList.stream()
                .map(menu -> modelMapper.map(menu, MenuDTO.class))
                .collect(Collectors.toList());
    }

//=====================================================================================================================

    //메뉴 등록  카테고리, 판매여부 select
    public List<CategoryDTO> findAllCategory() {

        List<Category> categoryList
                = categoryRepository.findAllCategory();

        return categoryList.stream().map(category -> modelMapper.map(category, CategoryDTO.class))
                // DTO 형태로 리스트를 반환합니다.
                .collect(Collectors.toList());

    }

//=====================================================================================================================
    //신규 메뉴등록

    public Menu saveMenu(MenuDTO menuDTO){
        //DTO -> Entity 변환
        Menu menu = new Menu();
        menu.setMenuName(menuDTO.getMenuName());
        menu.setMenuPrice(menuDTO.getMenuPrice());
        menu.setCategoryCode(menuDTO.getCategoryCode());
        menu.setOrderableStatus(menuDTO.getOrderableStatus());

        return repository.save(menu);
    }
//================================================================================================================

    // 삭제 기능

    public void deleteMenu(int menuCode) {
        repository.deleteById(menuCode);
    }
//=============================================================================================================

    //DML 구문 이기때문에
    @Transactional
    public void registNewMenu(MenuDTO newMenu){
        //지금까지는 Entity 를 DTO 변환 했다면 DML 구문에서는 DTO 타입을 Entity 로
        // 변환해야 P
        repository.save(modelMapper.map(newMenu, Menu.class));
    }


    @Transactional
    public void modifyMenu(MenuDTO modifyMenu) {

        /* update 는 엔티티를 특정해서 필드의 값을 변경해주면 된다.
        *  JPA 는 변경감지 기능이 있다.
        *  따라서 하나의 엔티티를 특정해서 필드 값을 변경하면
        *  변경된 값으로 flush(반영) 을 해준다. */

        // 엔티티 찾기(특정)
        Menu foundMenu = repository.findById(modifyMenu.getMenuCode())
                .orElseThrow(IllegalArgumentException::new);

        System.out.println("찾은 Entity 값 = " + foundMenu);



//        /* 1. setter 를 통해 update 기능 - (지양한다.)*/
//        foundMenu.setMenuName(modifyMenu.getMenuName());

//        /*2. @Builder 를 통해 update */
//        foundMenu=foundMenu.toBuilder()
//                .menuName(modifyMenu.getMenuName())
//                .build();
//        // build 를 통해서 foundMenu 새롭게 탄생 시켰으니
//        // save 메소드를 통해 jpa 에게 전달
//
//        repository.save(foundMenu);

        /*3. Entity 내부에 Builder 패턴을 사용*/
        foundMenu =foundMenu.menuName(modifyMenu.getMenuName())
                        .builder();

        repository.save(foundMenu);

        System.out.println("setter 사용후! = " + foundMenu);
    }

//====================================================================================================================





}
