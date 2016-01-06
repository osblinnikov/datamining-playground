package neuron

import scala.annotation.tailrec

/**
 * Created by oleg on 07.11.15.
 */
class network(in_period: Int, w_period: Int, threshold: Int, leak: Float=> Float) {

  def create_topology(): List[ticker] ={
    def connect(connections_list: List[(Int,Int,Boolean)], neurons : List[neuron]) : List[ticker] ={
      def take(index: Int): neuron = neurons.drop(connections_list.head.productElement(index).asInstanceOf[Int]).head
      connections_list match {
        case Nil => Nil
        case x :: tail => new connection(take(0),take(1),connections_list.head._3) :: connect(tail, neurons)
      }
    }

    val neurons = new input(1) :: new neuron(in_period,leak) :: new neuron(threshold,leak) :: new neuron(10,leak) :: Nil
    val connections = (0,1,true) :: (1,2,true) :: (2,3,true) :: Nil
    (neurons :: connect(connections, neurons)).asInstanceOf[List[ticker]]
  }

  def tick(t : List[ticker]): Unit ={
    print("debug")
    t match {
      case Nil => Nil
      case x :: tail => {
        print(x)
        x.tick()
        tick(tail)
      }
    }

  }

  def run(timeout: Int): Float ={
    val tickers = create_topology()

    var t = 0
    var lastT = -1
    var sum = 0
    var cntr = 0
    var lastDiff = -1
    while(t < timeout){
      t = t + 1
      tick(tickers)

//      if(res1.inFire) {
//        val diff = t - lastT
//        if(lastDiff > 0 && diff != lastDiff){
//          println(diff)
//          lastDiff = diff
//        }
//        sum += diff
//        lastT = t
//        cntr += 1
//      }
    }
    sum.toFloat/cntr.toFloat
  }
}
