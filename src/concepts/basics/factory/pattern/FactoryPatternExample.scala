package concepts.basics.factory.pattern

object FactoryPatternExample extends App {
  println("Hi")
  //Its used for providing single instance for creating multiple classes and hides logic of object creation

  class MRDatasets(var dataset_name:String,var source:String,var adapter:String,var target:String) {
    def printDetails = print(s"dataset - $dataset_name has source -$source and stored in $target")
  }

  class FRTB(dataset_name:String,source:String,adapter:String,target:String) extends MRDatasets(dataset_name:String,source:String,adapter:String,target:String)
  class ES(dataset_name:String,source:String,adapter:String,target:String) extends MRDatasets(dataset_name:String,source:String,adapter:String,target:String)

  object MRDatasets{
    def apply(dataset_name: String,source: String,adapter: String,target: String ): MRDatasets =
      dataset_name match {
        case "FRTB" =>  new FRTB(dataset_name,source,adapter,target)
        case "ES" => new ES(dataset_name,source,adapter,target)
        case _ => new MRDatasets(dataset_name,source,adapter,target)
      }
  }

  val frtb = MRDatasets("FRTB","dbRiskStore","MDS","hive")
  val es = MRDatasets("ES","backoffice","MDS","hive")

  frtb.printDetails
  print(frtb.getClass)

  es.printDetails
  print(es.getClass)
}