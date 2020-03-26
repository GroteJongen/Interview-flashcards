package com.biszczak.marek.persistence;

import com.biszczak.marek.Flashcard;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class FileWriterService {
  private static final String EXCEPTION_MSG = "No file detected";


  private File createFile(String resourcePath, String filename){
    File file = new File(resourcePath + filename);
    return file;
  }
  public void writeLine(Flashcard flashcard, String fileName, String resourcePath) {
    try (FileWriter fileWriter = new FileWriter(createFile(fileName,resourcePath), true)) {
      fileWriter.write(flashcard.getQuestion() + " : " + flashcard.getAnswer() + "\n");

    } catch (IOException e) {
      System.out.println(EXCEPTION_MSG);
      //TODO Implement business exceptions
    }
  }

  public void writeLines(List<Flashcard> lines, String fileName, String resourcePath) {
    try (FileWriter fileWriter = new FileWriter(fileName, true)) {

      for(int i = 0; i <lines.size() ; i++) {
        writeLine(new Flashcard(lines.get(i).getQuestion(),  lines.get(i).getAnswer()),resourcePath,fileName);
      }

    } catch (IOException e) {
      System.out.println(EXCEPTION_MSG);
      //TODO Implement business exceptions
    }
  }


}
 /* public void writeLines(List<Flashcard> lines) {
    try (FileWriter fileWriter = new FileWriter(fileName, true)) {

      lines.forEach(this::writeLine);

    } catch (IOException e) {
      System.out.println(EXCEPTION_MSG);
      //TODO Implement business exceptions
    }
  }*/
