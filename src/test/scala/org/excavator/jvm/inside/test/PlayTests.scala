package org.excavator.jvm.inside.test

import java.lang.reflect.{AccessibleObject, Field}
import org.excavator.jvm.inside._

import org.excavator.jvm.inside.AddressExtractor
import org.junit.jupiter.api.Assertions._
import org.junit.jupiter.api.{DisplayName, Test}

class PlayTests {

  @Test
  @DisplayName("test get object address")
  def testAddress() = {
    val addressExtractor: AddressExtractor = new AddressExtractor()
    val clazz:Class[_ <:AddressExtractor] = addressExtractor.getClass
    val field: Field = clazz.getDeclaredField("pointerValue")
    val fieldType: Field = Predef.classOf[Field].getDeclaredField("type")

    AccessibleObject.setAccessible(Array.apply(field, fieldType), true)

    fieldType.set(field, Predef.classOf[Object])

    val dummy: Dummy = new Dummy()
    dummy.value = 0xBADF00D

    field.set(addressExtractor, dummy)

    println(s"object address = ${java.lang.Long.toHexString(addressExtractor.pointerValue)}")
    assertEquals(field.get(addressExtractor), dummy)
  }

}
