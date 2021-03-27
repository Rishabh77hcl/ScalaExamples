package concepts.basics.futures

import scala.util.{Failure, Success}

object OperationsInFuture extends App {

  // bunch of future operations to achieve parallelism in futures
  //Future.sequence() function -  these futures will be non-blocking and run in parallel

  import scala.concurrent.ExecutionContext.Implicits.global
  import scala.concurrent.Future
  def donutStock(donut: String): Future[Option[Int]] = Future {
    println("checking donut stock ... sleep for 2 seconds")
    Thread.sleep(2000)
    if(donut == "vanilla donut") Some(10) else None
  }

  def buyDonuts(quantity: Int): Future[Boolean] = Future {
    println(s"buying $quantity donuts ... sleep for 3 seconds")
    Thread.sleep(3000)
    if(quantity > 0) true else false
  }

  def processPayment(): Future[Unit] = Future {
    println("processPayment ... sleep for 1 second")
    Thread.sleep(1000)
  }

  //combining above futures

  val futureOperations: List[Future[Any]] = List(donutStock("vanilla donut"), buyDonuts(10), processPayment())

  //Call Future.sequence to run the future operations in parallel

  //val futureSequenceResults = Future.traverse(futureOperations)
  val futureSequenceResults = Future.sequence(futureOperations)
  //executing all of them in parallel
  //one drawback here is order os execution is not gauranteed , it will be triggered ib random orders
  futureSequenceResults.onComplete {
    case Success(results) => println(s"Results $results")
    case Failure(e)       => println(s"Error processing future operations, error = ${e.getMessage}")
  }

  //Future.traverse()- traverse function, though, has the added benefit of allowing you to apply a function over the future operations

val futureinSeq = List(donutStock("vanilla donut"),donutStock("vanilla donut"),donutStock("vanilla donut"))

  //here , I am extrating the values from multiple future of Options type to scala primitive data type
  val futureTraverseResult = Future.traverse(futureinSeq){ futureSomeQty =>
    futureSomeQty.map(someQty => someQty.getOrElse(0))
  }

  //other functions that could be used are -
  //val futureFirstCompletedResult = Future.firstCompletedOf(futureOperations)

  //aggregating the result of two futures
def donutPrice(): Future[Double] = Future.successful(3.25)
//  val donutStockAndPriceOperation = donutStock("vanilla donut") zip donutPrice()
//  donutStockAndPriceOperation.onComplete {
//    case Success(results) => println(s"Results $results")
//    case Failure(e)       => println(s"Error processing future operations, error = ${e.getMessage}")
//  }

  //if we want to apply any tranformation after zipping futures we can use zipWith , which allowa passing a function as the last for transformation
  val qtyAndPriceF: (Option[Int], Double) => (Int, Double) = (someQty, price) => (someQty.getOrElse(0), price)

  val donutAndPriceOperation = donutStock("vanilla donut").zipWith(donutPrice())(qtyAndPriceF)
  donutAndPriceOperation.onComplete {
    case Success(result) => println(s"Result $result")
    case Failure(e)      => println(s"Error processing future operations, error = ${e.getMessage}")
  }

  futureTraverseResult.onComplete {
    case Success(results) => println(s"Results $results")
    case Failure(e)       => println(s"Error processing future operations, error = ${e.getMessage}")
  }


Thread.sleep(3000)
}
