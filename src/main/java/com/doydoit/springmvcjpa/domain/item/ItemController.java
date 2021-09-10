package com.doydoit.springmvcjpa.domain.item;

import com.doydoit.springmvcjpa.web.item.ItemForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

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
        return "item/itemList";
    }

    /**
     * 아이템 등록 폼
     */
    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("item" ,new Item());
        return "item/addForm";
    }

    /**
     * 아이템 등록
     */
    @PostMapping("/add")
    public String add(@ModelAttribute("item") ItemForm item, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (!StringUtils.hasText(item.getItemName())) {
            bindingResult.addError(new FieldError("item", "itemName",item.getItemName(),false, new String[]{"require.item.itemName"},null, null));
        }

        if (item.getPrice() == null || item.getPrice() < 1000 || item.getPrice() >1000000) {
            bindingResult.addError(new FieldError("item", "price", item.getPrice(),false, new String[]{"range.item.price"},new Object[]{1000,1000000}, null));
        }

        if (item.getStockQuantity() == null || item.getStockQuantity() > 9999) {
            bindingResult.addError(new FieldError("item", "stockQuantity",item.getStockQuantity(),false,new String[]{"max.item.quantity"},new Object[]{9999}, null));
        }

        if (item.getPrice() != null && item.getStockQuantity() != null) {
            int resultPrice = item.getPrice() * item.getStockQuantity();
            if (resultPrice < 10000) {
                bindingResult.addError(new ObjectError("item",new String[]{"totalPriceMin"},new Object[]{10000,resultPrice}, null));
            }
        }

        if (bindingResult.hasErrors()) {
            return "item/addForm";
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
        return "item/item";
    }

    /**
     * 아이템 수정 폼
     */
    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable("itemId") Long itemId, Model model) {
        Item item = itemService.findById(itemId);
        model.addAttribute("item", item);
        return "item/editItem";
    }

    /**
     * 아이템 수정
     */
    @PostMapping("/{itemId}/edit")
    public String edit(@PathVariable("itemId") Long itemId, @ModelAttribute("item") ItemForm item, RedirectAttributes redirectAttributes) {
        itemService.update(itemId, item);
        redirectAttributes.addAttribute("itemId", itemId);
        return "redirect:/items/{itemId}";
    }
}
