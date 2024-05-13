//> using scala 2

trait Resettable {
  def reset(): Unit
}

trait Growable[A] {
  def add(a: A): Unit
}

trait Both[A] extends Resettable with Growable[A]

object S2IntersectionTypesDemo extends App {

  def f(x: Resettable with Growable[String]): Unit = {
    x.reset()
    x.add("a")
  }

  f(
    new Resettable with Growable[String] {
      def add(a: String): Unit = ()
      def reset(): Unit = ()
    }
  )

  val both: Seq[Resettable with Growable[String]] = Seq.empty
  val r: Seq[Resettable] = both
  val g: Seq[Growable[String]] = both
  val rg: Seq[Resettable] with Seq[Growable[String]] = both

}
