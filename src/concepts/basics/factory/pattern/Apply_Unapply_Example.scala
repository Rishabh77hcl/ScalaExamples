package concepts.basics.factory.pattern

object Apply_Unapply_Example extends App {
  
  def safeDivide (x:Int,y:Int):Option[Int]={
   if (y==0) None else Some(x/y)
  }
  val r=safeDivide(4,0)
  print(r)

  //Companion Class
  class MRDatasets(var dataset_name:String,var source:String,var adapter:String,var target:String)
  {
    def printDetails = print(s"dataset - $dataset_name has source -$source and stored in $target")
  }
//Companion Object
  object  MRDatasets {
    //Factory method
  //takes object parameters and returns object
  def apply( dataset_name: String, source: String, adapter: String, target: String ): Unit =
      new MRDatasets(dataset_name, source, adapter, target)

  //takes object instance ad returns parameters for that objects
  def unapply( datasets: MRDatasets ): Option[(String, String, String, String)] =
    Some(datasets.dataset_name,datasets.source,datasets.adapter,datasets.target)
  }

  val dataset_obj = new MRDatasets("IMA","dbRiskStore","MDS","Hive")

  val sample_list = List(4,5)
  println(sample_list)

  val lres = sample_list match {case List(x,z)=>x+z}
  println(lres)

  //matching the onjects instance through unapply
  dataset_obj match {case MRDatasets("IMA","dbRiskStore","MDS","Hive") => println("Yes")
  case _ => println("No")}

  def targetIsHive(dataset: MRDatasets):Boolean =dataset match {
    case MRDatasets(_,_,_,"Hive")=> true
    case _=> false
  }

  //checking specficproperty of onjects through unapply
  println(targetIsHive(dataset_obj))

}
