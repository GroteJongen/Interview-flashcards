package com.biszczak.marek;

public enum MenuOptions {
    CHANGE_THEME("Change Theme"),
    PLAY("Play"),
    DISPLAY_THEME("Display Themes"),
    IMPORT("Import"),
    EXPORT("Export"),
    EXIT("Exit");

    private final String optionName;

    MenuOptions(String optionName) {
        this.optionName = optionName;
    }

    public String getOptionName() {
        return optionName;
    }
}
