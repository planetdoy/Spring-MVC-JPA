package com.doydoit.springmvcjpa.domain.item;

import com.doydoit.springmvcjpa.web.item.ItemForm;
import com.doydoit.springmvcjpa.web.item.ItemSaveForm;
import com.doydoit.springmvcjpa.web.item.ItemUpdateForm;
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
        model.addAttribute("item", new ItemSaveForm());
        return "item/addForm";
    }

    /**
     * 아이템 등록
     */
    @PostMapping("/add")
    public String add(@Validated @ModelAttribute("item") ItemSaveForm form, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (form.getPrice() != null && form.getStockQuantity() != null) {
            int resultPrice = form.getPrice() * form.getStockQuantity();
            if (resultPrice < 10000) {
                bindingResult.reject("totalPriceMin",new Object[]{10000,resultPrice},null);
//                bindingResult.addError(new ObjectError("item",new String[]{"totalPriceMin"},new Object[]{10000,resultPrice},null));
            }
        }

        if (bindingResult.hasErrors()) {
            log.info("errors ={}", bindingResult.toString());
            return "item/addForm";
        }

        Item item = new Item(form.getItemName(), form.getPrice(), form.getStockQuantity());

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
    public String edit(@PathVariable("itemId") Long itemId,
                       @Validated @ModelAttribute("item") ItemUpdateForm form,
                       BindingResult bindingResult,
                       RedirectAttributes redirectAttributes) {

        if (form.getPrice() != null && form.getStockQuantity() != null) {
            int resultPrice = form.getPrice() * form.getStockQuantity();
            if (resultPrice < 10000) {
                bindingResult.reject("totalPriceMin",new Object[]{10000,resultPrice},null);
            }
        }

        if (bindingResult.hasErrors()) {
            log.info("errors ={}", bindingResult.toString());
            return "item/editItem";
        }

        Item item = new Item(form.getId(), form.getItemName(), form.getPrice(), form.getStockQuantity());

        itemService.update(itemId, item);
        redirectAttributes.addAttribute("itemId", itemId);

        return "redirect:/items/{itemId}";
    }
}
