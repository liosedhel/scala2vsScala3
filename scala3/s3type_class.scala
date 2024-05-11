//> using scala 3

trait Add[A]:
  extension (x: A) def add(y: A): A
  extension (x: A) def +(y: A): A = add(x)(y)

class Rational(val numer: Int, val denom: Int = 1):
  override def toString = s"$numer/$denom"

object Rational:
  given Add[Rational] with
    extension (x: Rational)
      def add(y: Rational): Rational =
        Rational(
          x.numer * y.denom + y.numer * x.denom,
          x.denom * y.denom
        )

@main
def s3TypeClassDemo =
  val rational1 = Rational(1, 2)
  val rational2 = Rational(1, 2)
  val sum = rational1 + rational2
  println(sum)
