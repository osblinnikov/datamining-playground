import scala.annotation.tailrec

object exercise2{
  def sum(f: Int => Int, a: Int, b: Int): Int = {
    @tailrec
    def tail(a: Int, acc: Int): Int = {
      if( a > b) acc
      else tail(a + 1, f(a) + acc)
    }
    tail(a, 0)
  }
  sum(x=>x*x , 1,2)
}