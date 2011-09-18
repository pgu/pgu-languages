package com.pgu.shared;

import javax.persistence.Id;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.googlecode.objectify.Objectify;
import com.pgu.server.dao.ObjectifyDao;

public class UserAccount implements IsSerializable {

    public enum ProviderAuth {
        GOOGLE {
            @Override
            public void setUserInDBAndSession(final String id, final String name, final HttpServletRequest request) {
                final UserAccount u = new UserAccount();
                u.setId(null);
                u.setProviderAuth(this);
                u.setName(name);

                final Objectify ofy = ObjectifyDao.ofy();
                final UserAccount uDB = ofy.query(UserAccount.class)//
                        .filter("name", u.getName()) //
                        .filter("providerAuth", ProviderAuth.GOOGLE) //
                        .get();
                setUserInSession(u, ofy, uDB, request);
            }
        }, //
        FACEBOOK {
            @Override
            public void setUserInDBAndSession(final String id, final String name, final HttpServletRequest request) {
                final UserAccount u = new UserAccount();
                u.setId(Long.parseLong(id));
                u.setProviderAuth(this);
                u.setName(name);

                final Objectify ofy = ObjectifyDao.ofy();
                final UserAccount uDB = ofy.get(UserAccount.class, u.getId());
                setUserInSession(u, ofy, uDB, request);
            }

        }, //
        TWITTER {
            @Override
            public void setUserInDBAndSession(final String id, final String name, final HttpServletRequest request) {
                final UserAccount u = new UserAccount();
                u.setId(Long.parseLong(id));
                u.setProviderAuth(this);
                u.setName(name);

                final Objectify ofy = ObjectifyDao.ofy();
                final UserAccount uDB = ofy.get(UserAccount.class, u.getId());
                setUserInSession(u, ofy, uDB, request);
            }
        };

        public abstract void setUserInDBAndSession(final String id, final String name, HttpServletRequest request);

        public void setUserInSession(final UserAccount u, final Objectify ofy, UserAccount uDB,
                final HttpServletRequest request) {
            if (null == uDB) {
                ofy.put(u);
                uDB = u;
            }
            final HttpSession session = request.getSession();
            session.setAttribute("userId", uDB.getId());
            session.setAttribute("loggedin", true);

            System.out.println("User id:" + uDB.getId() + ", " + uDB.getName());
        }
    }

    @Id
    private Long id;
    private String name;
    private ProviderAuth providerAuth;

    public UserAccount() {
    }

    public UserAccount(final Long id, final ProviderAuth providerAuth) {
        this.id = id;
        this.providerAuth = providerAuth;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public ProviderAuth getProviderAuth() {
        return providerAuth;
    }

    public void setProviderAuth(final ProviderAuth providerAuth) {
        this.providerAuth = providerAuth;
    }

}
