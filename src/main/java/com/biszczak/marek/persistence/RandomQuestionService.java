package com.biszczak.marek.persistence;

import com.biszczak.marek.exceptions.EmptyQuestionRepositoryException;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class RandomQuestionService {
  private Set<Integer> uniqueIntegers = new HashSet<>();
  private List<String> questions;

  public RandomQuestionService(List<String> questions) {
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

   int generateUniqueIndex() {
    try {
      checkIfAllIntegersAreUsed();
      Random random = new Random();
      int randomIndex = random.nextInt(questions.size());
      while (uniqueIntegers.contains(randomIndex)) {
        randomIndex = random.nextInt(questions.size());
      }
      uniqueIntegers.add(randomIndex);

      return randomIndex;
    } catch (IllegalArgumentException e) {
      throw new EmptyQuestionRepositoryException("There are no questions on your list");
    }
  }
}
