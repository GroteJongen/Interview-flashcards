package com.biszczak.marek;

public enum MenuOptions {
    CHANGE_THEME("Change Theme"),
    EXIT("Exit"),
    IMPORT("Import"),
    EXPORT("Export"),
    PLAY("Play");

    private final String OPTION_NAME;

    MenuOptions(String OPTION_NAME) {
        this.OPTION_NAME = OPTION_NAME;
    }

    public String getOPTION_NAME() {
        return OPTION_NAME;
    }
}
