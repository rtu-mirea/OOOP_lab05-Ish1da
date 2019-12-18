package com.company;

import java.io.Serializable;
import java.util.Comparator;

public class Request  implements Serializable {
    private Client requester;
    private String product;
    private int price;
    private int count;
    private int type;

    Request(Client r, String pt, int pe, int c, int t){
        requester = r;
        product = pt;
        price = pe;
        count = c;
        type = t;
    }

    public Client getRequester() {
        return requester;
    }

    public String getProduct() {
        return product;
    }

    public int getPrice() {
        return price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getType() {
        return type;
    }

    int compareTo(Request req) {
        return (this.price - req.price);
    }

    public static Comparator<Request> priceComparator = new Comparator<Request>() {
        @Override
        public int compare(Request e1, Request e2) {
            return e1.price - e2.price;
        }
    };

    public static Comparator<Request> reversePriceComparator = new Comparator<Request>() {
        @Override
        public int compare(Request e1, Request e2) {
            return e2.price - e1.price;
        }
    };
}
