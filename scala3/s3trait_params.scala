//> using scala 3

trait Greeting(name: String):
  def greeting = s"How are you, $name"

trait Goodbyes(name: String):
  def goodbye = s"Goodbye, $name"

class Person(name: String) extends Greeting(name), Goodbyes(name):
  println(greeting)
  println(goodbye)

@main
def s3TraitParameters =
  Person("Bob")
