object exercise3{

  def mapReduce(f: Int=>Int, combine: (Int, Int) => Int, unit: Int)( a: Int, b: Int): Int = {
    if(a>b) unit
    else combine(f(a), mapReduce(f,combine,unit)(a+1,b))
  }

  def product(f: Int=>Int)(a: Int, b: Int): Int = mapReduce(f,(x,y)=>x*y,1)(a,b)

  product(x=>x)(6,4)

  def factorial(n: Int) = product(x=>x)(1,n)

  factorial(5)



}