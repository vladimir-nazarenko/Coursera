object tryWork {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(48); 
	def charsIter = List('a', 'c');System.out.println("""charsIter: => List[Char]""");$skip(20); 
	def as: Char = 'a';System.out.println("""as: => Char""");$skip(30); val res$0 = 
	charsIter.exists(as => true);System.out.println("""res0: Boolean = """ + $show(res$0))}
}
