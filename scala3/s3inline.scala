//> using scala 3

inline val pi = 3.141592653589793

inline def f1(x: Int)(inline op: => Int) =
  x + op + op

@main
def s3inlineDemo =
  f1(1)(
    {
      println("inlined")
      1
    }
  )
