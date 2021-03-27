package concepts.basics.com


object MainCallingClass extends App {

val data1 = new Data("ABC",123)
  //data1.print
 // println(data1.orderid)
val data2 = new Data("DEF",456)
  data2.print
  println(data2.orderid)
    val a :Int = 2

  val method1_res = data2.method1(2,2)
  println(method1_res)
}
