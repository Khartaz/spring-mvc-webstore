package com.packt.webstore.domain;

import java.io.Serializable;
import java.util.Objects;

public class Order implements Serializable {
    private static final long serialVersionUID = -3560539622417210365L;
    private Long orderId;
    private Cart cart;
    private Customer customer;
    private ShippingDetail shippingDetail;

    public Order(){
        this.customer = new Customer();
        this.shippingDetail = new ShippingDetail();
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getOrderId() {
        return orderId;
    }

    public Cart getCart() {
        return cart;
    }

    public Customer getCustomer() {
        return customer;
    }

    public ShippingDetail getShippingDetail() {
        return shippingDetail;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setShippingDetail(ShippingDetail shippingDetail) {
        this.shippingDetail = shippingDetail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(orderId, order.orderId) &&
                Objects.equals(cart, order.cart) &&
                Objects.equals(customer, order.customer) &&
                Objects.equals(shippingDetail, order.shippingDetail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, cart, customer, shippingDetail);
    }
}
