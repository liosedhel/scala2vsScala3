//> using scala 2

object S2InlineDemo extends App {
  final val pi = 3.141592653589793
  @inline final def f1(x: Int)(op: => Int): Int = 
    x + op + op

  def i() = 1
  f1(1)(
    {
      println("inlined")
      1
    }
  )
}
