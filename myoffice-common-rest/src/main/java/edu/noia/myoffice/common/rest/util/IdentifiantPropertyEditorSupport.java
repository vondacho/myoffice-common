package edu.noia.myoffice.common.rest.util;

import edu.noia.myoffice.common.domain.exception.ResourceNotFoundException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.beans.PropertyEditorSupport;
import java.util.Optional;
import java.util.function.Function;

@RequiredArgsConstructor
public class IdentifiantPropertyEditorSupport<I> extends PropertyEditorSupport {
    @NonNull
    private Function<String, I> textToIdentifiant;

    public void setAsText(String text) {
        I identifiant = Optional.ofNullable(text)
                .map(this.textToIdentifiant)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("No identifier in %s has been found", text)));
        this.setValue(identifiant);
    }
}
