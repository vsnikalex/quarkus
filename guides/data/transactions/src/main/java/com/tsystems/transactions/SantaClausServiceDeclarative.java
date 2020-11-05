package com.tsystems.transactions;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.SystemException;
import javax.transaction.TransactionManager;
import javax.transaction.Transactional;

@ApplicationScoped
public class SantaClausServiceDeclarative {

    @Inject
    TransactionManager tm;
    @Inject
    ChildDAO childDAO;
    @Inject
    SantaClausDAO santaDAO;

    @Transactional
    public void getAGiftFromSanta(Child child, String giftDescription) throws SystemException {
        // some transaction work
        Gift gift = childDAO.addToGiftList(child, giftDescription);
        if (gift == null) {
//            throw new OMGGiftNotRecognizedException();
            tm.setRollbackOnly();
        }
        else {
            santaDAO.addToSantaTodoList(gift);
        }
    }
}
