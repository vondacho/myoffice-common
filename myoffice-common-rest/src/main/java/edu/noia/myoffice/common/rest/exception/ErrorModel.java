package edu.noia.myoffice.common.rest.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ErrorModel {

    private String field;
    private String message;

    public ErrorModel(Exception e) {
        this.message = e.getMessage();
    }
}
