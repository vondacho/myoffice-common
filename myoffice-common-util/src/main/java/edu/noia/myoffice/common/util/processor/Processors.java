package edu.noia.myoffice.common.util.processor;

import java.util.Optional;

public class Processors {

    private Processors() {
    }

    public static final Processor noProcessing = Optional::of;
}
