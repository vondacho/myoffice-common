package edu.noia.myoffice.common.data.jpa.hibernate;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;
import org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy;

public class PhysicalNamingStrategy extends SpringPhysicalNamingStrategy {

    private static final String PREFIX_TABLE_DB = "myo_";

    @Override
    public Identifier toPhysicalTableName(Identifier name, JdbcEnvironment context) {
        return super.toPhysicalTableName(
                new Identifier(PREFIX_TABLE_DB + name.getText()
                        .replaceFirst("Jpa","")
                        .replaceFirst("State", ""), name.isQuoted()),
                context);
    }

}
