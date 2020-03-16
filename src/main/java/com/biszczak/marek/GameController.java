package com.biszczak.marek;

import com.biszczak.marek.display.DisplayContext;
import com.biszczak.marek.input.InputContext;

public class GameController{
    public DisplayContext displayContext;
    public InputContext inputContext;

    public GameController(DisplayContext displayContext, InputContext inputContext) {
        this.displayContext = displayContext;
        this.inputContext = inputContext;
    }
}

