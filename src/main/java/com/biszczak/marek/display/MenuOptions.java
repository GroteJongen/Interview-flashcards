package com.biszczak.marek.display;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor

public enum MenuOptions {
    CHANGE_THEME("Change theme"),
    PLAY("Play"),
    DISPLAY_THEME("Display themes"),
    IMPORT("Import"),
    EXPORT("Export"),
    PRINT_QUESTIONS("Print questions"),
    ADD_FLASHCARD("Add flashcard"),
    EXIT("Exit");

    private final String optionName;
}
