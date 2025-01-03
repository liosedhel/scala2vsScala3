//> using scala 3

enum Planet(mass: Double, val radius: Double) extends java.lang.Enum[Planet]:
  val G: Double = 6.67300e-11
  def surfaceGravity: Double = G * mass / (radius * radius)
  def surfaceWeight(otherMass: Double): Double = otherMass * surfaceGravity

  case Mercury extends Planet(3.303e+23, 2.4397e6)
  case Venus extends Planet(4.869e+24, 6.0518e6)
  case Earth extends Planet(5.976e+24, 6.37814e6)
  case Mars extends Planet(6.421e+23, 3.3972e6)
  case Jupiter extends Planet(1.9e+27, 7.1492e7)
  case Saturn extends Planet(5.688e+26, 6.0268e7)
  case Uranus extends Planet(8.686e+25, 2.5559e7)
  case Neptune extends Planet(1.024e+26, 2.4746e7)

object ADT:
  enum Option[+T]:
    case Some(x: T)
    case None

enum Color(color: String):
  def printColor = println(color)

  case Red extends Color("red")
  case Green extends Color("green")
  case Other(s: String) extends Color(s)

@main
def s3EnumDemo =
  println(Planet.values.filter(_.radius > 7.0e6).toSeq)
  val mercury: Planet = Planet.Mercury
