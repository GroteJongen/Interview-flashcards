package com.biszczak.marek.persistence;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RandomQuestionServiceTest {
  List<String> testQuestions = populateTestList();
  RandomQuestionService randomQuestionService = new RandomQuestionService(testQuestions);
  int listSize = testQuestions.size();

  @Test
  void shouldReturnRandomQuestion() {
    List<String> l1 = new ArrayList<>();
    List<String> l2 = new ArrayList<>();
    List<String> l3 = new ArrayList<>();
    List<String> l4 = new ArrayList<>();
    List<String> l5 = new ArrayList<>();
    List<List<String>> listOfLists = new ArrayList<>();
    listOfLists.add(l1);
    listOfLists.add(l2);
    listOfLists.add(l3);
    listOfLists.add(l4);
    listOfLists.add(l5);
    for (List<String> list :listOfLists) {
      for (int i = 0; i <listSize ; i++) {
        list.add(randomQuestionService.getRandomQuestion());
      }
    }
    boolean areAllEqual = l1.equals(l2) && l1.equals(l3) && l1.equals(l4) && l1.equals(l5);
    assertFalse(areAllEqual);
  }

  @Test
  void checkIfAllIntegersAreUsed() {}

  @Test
  void shouldReturnUniqueIndexes() {
    final List<Integer> controlIndexes = addAllExpectedIndexes();
    List<Integer> indexes = new ArrayList<>();
    int expectedIndexes = listSize;
    for (int i = 0; i < expectedIndexes; i++) {
      indexes.add(randomQuestionService.generateUniqueIndex());
    }
    Collections.sort(indexes);
    System.out.println(indexes);
    System.out.println(controlIndexes);
    assertEquals(indexes, controlIndexes);

  }

  @Test
  void shouldBeBetweenZeroAndListSize() {
    final String tooSmallIndex = "Index is below 0";
    final String tooBigIndex = "Index above list size";
    final int maximalNumber = listSize;
    final int minimalNumber = 0;
    int index = randomQuestionService.generateUniqueIndex();
    assertTrue(index >= minimalNumber, tooSmallIndex);
    assertTrue(index < maximalNumber, tooBigIndex);
  }

  private List<Integer> addAllExpectedIndexes() {
    List<Integer> allIndexes = new ArrayList<>();
    for (int i = 0; i < listSize; i++) {
      allIndexes.add(i);
    }
    return allIndexes;
  }

  private List<String> populateTestList() {
    return Arrays.asList("q1", "q2", "q3", "q4", "q5");
  }
}
