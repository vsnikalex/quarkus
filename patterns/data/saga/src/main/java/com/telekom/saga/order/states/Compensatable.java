package com.telekom.saga.order.states;

public interface Compensatable {
    void onSuccess();
    void onFail();
}
