package edu.noia.myoffice.common.rest.util;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.beans.PropertyEditorSupport;
import java.util.Optional;
import java.util.function.Function;

import static edu.noia.myoffice.common.util.exception.ExceptionSuppliers.notFound;

/**
 * {@link PropertyEditorSupport} pour récupérer un objet à partir d'un string. Le string sera transformé dans un identifiant.
 * Ensuite l'identfiant sera utilisé pour récupér l'objet (en général une entity)
 *
 * @param <E> le type de l'objet
 * @param <I> le type de l'identifiant
 */
@RequiredArgsConstructor
public class EntityPropertyEditorSupport<E, I> extends PropertyEditorSupport {

    /**
     * indique comment transformer un string en un objet de type {@literal I}
     */
    @NonNull
    private Function<String, I> textToIdentifiant;
    /**
     * indique comment transformer un identifiant en un objet de type {@literal E}
     */
    @NonNull
    private Function<I, Optional<E>> identifiantToEntity;
    /**
     * indique comment transformer un objet de type {@literal I} en un string
     */
    @NonNull
    private Function<I, String> identifiantToText;
    /**
     * indique comment transformer un objet de type {@literal E} en un identifiant
     */
    @NonNull
    private Function<E, I> entityToIdentifiant;
    /**
     * la classe {@literal E}
     */
    @NonNull
    private Class<E> entityClass;

    @Override
    public String getAsText() {
        return Optional.ofNullable(this.getValue())
                .map(value -> (E)value)
                .map(entityToIdentifiant)
                .map(identifiantToText)
                .orElseThrow(notFound(entityClass));
    }

    @Override
    public void setAsText(String text) {
        E entity = Optional.ofNullable(text)
                .map(textToIdentifiant)
                .flatMap(identifiantToEntity)
                .orElseThrow(notFound(entityClass, text));
        this.setValue(entity);
    }
}
