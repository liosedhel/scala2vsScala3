//> using scala 2

trait Add[A] {
  def add(x: A, y: A): A
}

object Add {
  implicit class Adding[A: Add](x: A) {
    def +(y: A): A =
      implicitly[Add[A]].add(x, y)
  }
}

class Rational(val numer: Int, val denom: Int = 1) {
  override def toString = s"$numer/$denom"
}

object Rational {
  implicit val addRational: Add[Rational] =
    (x: Rational, y: Rational) => {
      new Rational(
        x.numer * y.denom + y.numer * x.denom,
        x.denom * y.denom
      )
    }
}

object S2TypeClassDemo extends App {
  import Add._
  val rational1 = new Rational(1, 2)
  val rational2 = new Rational(1, 2)
  val sum = rational1 + rational2
  println(sum)
}
