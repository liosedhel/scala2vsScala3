//>> using scala 2

package object p {
  type Labelled[T] = (String, T)
  val a: Labelled[Int] = ("count", 1)
  def b = a._2
  implicit class COps(x: C) {
    def pair(y: C) = (x, y)
  }
}

case class C()

object S2PackageObjectsDemo extends App {
  import p._
  println(C().pair(C()))
}
