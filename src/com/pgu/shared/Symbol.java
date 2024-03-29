package com.pgu.shared;

import javax.persistence.Id;

import com.google.gwt.user.client.rpc.IsSerializable;

public class Symbol implements IsSerializable {

    public enum Group {
        HIRAGANA, KATAKANA, RUSSIAN
    }

    @Id
    private Long id;
    private Group group;
    private String alpha;
    private String unicode;

    public Symbol() {
        super();
    }

    public Symbol(final Group group, final String alpha, final String unicode) {
        this.group = group;
        this.alpha = alpha;
        this.unicode = unicode;
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
        final Symbol other = (Symbol) obj;
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
        return "Symbol [id=" + id + ", group=" + group + ", alpha=" + alpha + ", unicode=" + unicode + "]";
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(final Group group) {
        this.group = group;
    }

    public String getAlpha() {
        return alpha;
    }

    public void setAlpha(final String alpha) {
        this.alpha = alpha;
    }

    public String getUnicode() {
        return unicode;
    }

    public void setUnicode(final String unicode) {
        this.unicode = unicode;
    }

}
