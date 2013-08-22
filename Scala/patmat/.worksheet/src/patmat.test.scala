package patmat
import patmat._

object test {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(123); 
  List(("a",1),("b",2)).foreach { case (ch, nt) => println(nt + " = " + ch) };$skip(119); val res$0 = 
	patmat.Huffman.quickEncode(patmat.Huffman.createCodeTree(patmat.Huffman.decodedSecret))(patmat.Huffman.decodedSecret);System.out.println("""res0: List[patmat.Huffman.Bit] = """ + $show(res$0))}
}
