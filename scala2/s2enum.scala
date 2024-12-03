//> using scala 2

object Planet extends Enumeration {
  protected case class PlanetVal(mass: Double, radius: Double)
      extends super.Val {
    def surfaceGravity: Double = Planet.G * mass / (radius * radius)
    def surfaceWeight(otherMass: Double): Double = otherMass * surfaceGravity
  }
  import scala.language.implicitConversions
  implicit def valueToPlanetVal(x: Value): PlanetVal = x.asInstanceOf[PlanetVal]

  val G: Double = 6.67300e-11
  val Mercury = PlanetVal(3.303e+23, 2.4397e6)
  val Venus = PlanetVal(4.869e+24, 6.0518e6)
  val Earth = PlanetVal(5.976e+24, 6.37814e6)
  val Mars = PlanetVal(6.421e+23, 3.3972e6)
  val Jupiter = PlanetVal(1.9e+27, 7.1492e7)
  val Saturn = PlanetVal(5.688e+26, 6.0268e7)
  val Uranus = PlanetVal(8.686e+25, 2.5559e7)
  val Neptune = PlanetVal(1.024e+26, 2.4746e7)
}

// ADT
object ADT {
  sealed trait Option[+T]
  case class Some[T](x: T) extends Option[T]
  case object None extends Option[Nothing]
}

sealed abstract class Color(color: String) {
  def printColor = println(color)
}
case object Red extends Color("red")
case object Green extends Color("green")
case class Other(s: String) extends Color(s)

object S2EnumDemo extends App {
  println(Planet.values.filter(_.radius > 7.0e6).toSeq)
  val mercury: Planet.Value = Planet.Mercury
}
