package com.doydoit.springmvcjpa.domain.item;

import com.doydoit.springmvcjpa.web.item.ItemForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Slf4j
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
        model.addAttribute("itemForm", new ItemForm());
        return "item/addForm";
    }

    /**
     * 아이템 등록
     */
    @PostMapping("/add")
    public String add(@Validated @ModelAttribute("itemForm") ItemForm item, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (item.getPrice() != null && item.getStockQuantity() != null) {
            int resultPrice = item.getPrice() * item.getStockQuantity();
            if (resultPrice < 10000) {
                bindingResult.reject("totalPriceMin",new Object[]{10000,resultPrice},null);
//                bindingResult.addError(new ObjectError("item",new String[]{"totalPriceMin"},new Object[]{10000,resultPrice},null));
            }
        }

        if (bindingResult.hasErrors()) {
            log.info("errors ={}", bindingResult.toString());
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
        ItemForm itemForm = new ItemForm(item.getId(), item.getItemName(), item.getPrice(), item.getStockQuantity());
        model.addAttribute("itemForm", itemForm);
        return "item/item";
    }

    /**
     * 아이템 수정 폼
     */
    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable("itemId") Long itemId, Model model) {
        Item item = itemService.findById(itemId);
        ItemForm itemForm = new ItemForm(item.getId(), item.getItemName(), item.getPrice(), item.getStockQuantity());
        model.addAttribute("itemForm", itemForm);
        return "item/editItem";
    }

    /**
     * 아이템 수정
     */
    @PostMapping("/{itemId}/edit")
    public String edit(@PathVariable("itemId") Long itemId,
                       @Validated @ModelAttribute("itemForm") ItemForm item,
                       BindingResult bindingResult,
                       RedirectAttributes redirectAttributes) {

        if (item.getPrice() != null && item.getStockQuantity() != null) {
            int resultPrice = item.getPrice() * item.getStockQuantity();
            if (resultPrice < 10000) {
                bindingResult.reject("totalPriceMin",new Object[]{10000,resultPrice},null);
            }
        }

        if (bindingResult.hasErrors()) {
            log.info("errors ={}", bindingResult.toString());
            return "item/editItem";
        }

        itemService.update(itemId, item);
        redirectAttributes.addAttribute("itemId", itemId);

        return "redirect:/items/{itemId}";
    }
}
