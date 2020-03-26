package com.biszczak.marek.persistence;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class RandomQuestsionService {
    private Set<Integer> uniqueIntegers = new HashSet<>();
    private List<String> questions;

    public RandomQuestsionService(List<String> questions) {
        this.questions = questions;
    }

    public String getRandomQuestion() {
        return questions.get(generateUniqueIndex());
    }

    private void checkIfAllIntegersAreUsed() {
        if (questions.size() == uniqueIntegers.size()) {
            uniqueIntegers.clear();

        }
    }

    private int generateUniqueIndex() {
        checkIfAllIntegersAreUsed();
        Random random = new Random();
        int randomIndex = random.nextInt(questions.size());
        while (uniqueIntegers.contains(randomIndex)) {
            randomIndex = random.nextInt(questions.size());

        }
        uniqueIntegers.add(randomIndex);

        return randomIndex;
    }
}
