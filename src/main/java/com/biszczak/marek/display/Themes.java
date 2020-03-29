package com.biszczak.marek.display;

public enum Themes {
    ASTRIX_DISPLAY_STRATEGY("Atrix display"),
    SLASH_DISPLAY_STRATEGY("Slash display Strategy"),
    TRIANGLE_DISPLAY_STRATEGY("Triangle display strategy");


    private final String themeName;

    Themes(String themeName) {
        this.themeName = themeName;
    }

    public String getOptionName() {
        return themeName;
    }
}
