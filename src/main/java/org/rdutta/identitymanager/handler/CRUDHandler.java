package org.rdutta.identitymanager.handler;

import org.rdutta.identitymanager.dao.features.ProfileManagement;

public interface CRUDHandler {
    Object handle(ProfileManagement management) throws Exception;
    void setNext(CRUDHandler nextHandler);
}
