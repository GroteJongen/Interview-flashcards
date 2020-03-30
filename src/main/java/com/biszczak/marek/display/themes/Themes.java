package com.biszczak.marek.display.themes;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Themes {
  ASTRIX_DISPLAY_STRATEGY("Astrix display"),
  SLASH_DISPLAY_STRATEGY("Slash display Strategy"),
  TRIANGLE_DISPLAY_STRATEGY("Triangle display strategy");

  private final String themeName;
}
