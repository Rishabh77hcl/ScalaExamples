package concepts.basics.loops

object loopsInScala extends App {

  for(index <- 1 to 5) {println(index)}
  for(index2 <- 1 until 5) {println(index2)}//exluding 5

  //iterating over and finding the value in collection
  val names = List("flour", "sugar", "egg yolks", "syrup", "flavouring")
  val sweet = for(ingredient <- names if ingredient == "sugar"){
    println(s"Found sweetening ingredient = $ingredient")
    ingredient
  }
  println(sweet.getClass)

  //iterating over and returing the value in collection
  val sweeteningIngredients = for {
    ingredient <- names
    if (ingredient == "sugar" || ingredient == "syrup")//conditional expersion for yield
  } yield ingredient

  val sweeteningIngredients_uppercase = for (ingredient <- names) yield ingredient.toUpperCase
  println(sweeteningIngredients_uppercase)

  val sweeteningIngredients_word_length = for (ingredient <- names) yield ingredient.length
  println(sweeteningIngredients_word_length)

  //for yield is sometimes same as map function
  val sweeteningIngredients_hashcode = names.map(_.hashCode)
  println(sweeteningIngredients_hashcode)

  val sweeteningIngredients_check_value = names.map(_.contains("s"))
  println(sweeteningIngredients_check_value)
  //return type of yield will be same as initial collection
  println(sweeteningIngredients.getClass)

  //multiple genertors through yield
  val nums = Seq(1,2,3)
  val letters = Seq('a', 'b', 'c')
  val res = for {
    n <- nums
    c <- letters
  } yield (n, c)

  //println(res)//returns a 2 dimentional array form

  //conditional loop
  for (i <- 1 to 10 if i < 4) println(i)
  for {i <- 1 to 10 if i > 3 if i < 6 if i % 2 == 0} println(i)

  //for loop Counters

  for (i <- 0 until names.length) {
    //println(s"$i is ${names(i)}")
  }

  //provides more control on counters
  for ((names, count) <- names.zipWithIndex) {
    //println(s"$count is $names")
  }

  val zwi2 = names.view.zipWithIndex
  println(zwi2)

  for ((name,count) <- names.view.zip(Stream from 6)) {
    println(s"$count is $name")
  }

  names.zipWithIndex.foreach { d =>
    println(s"${d._2} is ${d._1}")//clearly shows that zip is returns a Tuple
  }


}
