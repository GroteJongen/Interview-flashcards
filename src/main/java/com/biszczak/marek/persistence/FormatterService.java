package com.biszczak.marek.persistence;

import com.biszczak.marek.Flashcard;
import java.util.List;
import java.util.stream.Collectors;

public class FormatterService {


    public List<Flashcard> getFlashcardsToFormat(List<String> linesToFormat) {
        final String semicolon = ":";
        return linesToFormat.stream()
                .map(line -> new Flashcard(line.split(semicolon)[0].trim(), line.split(semicolon)[1].trim()))
                .collect(Collectors.toList());
    }
}
