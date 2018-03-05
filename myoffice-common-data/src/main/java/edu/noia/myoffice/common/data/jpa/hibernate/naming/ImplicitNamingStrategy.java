package edu.noia.myoffice.common.data.jpa.hibernate.naming;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.ImplicitJoinColumnNameSource;
import org.springframework.boot.orm.jpa.hibernate.SpringImplicitNamingStrategy;

public class ImplicitNamingStrategy extends SpringImplicitNamingStrategy {

    private static final String UNDERSCORE = "_";
    private static final String EMPTY_STRING = "";
    private static final String PREFIX_FK_DB = "fk_";

    @Override
    public Identifier determineJoinColumnName(ImplicitJoinColumnNameSource source) {
        return toIdentifier(PREFIX_FK_DB + super.determineJoinColumnName(source).getText()
                .replaceFirst(UNDERSCORE + source.getReferencedColumnName().getText(), EMPTY_STRING), source.getBuildingContext());
    }
}
