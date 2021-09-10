package com.doydoit.springmvcjpa.domain.item;

import com.doydoit.springmvcjpa.web.item.ItemForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/items")
@RequiredArgsConstructor
@Controller
public class ItemController {

    private final ItemService itemService;

    /**
     * 아이템 전체 조회
     */
    @GetMapping
    public String items(Model model) {
        List<Item> items = itemService.findAll();
        model.addAttribute("items", items);
        return "item/itemList.html";
    }

    /**
     * 아이템 등록 폼
     */
    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("item" ,new Item());
        return "item/addForm.html";
    }

    /**
     * 아이템 등록
     */
    @PostMapping("/add")
    public String add(@ModelAttribute("item") ItemForm item, RedirectAttributes redirectAttributes, Model model) {

        Map<String,String> errors = new HashMap<>();

        if (!StringUtils.hasText(item.getName())) {
            errors.put("name", "상품 명은 필수입니다.");
        }

        if (item.getPrice() == null || item.getPrice() < 1000 || item.getPrice() >1000000) {
            errors.put("price", "상품 가격은 1,000원 이상 1,000,000원 미만이어야 합니다.");
        }

        if (item.getStockQuantity() == null || item.getStockQuantity() > 9999) {
            errors.put("quantity", "수량은 최대 9,999까지 허용합니다.");
        }

        if (item.getPrice() != null && item.getStockQuantity() != null) {
            int resultPrice = item.getPrice() * item.getStockQuantity();
            if (resultPrice < 10000) {
                errors.put("globalError", "가격 * 수량은 10,000원 이상이어야 합니다. 현재 값 " + resultPrice);
            }
        }

        if (!errors.isEmpty()) {
            model.addAttribute("errors",errors);
            return "item/addForm.html";
        }

        Long itemId = itemService.save(item);
        redirectAttributes.addAttribute("itemId", itemId);
        redirectAttributes.addAttribute("status", true);
        return "redirect:/items/{itemId}";
    }

    /**
     * 단일 아이템 조회
     */
    @GetMapping("/{itemId}")
    public String item(@PathVariable("itemId") Long itemId, Model model) {

        Item item = itemService.findById(itemId);
        model.addAttribute("item", item);
        return "item/item.html";
    }

    /**
     * 아이템 수정 폼
     */
    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable("itemId") Long itemId, Model model) {
        Item item = itemService.findById(itemId);
        model.addAttribute("item", item);
        return "item/editItem.html";
    }

    /**
     * 아이템 수정
     */
    @PostMapping("/{itemId}/edit")
    public String edit(@PathVariable("itemId") Long itemId,@ModelAttribute("item") ItemForm item) {
        itemService.update(itemId, item);
        return "redirect:/items/"+itemId;
    }
}
