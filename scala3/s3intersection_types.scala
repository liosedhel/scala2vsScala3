//> using scala 3

trait Resettable:
  def reset(): Unit

trait Growable[A]:
  def add(a: A): Unit

trait Both[A] extends Resettable, Growable[A]

@main
def s3CompoundTypeDemo =

  def f(x: Resettable & Growable[String]): Unit =
    x.reset()
    x.add("first")

  f(
    new Resettable with Growable[String]:
      def reset(): Unit = ()
      def add(a: String): Unit = ()
  )

  val both: Seq[Resettable & Growable[String]] = Seq.empty
  val r: Seq[Resettable] = both
  val g: Seq[Growable[String]] = both
  val rg: Seq[Resettable] & Seq[Growable[String]] = both
