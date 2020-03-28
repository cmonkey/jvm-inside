import java.lang.reflect.{AccessibleObject, Field}

class PlayApp extends App{

  val addressExtractor: AddressExtractor = new AddressExtractor()
  val clazz:Class[_ <:AddressExtractor] = addressExtractor.getClass
  val field: Field = clazz.getDeclaredField("pointerValue")
  val fieldType: Field = Predef.classOf[Field].getDeclaredField("type")

  AccessibleObject.setAccessible(Array.apply(field, fieldType), true)

  fieldType.set(field, Predef.classOf[Object])

  val dummy: Dummy = new Dummy()
  dummy.value = 0xBADF00D

  field.set(addressExtractor, dummy)

  System.out.println(java.lang.Long.toHexString(addressExtractor.pointerValue))
  System.out.println(field.get(addressExtractor).==(dummy))

  System.in.read()

}
