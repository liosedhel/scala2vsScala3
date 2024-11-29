//> using scala 3

class Super
class Sub extends Super

type IsSub[X] = X match
  case Sub => true //true and false are singleton types
  case _ => false

@main
def s3MatchTypesProblemsDemo =

  summon[IsSub[Sub] =:= true] //compiles
  summon[IsSub[Int] =:= false] //compiles
  summon[IsSub[Array[Float]] =:= false]//compiles

  //summon[IsSub[Super] =:= false]//COMPILE ERROR

  object Opaques {
    opaque type MyString = String
  }
  //summon[IsSub[Opaques.MyString] =:= false]//COMPILE ERROR

  def isSub[X](x: X): IsSub[X] = x match
    case x: Sub      => true
    case _      => false

  val a = isSub(new Sub) //val a: true = true
  val b = isSub(new Super) //val a: IsSub[Super] = false


  val aForced:true = isSub(new Sub) //compiles
  //val bForced:false = isSub(new Super) //COMPILE ERROR


  type IsSubtypeOf[Sub, Super] = Sub match
    case Super => true
    case _ => false
  
  