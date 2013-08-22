package patmat
import patmat._

object test {
  List(("a",1),("b",2)).foreach { case (ch, nt) => println(nt + " = " + ch) }
                                                  //> 1 = a
                                                  //| 2 = b
	patmat.Huffman.quickEncode(patmat.Huffman.createCodeTree(patmat.Huffman.decodedSecret))(patmat.Huffman.decodedSecret)
                                                  //> java.lang.StackOverflowError
                                                  //| 	at scala.collection.generic.GenericTraversableTemplate$class.genericBuil
                                                  //| der(GenericTraversableTemplate.scala:69)
                                                  //| 	at scala.collection.AbstractTraversable.genericBuilder(Traversable.scala
                                                  //| :105)
                                                  //| 	at scala.collection.generic.GenTraversableFactory$GenericCanBuildFrom.ap
                                                  //| ply(GenTraversableFactory.scala:58)
                                                  //| 	at scala.collection.generic.GenTraversableFactory$GenericCanBuildFrom.ap
                                                  //| ply(GenTraversableFactory.scala:53)
                                                  //| 	at scala.collection.TraversableLike$class.builder$1(TraversableLike.scal
                                                  //| a:239)
                                                  //| 	at scala.collection.TraversableLike$class.map(TraversableLike.scala:243)
                                                  //| 
                                                  //| 	at scala.collection.AbstractTraversable.map(Traversable.scala:105)
                                                  //| 	at patmat.Huffman$.makeOrderedLeafList(Huffman.scala:108)
                                                  //| 	at patmat.Huffman$.makeOrderedLeafList(Huffman.scala:108)
                                                  //| 	at patmat.Huffman$.makeOrderedLeafList(Huffman.scala:108)
                                                  //| 	at patmat.Huffman$.makeOrderedLeafList(Huffman.scala:108)
                                                  //| 	at patmat.Huffman$.
                                                  //| Output exceeds cutoff limit.
}