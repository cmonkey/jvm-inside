import java.lang.reflect.{AccessibleObject, Field}

class PlayApp extends App{

  val addressExtractor = new AddressExtractor()
  val clazz:Class[_ <:AddressExtractor] = addressExtractor.getClass
  val field = clazz.getDeclaredField("pointerValue")
  val fieldType = classOf[Field].getDeclaredField("type")

  AccessibleObject.setAccessible(Array(field, fieldType), true)

  fieldType.set(field, classOf[Object])

  val dummy = new Dummy()
  dummy.value = 0xBADF00D

  field.set(addressExtractor, dummy)

  System.out.println(java.lang.Long.toHexString(addressExtractor.pointerValue))
  System.out.println(field.get(addressExtractor) == dummy)

  System.in.read()

}
