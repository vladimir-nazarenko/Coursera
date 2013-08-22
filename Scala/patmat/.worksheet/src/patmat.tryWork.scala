package patmat

object tryWork {
	type Occurrences = List[(Char, Int)];import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(128); 

	val lard = List(('a', 1), ('d', 1), ('l', 1), ('r', 1));System.out.println("""lard  : List[(Char, Int)] = """ + $show(lard ));$skip(25); 
  val r = List(('r', 1));System.out.println("""r  : List[(Char, Int)] = """ + $show(r ));$skip(47); 
  val lad = List(('a', 1), ('d', 1), ('l', 1));System.out.println("""lad  : List[(Char, Int)] = """ + $show(lad ));$skip(378); 
  


	def subtract(x: Occurrences, y: Occurrences): Occurrences = {
		def xMap: Map[Char, Int] = x.toMap
		def convert(pair: (Char, Int))(map: Map[Char, Int]): Map[Char, Int] = {
			def ch = pair._1
			def num = pair._2
			if (map(ch) - num == 0)
				map - ch
			else
				xMap.updated(ch, xMap(ch) - num)
		}
		y.foldLeft(xMap){case (map, pair) => convert(pair)(map)} toList
	};System.out.println("""subtract: (x: patmat.tryWork.Occurrences, y: patmat.tryWork.Occurrences)patmat.tryWork.Occurrences""");$skip(23); val res$0 = 
	
	  subtract(lard, r);System.out.println("""res0: patmat.tryWork.Occurrences = """ + $show(res$0))}
}
