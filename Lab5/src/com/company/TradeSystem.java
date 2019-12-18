package com.company;

import java.io.Serializable;
import java.util.ArrayList;

public class TradeSystem implements Serializable {
    public ArrayList<Client> users = new ArrayList<Client>();
    public ArrayList<Request> sellerRequests = new ArrayList<Request>();
    public ArrayList<Request> buyerRequests = new ArrayList<Request>();
    private Client currentUser = null;

    void addUser(String n, String l, String p){
        users.add(new Client(n, l, p));
    }

    boolean findUser(String l, String p){
        for (Client person : users) {
            if (person.enter(l, p)){
                currentUser = person;
                return true;
            }
        }
        return false;
    }

    ArrayList<Request> processRequests(){
        ArrayList<Request> res = new ArrayList<Request>();
        sellerRequests.sort(Request.priceComparator);
        buyerRequests.sort(Request.reversePriceComparator);
        for (Request rq : sellerRequests) {
            for (Request rqst : buyerRequests) {
                if(rq.getProduct().equals(rqst.getProduct()) && rq.getPrice()<=rqst.getPrice() && rq.getCount()!=0 && rqst.getCount()!=0){
                    if(rq.getCount()>rqst.getCount()){
                        System.out.println("Покупатель " + rqst.getRequester().getName() + " покупает товар " + rqst.getProduct() + " у продавца " + rq.getRequester().getName() + " по цене " + rqst.getPrice() + " в количестве " + (rqst.getCount()));
                        rq.setCount(rq.getCount()-rqst.getCount());
                        rqst.setCount(0);
                    }
                    else{
                        System.out.println("Покупатель " + rqst.getRequester().getName() + " покупает товар " + rqst.getProduct() + " у продавца " + rq.getRequester().getName() + " по цене " + rqst.getPrice() + " в количестве " + (rq.getCount()));
                        rqst.setCount(rqst.getCount()-rq.getCount());
                        rq.setCount(0);
                    }
                }
            }
        }
        for (int i = 0; i < sellerRequests.size(); i++) {
            if(sellerRequests.get(i).getCount() == 0){
                res.add(sellerRequests.remove(i));
                i--;
            }
        }
        for (int i = 0; i < buyerRequests.size(); i++) {
            if(buyerRequests.get(i).getCount() == 0){
                res.add(buyerRequests.remove(i));
                i--;
            }
        }
        return res;
    }

    public Client getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(Client currentUser) {
        this.currentUser = currentUser;
    }
}
