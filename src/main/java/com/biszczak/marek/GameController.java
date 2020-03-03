package com.biszczak.marek;

public class GameController{
    public DisplayContext displayContext;
    public InputContext inputContext;

    public GameController(DisplayContext displayContext, InputContext inputContext) {
        this.displayContext = displayContext;
        this.inputContext = inputContext;
    }
}

