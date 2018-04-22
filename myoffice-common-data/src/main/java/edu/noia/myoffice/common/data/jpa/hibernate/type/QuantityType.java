package edu.noia.myoffice.common.data.jpa.hibernate.type;

import edu.noia.myoffice.common.domain.vo.Quantity;
import edu.noia.myoffice.common.domain.vo.Unit;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.type.BigDecimalType;
import org.hibernate.type.StringType;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class QuantityType extends AbstractUserType {

    @Override
    public int[] sqlTypes() {
        return new int[]{
                BigDecimalType.INSTANCE.sqlType(), // quantity value
                StringType.INSTANCE.sqlType(), // unit
        };
    }

    @Override
    public Class returnedClass() {
        return Quantity.class;
    }

    @Override
    public Object nullSafeGet(ResultSet rs, String[] names, SharedSessionContractImplementor session, Object owner) throws SQLException {
        final Optional<BigDecimal> value = Optional.ofNullable(rs.getBigDecimal(names[0]));
        final Optional<String> unit = Optional.ofNullable(rs.getString(names[1]));
        return unit.map(Unit::valueOf).flatMap(u -> value.map(v -> new Quantity(v, u))).orElse(null);
    }

    @Override
    public void nullSafeSet(PreparedStatement st, Object value, int index, SharedSessionContractImplementor session) throws SQLException {
        Quantity quantity = (Quantity) value;
        st.setBigDecimal(index++, quantity.itimes(100L).getValue());
        st.setString(index, quantity.getUnit().toString());
    }
}
