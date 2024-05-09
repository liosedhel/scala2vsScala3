//> using scala 3

trait ContextWrapper[T]:
  def c: T

case class Context(c: String) extends ContextWrapper[String]
case class ContextInt(c: Int) extends ContextWrapper[Int]

object Conversions:
  given fromStringToContext: Conversion[String, Context] =
    (name: String) => Context(name)

object Params:
  def method(s: String)(using c: Context) =
    m1()
    m2()(using c)(using ContextInt(1))
    m3(s)

  def m1()(using c: Context) = 
    println(c.c)

  def m2()(using Context)(using ContextWrapper[Int]) = 
    println(summon[Context].c)

  def m3[T: ContextWrapper](s: T) = 
    println(summon[ContextWrapper[T]].c)

@main
def s3ImplicitsDemo =
  import Conversions.given
  given Context = "context"

  Params.method("s")
  Params.method("s")(using Context("newContext"))
