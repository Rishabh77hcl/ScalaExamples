package concepts.basics.collection

//to avoid null pointer exception caused by many languages NullPointerException!
object OptionsExample extends App{
  val member:Option[String]=Some("data")
  println(member)


  val another_variable: Option[String] = None
 another_variable.getOrElse("passed_value")

  //case matches with the Option initialized value initialization else returns none
  println(another_variable.getClass)
  member match {case Some("data")=> println(member)
  case _ => None}
}
