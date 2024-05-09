//> using scala 2

trait Resettable {
  def reset(): Unit
}

trait Growable[A] {
  def add(a: A): Unit
}

trait Both[A] extends Resettable with Growable[A]

object S2CompoundType extends App {

  def f(x: Resettable with Growable[String]): Unit = {
    x.reset()
    x.add("a")
  }

  f(new Resettable with Growable[String] {
    def add(a: String): Unit = ()
    def reset(): Unit = ()
  })

  f(new Growable[String] with Resettable {
    def add(a: String): Unit = ()
    def reset(): Unit = ()
  })

}
