package edu.noia.myoffice.common.rest.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Getter
@AllArgsConstructor(staticName = "of")
public class SearchCriteria {
    private String key;
    private String operation;
    private Object value;

    public static List<SearchCriteria> of(String filter) {
        List<SearchCriteria> result = new ArrayList();
        Pattern pattern = Pattern.compile("(\\w+?)(:|<|>)(\\w+?),");
        Matcher matcher = pattern.matcher(filter + ",");
        while (matcher.find()) {
            result.add(of(matcher.group(1), matcher.group(2), matcher.group(3)));
        }
        return result;
    }

    public <E> Optional<Specification<E>> toSpecification() {
        return Optional.ofNullable(toSpec());
    }

    private <E> Specification<E> toSpec() {
        return (root, query, builder) -> {
            if (getOperation().equalsIgnoreCase(">")) {
                return builder.greaterThanOrEqualTo(root.get(getKey()), getValue().toString());
            }
            else if (getOperation().equalsIgnoreCase("<")) {
                return builder.lessThanOrEqualTo(root.get(getKey()), getValue().toString());
            }
            else if (getOperation().equalsIgnoreCase(":")) {
                if (root.get(getKey()).getJavaType() == String.class) {
                    return builder.like(root.get(getKey()), getValue() + "%");
                } else {
                    return builder.equal(root.get(getKey()), getValue());
                }
            }
            return null;
        };
    }
}