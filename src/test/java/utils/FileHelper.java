package utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import exceptions.TestExecutionException;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class FileHelper {
  static ObjectMapper objectMapper = new ObjectMapper();
  static CsvMapper csvMapper = new CsvMapper();

  public static JsonNode readJsonData(String data) {
    try {
      return objectMapper.readTree(data);
    } catch (IOException e1) {
      try {
        return objectMapper.readTree(new File(data));
      } catch (IOException e2) {
        throw new TestExecutionException(e2.getMessage());
      }
    }
  }

  public static <T> List<T> readCsvFileAsObject(File file, Class<T> classToCast) {
    CsvSchema classSchema = csvMapper.schemaFor(classToCast).withHeader();
    try {
      MappingIterator<T> it = csvMapper
              .readerFor(classToCast)
              .with(classSchema)
              .readValues(file);
      return it.readAll();
    } catch (IOException e) {
      throw new TestExecutionException(e.getMessage());
    }
  }
}
