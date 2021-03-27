package concepts.basics.futures

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success}

object FuturesCalculator extends App {

  def add (num:Int):Long=1+num

  val f2 = Future {
    for (i <- 1 to 20) yield add(i)
   // throw new RuntimeException ("Created an own exception")
  }


//  Thread.sleep(10000)
//  println(f2)


  f2.onComplete {
    case Success(v) => println(v)
    case Failure(e) => println(s"Failed to get values, exception = $e")
  }

  //here we are passing passing extimated time for the completion above method/Future, but in realtime we cant estimate
  //hence we need some something which can monitor the status and than give results
  Thread.sleep(10000)

  val url_data1 = Future {
    io.Source.fromURL("https://www.google.com/").take(100).mkString
  }

  val url_data2 = Future {
    io.Source.fromURL("https://alvinalexander.com/").take(100).mkString
  }

  Thread.sleep(10000)

  val future_list = List(url_data1,url_data2)
  val future_completed_first = Future.firstCompletedOf(future_list)
  val all_url_data = Future.sequence(future_list)
  all_url_data.foreach(println)
  println(future_completed_first)


  //Using methods of Future API for various future object created
  //f2.foreach(println)

}
