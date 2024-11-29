//> using scala 2

trait LeafElem[X] {
  type Out

  def leafElem(x: X): Out
}

object LeafElem {
  type Aux[X, OutT] = LeafElem[X] {type Out = OutT}

  implicit object Str extends LeafElem[String] {
    type Out = Char

    def leafElem(x: String): Out = x.head
  }

  implicit def array[F[t] <: Array[t], T, Inner](implicit inner: LeafElem.Aux[T, Inner]): LeafElem.Aux[F[T], Inner] = new LeafElem[F[T]] {
    type Out = Inner

    def leafElem(x: F[T]): Out = inner.leafElem(x.head)

  }

  implicit def iterator[F[t] <: Iterable[t], T, Inner](implicit inner: LeafElem.Aux[T, Inner]): LeafElem.Aux[F[T], Inner] = new LeafElem[F[T]] {
    type Out = Inner

    def leafElem(x: F[T]): Out = inner.leafElem(x.head)

  }

  implicit def anyVal[T <: AnyVal]: LeafElem.Aux[T, T] = new LeafElem[T] {
    type Out = T

    def leafElem(x: T): Out = x

  }

  def leafElem[T, Out](x: T)(implicit leafElem: LeafElem.Aux[T, Out]): Out = leafElem.leafElem(x)
}

object S2IntersectionTypesDemo extends App {

  import LeafElem.leafElem

  val onString: Char = leafElem("first") // val ret: Char = f

  //works recursively
  val onArray: Char = leafElem(Array("first")) // val ret: Char = f
  val onIterable: Char = leafElem(Iterable("first")) // val ret: Char = f

  //Works also on subtypes
  val onList: Char = leafElem(List("first")) // val ret: Char = f
  val onOption: Char = leafElem(Vector("first")) // val ret: Char = f

  //can be used in defs with generics
  def genIterable[CC <: Iterable[_]](t: CC)(implicit leafElem: LeafElem[CC]) = leafElem.leafElem(t)

  val resGen: Char = genIterable(Vector("first")) // val ret: Char = f

}
