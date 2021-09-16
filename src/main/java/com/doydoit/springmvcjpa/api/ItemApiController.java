package com.doydoit.springmvcjpa.api;

import com.doydoit.springmvcjpa.domain.item.Item;
import com.doydoit.springmvcjpa.domain.item.ItemService;
import com.doydoit.springmvcjpa.exhandler.ErrorResult;
import com.doydoit.springmvcjpa.web.item.ItemSaveForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequestMapping("/api/items")
@RequiredArgsConstructor
@RestController
public class ItemApiController {

    private final ItemService itemService;

    /**
     * 상품 등록 API
     */
    @PostMapping("/add")
    public Object add(@Validated @RequestBody ItemSaveForm form, BindingResult bindingResult) {

        log.info("API 컨트롤러 호출");

        //검증 실패 시
        if (bindingResult.hasErrors()) {
            log.info("검증 오류 발생", bindingResult);
            return bindingResult.getAllErrors();
        }

        log.info("성공 로직 실행");

        Item item = new Item(form.getItemName(), form.getPrice(), form.getStockQuantity());
        Long saveResult = itemService.save(item);
        SuccessForm successForm = new SuccessForm(item);

        return successForm;
    }

}
