package wrappers;

import beans.Subscription;
import beans.SubscriptionBill;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoadUserWindowWrapper {
    List<SubscriptionBill> subscriptionBillList = new ArrayList<>();
    Map<String, Subscription> map = new HashMap<>();

    public LoadUserWindowWrapper() {
    }

    private LoadUserWindowWrapper(Builder builder) {
        this.subscriptionBillList = builder.subscriptionBillList;
        this.map = builder.map;
    }

    public List<SubscriptionBill> getSubscriptionBillList() {
        return subscriptionBillList;
    }

    public Map<String, Subscription> getMap() {
        return map;
    }

    public static class Builder {
        List<SubscriptionBill> subscriptionBillList = new ArrayList<>();
        Map<String, Subscription> map = new HashMap<>();

        public Builder setSubscriptionBillList(List<SubscriptionBill> subscriptionBillList) {
            this.subscriptionBillList = subscriptionBillList;
            return this;
        }

        public Builder setMap(Map<String, Subscription> map) {
            this.map = map;
            return this;
        }

        public LoadUserWindowWrapper build(){
            return new LoadUserWindowWrapper(this);
        }
    }
}
