package org.excavator.jvm.inside.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.excavator.jvm.inside.Item;
import org.junit.jupiter.api.*;

public class ErasureTypeInferenceIssueTest {

    @Test
  @DisplayName("erasure type inference issue")
  public void  testErasureTypeInferenceIssue() {

    List<String> strings = new ArrayList<>();

    List<Item> items = strings.stream().map(item -> new Item(new HashMap<>()))
      .collect(Collectors.toList());
  }
}
