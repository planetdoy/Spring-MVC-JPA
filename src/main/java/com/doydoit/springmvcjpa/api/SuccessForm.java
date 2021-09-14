package com.doydoit.springmvcjpa.api;

import com.doydoit.springmvcjpa.domain.item.Item;
import lombok.Data;

@Data
public class SuccessForm {
    private boolean success = true;
    private Item item;

    public SuccessForm(Item item) {
        this.item = item;
    }
}
