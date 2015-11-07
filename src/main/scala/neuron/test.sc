import neuron.network
object test{
  val startF = 1/60f
  val endF = 1f
  val leak: Float = -0.1f
  val threshold: Int = 30
  def wscan() = {
    val in = 1
    var w: Float = startF
    while(w < endF) {
      val net = new network(in, (1/w).toInt, threshold, leak)
      println((1/w).toInt + " " + net.run(10000))
      w *= 1.5f
    }
  }
//  wscan()

  def inScan() = {
    var in = startF
    var w = 5
    while(in < endF ){
      val net = new network((1/in).toInt, w, threshold, leak)
      println((1/in).toInt + " " + net.run(10000))
      in *= 1.5f
    }
  }
  inScan()
}