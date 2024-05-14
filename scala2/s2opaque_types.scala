//> using scala 2

//Scala 2 does not have opaque types
//this below is more or less standard approach to type aliasing

case class User(name: User.Name, age: User.Age)
object User {
  type Name = String

  // age has to be public to be AnyVal
  class Age(val age: Int) extends AnyVal {
    def retired = age > 65
  }
  object Age {
    def apply(age: Int): Age = {
      if (0 <= age && age < 130)
        new Age(age)
      else throw new RuntimeException("invalid")
    }
  }
}

object S2OpaqueTypesDemo extends App {
  import User._

  // you can just pass over any String directly
  val user = User("Ala", Age(25))
  println(user.age.retired)
  // it compiles, exposing internals
  println(user.age.age > 66)

}
