package com.accenture

object MasterOfNames {

  def flatten (source: List[AnyRef]): List[AnyRef] = {
    source flatten {
      _ match {
        case null => Nil
        case s: List[AnyRef] => flatten(s)
        case s => List(s)
      }
    }
  }

  def reduceDuplicatesInRow(source: List[String]) = {
     source.foldRight(List[String]()){
       (i, j) => (i, j) match {
         case (q, head :: tail) if q == head => j
         case _ => i :: j
       }
     }
  }
}
