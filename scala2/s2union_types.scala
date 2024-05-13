//>using scala 2

trait ID
case class UserName(name: String) extends ID
case class Password(hash: String) extends ID
case class Email(id: String) extends ID

object S2UnionTypesDemo extends App {

  def lookupName(n: String) = n
  def lookupPassword(hash: String) = hash.toString

  def help(id: ID) = {
    val user = id match {
      case UserName(name) => lookupName(name)
      case Password(hash) => lookupPassword(hash)
    }
  }

  val either = if (true) UserName("ala") else Password("passwd")
  help(either)
  help(Email("ala@ola.com")) // runtime exception

}
