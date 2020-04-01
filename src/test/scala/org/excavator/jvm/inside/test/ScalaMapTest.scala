package org.excavator.jvm.inside.test

import org.junit.jupiter.api.{DisplayName, Test}
import org.junit.jupiter.api.Assertions._

class ScalaMapTest {

  @Test
  @DisplayName("testScalaMap")
  def testScalaMap(): Unit = {

    val items = Array[Item](Item(10.0), Item(20.0), Item(30.0))
    val sum = items.map(item => item.price).sum

    assertEquals(60.0, sum)

  }

}

case class Item(price: Double)

