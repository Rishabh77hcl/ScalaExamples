package concepts.basics.loops

object loopsWithMap extends App {

  val empMap = Map("firstName" -> "Rishabh", "lastName" -> "Sahu","id" -> "51533591")
  for ((k,v) <- empMap) {
    println(s"key: $k, value: $v")
  }
//with yiels
  val result = for ((k,v) <- empMap) yield {
    (s"key: $k, value: $v")
  }
  //println(result)

  //more shorter ways
  for ((key,value) <- empMap) println(s"Key: $key, Value: $value")
  val res = empMap.foreach {
    case(key, value) => println(s"Key: $key, Value: $value")
  }

  println(empMap.getClass)
  empMap.foreach(x => println(s"key: ${x._1}, value: ${x._2}"))
  empMap.keys.foreach((key) => println(key))
  empMap.keys.foreach(println)
  empMap.values.foreach((value) => println(value))

}

