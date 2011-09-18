package com.pgu.shared;

import javax.persistence.Id;

import com.google.gwt.user.client.rpc.IsSerializable;

public class UserAccount implements IsSerializable {

    public enum ProviderAuth {
        GOOGLE {
            @Override
            public UserAccount get(final String id, final String name) {
                final UserAccount u = new UserAccount();
                u.setId(null);
                u.setProviderAuth(this);
                u.setName(name);
                return u;
            }
        }, //
        FACEBOOK {
            @Override
            public UserAccount get(final String id, final String name) {
                final UserAccount u = new UserAccount();
                u.setId(Long.parseLong(id));
                u.setProviderAuth(this);
                u.setName(name);
                return u;
            }

        }, //
        TWITTER {
            @Override
            public UserAccount get(final String id, final String name) {
                final UserAccount u = new UserAccount();
                u.setId(Long.parseLong(id));
                u.setProviderAuth(this);
                u.setName(name);
                return u;
            }
        };

        public abstract UserAccount get(final String id, final String name);

    }

    @Id
    private Long id;
    private String name;
    private ProviderAuth providerAuth;
    private String lastActive;

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

    public void setLastActive(final String date) {
        lastActive = date;
    }

    public String getLastActive() {
        return lastActive;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (id == null ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final UserAccount other = (UserAccount) obj;
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "UserAccount [id=" + id + ", name=" + name + ", providerAuth=" + providerAuth + ", lastActive="
                + lastActive + "]";
    }

}
