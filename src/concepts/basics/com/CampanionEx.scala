package concepts.basics.com

object CampanionEx {


  //declare static values
  val COMMAN_VAL1 = ""
  val COMMAN_VAL2= ""
  val COMMAN_VAL3  = ""
  private class ComEx1 extends CampanionExample
  {
    //overid method in main class (in abstarct/trait) or define methods here
  }
  private class ComEx2 extends CampanionExample
  {
    //overid method in main class (in abstarct/trait) or define methods here
  }
  private class ComEx3 extends CampanionExample
  {
    //overid method in main class (in abstarct/trait) or define methods here
  }

  def apply(ComExNo:Int):CampanionExample ={
    ComExNo match {
      case 1 => new ComEx1
      case 2 => new ComEx1
      case 3 => new ComEx1
    }
  }
}
