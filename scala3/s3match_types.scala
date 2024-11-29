//> using scala 3

type LeafElem[X] = X match
  case String => Char
  case Array[t] => LeafElem[t]
  case Iterable[t] => LeafElem[t]
  case AnyVal => X

def leafElem[X](x: X): LeafElem[X] = x match
  case x: String      => x.charAt(0)
  case x: Array[t]    => leafElem(x(0))
  case x: Iterable[t] => leafElem(x.head)
  case x: AnyVal      => x


@main
def s3MatchTypesDemo =


  val onString:Char = leafElem("first")    // val ret: Char = f

  //Works recursively
  val onArray:Char = leafElem(Array("first"))    // val ret: Char = f
  val onIterable:Char = leafElem(Iterable("first"))    // val ret: Char = f

  //Works also on subtypes
  val onList:Char = leafElem(List("first"))    // val ret: Char = f
  val onOption:Char = leafElem(Vector("first"))    // val ret: Char = f

  //can be used in defs with generics
  def genIterable[CC<:Iterable[?]](t:CC) = leafElem(t)
  val resGen:Char = genIterable(Vector("first")) // val ret: Char = f
