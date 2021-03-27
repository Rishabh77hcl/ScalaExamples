package concepts.basics.futures

object FutureWithOptions extends App{
  import scala.concurrent.Future
  import scala.concurrent.ExecutionContext.Implicits.global


  def donutStock(donut: String): Future[Option[Int]] = Future {
    // assume some long running database operation
    println("checking donut stock")
    if(donut == "vanilla donut") Some(10) else None
  }

  def buyDonuts(quantity: Int): Future[Boolean] = Future {
    println(s"buying $quantity donuts")
    if(quantity > 0) true else false
  }

  for {
    someStock  <- donutStock("vanilla donut")
    isSuccess  <- buyDonuts(someStock.getOrElse(0))
  } yield println(s"Buying vanilla donut was successful = $isSuccess and $someStock")

  val resultFromMap: Future[Future[Boolean]] = donutStock("vanilla donut")
    .map(someQty => buyDonuts(someQty.getOrElse(0)))

  Thread.sleep(3000)
  println(resultFromMap.getClass)
  //Thread.sleep(1000)


}
