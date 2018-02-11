package edu.noia.myoffice.common.rest.util;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.beans.PropertyEditorSupport;
import java.util.Optional;
import java.util.function.Function;

import static edu.noia.myoffice.common.util.exception.ExceptionSuppliers.anyException;

@RequiredArgsConstructor
public class IdentifiantPropertyEditorSupport<I> extends PropertyEditorSupport {
    @NonNull
    private Function<String, I> textToIdentifiant;

    @Override
    public void setAsText(String text) {
        I identifiant = Optional.ofNullable(text)
                .map(this.textToIdentifiant)
                .orElseThrow(anyException(String.format("No identifier in %s has been found", text)));
        this.setValue(identifiant);
    }
}
