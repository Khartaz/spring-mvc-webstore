package com.packt.webstore.domain.repository.impl;

import com.packt.webstore.domain.Cart;
import com.packt.webstore.domain.repository.CartRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class InMemoryCartRepository implements CartRepository {
    private Map<String, Cart> listOfCards;

    public InMemoryCartRepository() {
        listOfCards = new HashMap<>();
    }

    public Cart create(Cart cart) {
        if(listOfCards.keySet().contains(cart.getCartId())) {
            throw new IllegalArgumentException
                    (String.format("Can not create cart. Cart with indicated id (%) already exists.", cart.getCartId()));
        }
        listOfCards.put(cart.getCartId(), cart);
        return cart;
    }

    public Cart read(String cartId) {
        return listOfCards.get(cartId);
    }

    public void update(String cartId, Cart cart) {
        if(!listOfCards.keySet().contains(cartId)) {
            throw new IllegalArgumentException
                    (String.format("Can not update cart. Cart with indicated id (%) does not exist.", cart.getCartId()));
        }
        listOfCards.put(cartId, cart);
    }

    public void delete(String cartId) {
        if(!listOfCards.keySet().contains(cartId)) {
            throw new IllegalArgumentException
                    (String.format("Can not delete cart. Cart with indicated id (%) does not exist.", cartId));
        }
        listOfCards.remove(cartId);
    }
}
