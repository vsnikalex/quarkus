package com.tsystems.panache.active;

import javax.persistence.LockModeType;
import javax.transaction.Transactional;
import javax.ws.rs.GET;

public class PersonEndpoint {

    @GET
    @Transactional
    public PersonAct findByIdForUpdate(Long id) {
        PersonAct person = PersonAct.findById(id, LockModeType.PESSIMISTIC_WRITE);
        // do something useful, the lock will be released when the transaction ends.
        return person;
    }

    @GET
    @Transactional
    public PersonAct findByNameForUpdate(String name) {
        PersonAct person = PersonAct.find("name", name).withLock(LockModeType.PESSIMISTIC_WRITE).firstResult();
        // do something useful, the lock will be released when the transaction ends.
        return person;
    }

}
