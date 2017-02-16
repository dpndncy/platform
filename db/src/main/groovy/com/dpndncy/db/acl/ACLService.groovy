package com.dpndncy.db.acl

import com.dpndncy.db.entity.Auditable
import com.dpndncy.db.entity.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.acls.domain.ObjectIdentityImpl
import org.springframework.security.acls.domain.PrincipalSid
import org.springframework.security.acls.model.AccessControlEntry
import org.springframework.security.acls.model.MutableAcl
import org.springframework.security.acls.model.MutableAclService
import org.springframework.security.acls.model.NotFoundException
import org.springframework.security.acls.model.ObjectIdentity
import org.springframework.security.acls.model.Permission
import org.springframework.security.acls.model.Sid
import org.springframework.transaction.annotation.Transactional
import org.springframework.util.Assert


/**
 * Created by vaibhav on 14/02/17.
 */
class ACLService {

    @Autowired
    MutableAclService mutableAclService;

    @Transactional(readOnly = false, value = "dataSourceTransactionManager")
    public void addPermission(Auditable element, User recipient, Permission permission) {
        addPermission(element, getSidForUser(recipient), permission);
    }

    @Transactional(readOnly = false, value = "dataSourceTransactionManager")
    public void addPermission(Auditable element, Sid recipient, Permission permission) {
        MutableAcl acl;
        ObjectIdentity oid = new ObjectIdentityImpl(element);

        try {
            acl = (MutableAcl) mutableAclService.readAclById(oid);
        } catch (NotFoundException ignored) {
            acl = mutableAclService.createAcl(oid);
        }

        acl.insertAce(acl.getEntries().size(), permission, recipient, true);
        mutableAclService.updateAcl(acl);

    }

    @Transactional(readOnly = false, value = "dataSourceTransactionManager")
    public boolean addAccessControlEntry(Auditable element, User recipient, Permission permission) {
        addAccessControlEntry(element, getSidForUser(recipient), permission);
    }

    @Transactional(readOnly = false, value = "dataSourceTransactionManager")
    public boolean addAccessControlEntry(Auditable element, Sid recipient, Permission permission) {
        Assert.notNull(element, "AbstractSecuredEntity required");
        Assert.notNull(recipient, "recipient required");
        Assert.notNull(permission, "permission required");

        MutableAcl acl;
        ObjectIdentity oid = new ObjectIdentityImpl(element);

        try {
            acl = (MutableAcl) mutableAclService.readAclById(oid);
        } catch (NotFoundException ignored) {
            acl = mutableAclService.createAcl(oid);
        }

        /*
         * handle duplicate ACL entries
         * http://forum.springsource.org/showthread.php?73022-Should-AclImpl-allow-duplicate-permissions
         */
        if (!doesACEExists(element, recipient, permission)) {
            acl.insertAce(acl.getEntries().size(), permission, recipient, true);
            mutableAclService.updateAcl(acl);
        }
        return true;
    }

    @Transactional(readOnly = false, value = "dataSourceTransactionManager")
    public void deletePermission(Auditable element) {
        // Delete the ACL information as well
        ObjectIdentity oid = new ObjectIdentityImpl(element);
        mutableAclService.deleteAcl(oid, false);
    }

    @Transactional(readOnly = false, value = "dataSourceTransactionManager")
    public boolean deleteAccessControlEntry(Auditable element, User recipient, Permission permission) {
        ObjectIdentity oid = new ObjectIdentityImpl(element);
        MutableAcl acl;
        try {
            acl = (MutableAcl) mutableAclService.readAclById(oid);
        } catch (NotFoundException ignored) {
            acl = mutableAclService.createAcl(oid);
        }

        List<AccessControlEntry> entries = acl.getEntries();
        for (int i = 0; i < entries.size(); i++) {
            if (entries.get(i).getSid() == getSidForUser(recipient) && entries.get(i).getPermission() == permission) {
                acl.deleteAce(i);
            }
        }

        mutableAclService.updateAcl(acl);

        return true;
    }

    private boolean doesACEExists(Auditable element, Sid recipient, Permission permission) {
        boolean result = false;
        ObjectIdentity oid = new ObjectIdentityImpl(element);
        MutableAcl acl;
        try {
            acl = (MutableAcl) mutableAclService.readAclById(oid);
        } catch (NotFoundException ignored) {
            acl = mutableAclService.createAcl(oid);
        }

        List<AccessControlEntry> entries = acl.getEntries();
        for(AccessControlEntry ace : entries) {
            if (ace.getSid() == recipient && ace.getPermission() == permission) {
                return true;
            }
        }
        return result;
    }

    private static Sid getSidForUser(User user) {
        return new PrincipalSid(user.login);
    }
}
