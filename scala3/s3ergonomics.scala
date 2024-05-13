//> using scala 3

@main
def s3ergonomicsDemo =
  val xs: List[(Int, Int)] = List((1, 1))
  xs.map((x, y) => x + y)
  xs.map(_ + _)

  def combine(x: Int, y: Int) = x + y
  xs.map(combine)

  class A()
  val a = A()

@main
def controlSyntaxDemo =
  var x = 0
  val xs, ys = List.empty[Int]
  def f(x: Int) = x
  def handle = ()
  def body = ()

  if x < 0 then "negative"
  else if x == 0 then "zero"
  else "positive"

  if x < 0 then -x else x

  while x >= 0 do x = f(x)

  for x <- xs if x > 0 yield x * x

  for
    x <- xs
    y <- ys
  do println(x + y)

  try body
  catch case ex: Exception => handle

  x match
    case 1 => println("one")
    case _ => println("other")
