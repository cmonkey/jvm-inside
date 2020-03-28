package org.excavator.jvm.inside.test

import java.lang.reflect.{AccessibleObject, Field}
import org.excavator.jvm.inside._

import org.excavator.jvm.inside.AddressExtractor
import org.junit.jupiter.api.Assertions._
import org.junit.jupiter.api.{DisplayName, Test}

class PlayTests {

  @Test
  @DisplayName("test get object address")
  def testGetObjectAddress() = {
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

  @Test
  @DisplayName("test Reading Object Content")
  def testReadingObjectContent() = {
    val dummy: IntDummy = new IntDummy()
    dummy.value = 0xBADF00D
    dummy.value2 = 0xDEADBEEF
    dummy.value3 = 0xFEDCFEDC

    val addressExtractor: AddressExtractor = new AddressExtractor()
    val clazz:Class[_ <:AddressExtractor] = addressExtractor.getClass
    val field: Field = clazz.getDeclaredField("pointerValue")
    val fieldType: Field = Predef.classOf[Field].getDeclaredField("type")

    AccessibleObject.setAccessible(Array.apply(field, fieldType), true)

    fieldType.set(field, Predef.classOf[Object])

    val intsExtractor: IntsExtractor = new IntsExtractor()
    val intsExtractorClazz = intsExtractor.getClass
    val intsExtractorField = intsExtractorClazz.getDeclaredField("ints")
    AccessibleObject.setAccessible(Array.apply(intsExtractorField, fieldType), true)
    fieldType.set(intsExtractorField, Predef.classOf[java.lang.Long])

    field.set(addressExtractor, dummy)

    val trickAddress = addressExtractor.pointerValue - 8
    intsExtractorField.setLong(intsExtractor, trickAddress)

    println(s"intsExtractor length = ${intsExtractor.ints.length}")

    for (i <- 0 until 3){
      println(s"intsExtractor elem = ${intsExtractor.ints(i)}")
    }
  }

}
