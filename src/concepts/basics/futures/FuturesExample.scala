package concepts.basics.futures

import scala.concurrent.{Await, Future}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._
import scala.util.{Failure, Success}

//performing asynchronous non-blocking operations in parallel
object FuturesExample extends App {


  val stock = List("flour", "sugar", "egg yolks", "syrup", "flavouring","flour")
//  def itemStock(item_to_check: String): Int = {
//    // assume some long running database operation
//    println("checking item stock")
//    val item_list = for {
//      ingredient <- stock
//      if (ingredient == item_to_check)//conditional expersion for yield
//    } yield ingredient
//    //val sweeteningIngredients_check_value = items.map(_.contains("flour"))
//    item_list.length
//  }

  def itemStock(item_to_check: String): Future[Int] = Future {
    // assume some long running database operation
    println("checking item stock")
    val item_list = for {
      ingredient <- stock
      if (ingredient == item_to_check)//conditional expersion for yield
    } yield ingredient
    //val sweeteningIngredients_check_value = items.map(_.contains("flour"))
    item_list.length
  }

//called by blocking main method
  //Awaiting Futures - Sequencial processing
  val stock_value = Await.result(itemStock("flour"),10 seconds) //this will block main thread for 10secs
 //println(stock_value.getClass)


  //called by non blocking future

  itemStock("flour").onComplete {
    case Success(stock) => println(s"Stock for flour = $stock")//in realtime we may call another logic on the success of this future
    case Failure(e) => println(s"Failed to stock, exception = $e")
  }
  //Thread.sleep(3000)




//  def donutStock(donut: String): Future[Int] = Future {
//    // assume some long running database operation
//    println("checking donut stock")
//    10
//  }

  //Chainting future with flatmap

  def buyItem(quantity: Int): Future[Boolean] = Future {
    println(s"buying $quantity donuts")
    true
  }

  //checking one future against other
  val buyingFlour: Future[Boolean] = itemStock("flour").flatMap(qty => buyItem(qty))
  val isSuccess = Await.result(buyingFlour, 5 seconds)

  //println(isSuccess)
//another way for above
 val res = for {
    stock     <- itemStock("flour")
    isSuccess <- buyItem(stock)
  } yield println(s"Buying flour was successful = $isSuccess")

  Thread.sleep(5000)
  //println(res)


}
