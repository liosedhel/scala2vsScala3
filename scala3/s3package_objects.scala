//> using scala 3

// dropped, use just top definitions like
package p
type Labelled[T] = (String, T)
val a: Labelled[Int] = ("count", 1)
def b = a._2

case class C()

extension (x: C) def pair(y: C) = (x, y)

@main
def s3packageObjectsDemo =
  println(C().pair(C()))
