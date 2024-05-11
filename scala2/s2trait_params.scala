//> using scala 2

trait Goodbyes {
  val name: String
  def goodbye = s"Goodbye, $name"
}

trait Greeting {
  val name: String
  def greeting = s"How are you, $name"
}

class Person(val name: String) extends Greeting with Goodbyes {
  println(greeting)
  println(goodbye)
}

object S2TraitParamsDemo extends App {
  new Person("Bob")
}
