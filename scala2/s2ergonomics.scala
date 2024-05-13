//> using scala 2

object S2ErgonomicsDemo extends App {
  val xs: List[(Int, Int)] = List((1, 1))
  xs.map { case (x, y) => x + y }

  def combine(x: Int, y: Int) = x + y
  xs.map { case (x, y) => combine(x, y) }

  class A()
  val a = new A()
}

object ControlSyntaxDemo extends App {
  var x = 0
  val xs, ys = List.empty[Int]
  def f(x: Int) = x
  def handle = ()
  def body = ()

  if (x < 0) "negative"
  else if (x == 0) "zero"
  else "positive"

  if (x < 0) -x else x

  while (x >= 0) { x = f(x) }

  for { x <- xs if x > 0 } yield x * x

  for {
    x <- xs
    y <- ys
  } println(x + y)

  try body
  catch { case ex: Exception => handle }

  x match {
    case 1 => println("one")
    case _ => println("other")
  }
}
