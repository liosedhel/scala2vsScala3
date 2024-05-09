//> using scala 3

trait Resettable:
  def reset(): Unit

trait Growable[A]:
  def add(a: A): Unit

trait Both[A] extends Resettable, Growable[A]

class C:
  def reset(): Unit = ()
  def add(a: String): Unit = ()

def f(x: Resettable & Growable[String]): Unit =
  x.reset()
  x.add("first")

@main
def main =
  f(
    new Resettable with Growable[String]:
      def reset(): Unit = ()
      def add(a: String): Unit = ()
  )
