package edu.noia.myoffice.common.data.jpa.hibernate.naming;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;
import org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy;

public class PhysicalNamingStrategy extends SpringPhysicalNamingStrategy {

    private String PREFIX_TABLE_DB = "t_";

    @Override
    public Identifier toPhysicalTableName(Identifier name, JdbcEnvironment context) {
        return super.toPhysicalTableName(
                new Identifier(
                        name.getText().contains(PREFIX_TABLE_DB) ? name.getText() : PREFIX_TABLE_DB + name.getText(),
                        name.isQuoted()),
                context);
    }

}
