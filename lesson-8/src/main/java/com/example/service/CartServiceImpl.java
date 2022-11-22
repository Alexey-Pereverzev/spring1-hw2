package com.example.service;

import com.example.persist.ClientRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class CartServiceImpl implements CartService {

    private final ArrayList<LineItemRepr> cart = new ArrayList<>();

    private final ClientRepository clients;

    private Long currentId = 0L;

    public CartServiceImpl(ClientRepository clients) {
        this.clients = clients;
    }

    @Override
    public LineItemRepr addToCart(ProductRepr product) {
        ClientRepr currentClient = getCurrentClient();
        LineItemRepr lineItemRepr = new LineItemRepr(currentId + 1, currentClient, product, product.getCost(), 1,"");
        for (LineItemRepr itemRepr : cart) {
            if (Objects.equals(itemRepr.getProduct().getId(), product.getId())
                    && Objects.equals(itemRepr.getClient().getId(), currentClient.getId())) {
                itemRepr.setQty(itemRepr.getQty() + 1);
                return itemRepr;
            }
        }
        cart.add(lineItemRepr);
        currentId = currentId + 1;
        return lineItemRepr;
    }

    @Override
    public List<LineItemRepr> showCartForCurrentClient() {
        ClientRepr currentClient = getCurrentClient();
        ArrayList<LineItemRepr> cartForCurrentClient = new ArrayList<>();
        for (LineItemRepr lineItemRepr : cart) {
            if (lineItemRepr.getClient().getName().equals(currentClient.getName())) {
                cartForCurrentClient.add(lineItemRepr);
            }
        }
        return cartForCurrentClient;
    }

    private ClientRepr getCurrentClient() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return new ClientRepr(clients.findClientByName(user.getUsername()).orElseThrow());
    }

    @Override
    public void deleteLineItem(LineItemRepr lineItem) {
        cart.remove(lineItem);
    }

}
