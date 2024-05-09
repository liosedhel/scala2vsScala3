//> using scala 2

trait ContextWraper[T] {
  def c: T
}

case class Context(c: String) extends ContextWraper[String]
case class ContextInt(c: Int) extends ContextWraper[Int]

object Conversions {
  implicit def fromStringToContext(name: String): Context =
    Context(name)
}

object Params {
  def method(s: String)(implicit c: Context) = {
    m1()
    m2()(c, ContextInt(1))
    m3(s)
  }

  def m1()(implicit c: Context) = 
    println(c.c)

  def m2()(implicit c: Context, i: ContextWraper[Int]) = 
    println(implicitly[Context].c)

  def m3[T: ConxtexWrap](s: T) = 
    println(implicitly[ContextWraper[T]].c)
}

object S2ImplicitsDemo extends App {
  import Conversions._
  implicit val c: Context = "context"

  Params.method("s")
  Params.method("s")(using Context("newContext"))
}
