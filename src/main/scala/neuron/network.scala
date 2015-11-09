package neuron

/**
 * Created by oleg on 07.11.15.
 */
class network(in: Int, w: Int, threshold: Int, leak: Float) {
  val in1 = new input(in)
  val w1  = new input(w)
  val n1  = new neuron(threshold,leak)
  val cin = new connection(in1, n1, true)
  val cw  = new connection(w1, n1, false)

  def run(timeout: Int): Float ={
    var t = 0
    var lastT = -1
    var sum = 0
    var cntr = 0
    var lastDiff = -1
    while(t < timeout){
      t = t + 1
      in1.tick()
      w1.tick()
      cin.tick()
      cw.tick()
      n1.tick()

      if(n1.inFire) {
        val diff = t - lastT
        if(lastDiff > 0 && diff != lastDiff){
          println(diff)
          lastDiff = diff
        }
        sum += diff
        lastT = t
        cntr += 1
      }
    }
    sum.toFloat/cntr.toFloat
  }
}
