package edu.noia.myoffice.common.data.jpa.hibernate.type;

import org.hibernate.usertype.UserType;

import java.io.Serializable;

public abstract class AbstractUserType implements UserType {

    @Override
    public boolean equals(Object x, Object y) {
        return x != null && x.equals(y);
    }

    @Override
    public int hashCode(Object x) {
        return x.hashCode();
    }

    @Override
    public Object deepCopy(Object value) {
        return value;
    }

    @Override
    public boolean isMutable() {
        return false;
    }

    @Override
    public Serializable disassemble(Object value) {
        return (Serializable) value;
    }

    @Override
    public Object assemble(Serializable cached, Object owner) {
        return cached;
    }

    @Override
    public Object replace(Object original, Object target, Object owner) {
        return original;
    }
}
