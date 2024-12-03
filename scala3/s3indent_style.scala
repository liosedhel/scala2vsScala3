//> using scala 3

package indent.style

import scala.util.*

trait PrintColor(color: String):
  def printColor = println(color)

enum Color(val color: String) extends PrintColor(color):
  case Red extends Color("red")
  case Green extends Color("green")
  case Other(s: String) extends Color(s)

trait RedPaint(size: Int):
  def red(): List[Color] = List.fill(size)(Color.Red)

trait GreenPaint(size: Int):
  def green(): List[Color] = List.fill(size)(Color.Green)

class PaintBucket(size: Int) extends RedPaint(size), GreenPaint(size):

  def combine(): List[Color] =
    val combined = Random.shuffle(red() ::: green())
    // fewer braces SIP-44
    combined.foreach:
      case Color.Green => println("green")
      case other       => other.printColor
    combined
  end combine

end PaintBucket
