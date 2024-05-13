//>using scala 3

transparent trait ID
case class UserName(name: String) extends ID
case class Password(hash: String) extends ID
case class Email(id: String) extends ID

@main
def s3UnionTypesDemo =
  def lookupName(n: String) = n
  def lookupPassword(hash: String) = hash.toString

  def help(id: UserName | Password) =
    val user = id match
      case UserName(name) => lookupName(name)
      case Password(hash) => lookupPassword(hash)

  val either = if true then UserName("ala") else Password("passwd")
  help(either)
  // help(Email("ala@ola.com")) //compile time error
