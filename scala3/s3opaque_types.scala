//> using scala 3

case class User(name: User.Name, age: User.Age)

object User {
  opaque type Name = String
  opaque type Age = Int

  object Name:
    def apply(name: String): Name = name

  object Age:
    def apply(age: Int): Age =
      if 0 <= age && age < 130 then age
      else throw new RuntimeException("invalid")

  extension (a: Age) def retired: Boolean = a > 65
}

@main
def s3opaqueTypesDemo =
  import User.*

  val user = User(Name("Ala"), Age(25))
  println(user.age.retired)
  //println(user.age > 66) // compilation error

