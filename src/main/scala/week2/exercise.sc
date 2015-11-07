import scala.annotation.tailrec

object exercise {
  def factorial(n: Int): Int = {
    @tailrec
    def tailRecurse(acc: Int, n: Int): Int = {
      if (n == 0) acc
      else tailRecurse(acc*n, n - 1)
    }
    tailRecurse(1, n)
  }
  factorial(4)

}
