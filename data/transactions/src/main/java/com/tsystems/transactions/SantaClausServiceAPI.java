package com.tsystems.transactions;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.UserTransaction;

@ApplicationScoped
public class SantaClausServiceAPI {
    @Inject
    ChildDAO childDAO;
    @Inject
    SantaClausDAO santaDAO;
    @Inject
    UserTransaction transaction;

    public void getAGiftFromSanta(Child child, String giftDescription) throws Exception {
        // some transaction work
        try {
            transaction.begin();
            Gift gift = childDAO.addToGiftList(child, giftDescription);
            santaDAO.addToSantaTodoList(gift);
            transaction.commit();
        }
        catch(SomeException e) {
            // do something on Tx failure
            transaction.rollback();
        }
    }
}
