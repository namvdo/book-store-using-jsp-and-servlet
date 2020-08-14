/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Nam
 */
public class CartBean implements Serializable {
    private List<LineItemBean> items;

	public CartBean(List<LineItemBean> items) {
		this.items = items;
	}

	public CartBean() {
		this.items = new ArrayList<LineItemBean>();
	}

	public List<LineItemBean> getItems() {
		return items;
	}

	public void setItems(List<LineItemBean> items) {
		this.items = items;
	}

	public void add(LineItemBean item) {
        //If the item already exists in the cart, only the quantity is changed.
        String code = item.getIsbn();
        int quantity = item.getQuantity();
        for (int i = 0; i<items.size(); i++) {
            LineItemBean lineItem = items.get(i);
            if (lineItem.getIsbn().equals(code)) {
                lineItem.setQuantity(quantity);
                return;
            }
        } 
        items.add(item);
    }
	
	// Used to empty cart after order has been processed
	public void removeAllItems() {
		items = new ArrayList<LineItemBean>();
	}
    
    public void remove(LineItemBean item) {
        String code = item.getIsbn();
        for (int i = 0; i<items.size(); i++) {
            LineItemBean lineItem = items.get(i);
            if (lineItem.getIsbn().equals(code)) {
                items.remove(i);
                return;
            }
        }
    }	
}
