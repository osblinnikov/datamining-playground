import java.util
import com.github.osblinnikov.charts.Chart
import neuron.network
import scala.collection.JavaConverters._
object test{
  val wscan_in = 7
  var xw = new scala.collection.mutable.ListBuffer[Double]
  var yw = new scala.collection.mutable.ListBuffer[Double]
  val inscan_w = 30
  var xin = new scala.collection.mutable.ListBuffer[Double]
  var yin = new scala.collection.mutable.ListBuffer[Double]
  val startF = 1/60f
  val endF = 1f
  val leak: Float = -0.1f
  val threshold: Int = 30
  def wscan() = {
    val in = wscan_in
    var w: Float = startF
    while(w < endF) {
      val net = new network(in, (Math.round(1/w)).toInt, threshold, leak)
      val res: Float = net.run(10000)
      if(res != 0) {
        println((Math.round(1 / w)).toInt + " " + w + " " + 1 / res)
        xw.append(w)
        yw.append(1 / res)
      }
      w *= 1.1f
    }
  }
  wscan()
  def inScan() = {
    var in = startF
    var w = inscan_w
    while(in < endF ){
      val net = new network((Math.round(1/in)).toInt, w, threshold, leak)
      val res : Float = net.run(10000)
      if(res != 0) {
        println((Math.round(1 / in)).toInt + " " + in + " " + 1 / res)
        xin.append(in)
        yin.append(1 / res)
      }
      in *= 1.1f
    }
  }
  inScan()
  def inWScan() = {
    var in = startF
    var w = startF
    while (in < endF ) {
      w = startF
      while (w < endF) {
        val net = new network((Math.round(1/in)).toInt, (1/w).toInt, threshold, leak)
        val res = net.run(10000);
        w *= 1.1f
      }
      in *= 1.1f
    }
  }
//  inWScan()
    val wurl = Chart.createChartUrl(
      xw.map(java.lang.Double.valueOf(_)).asJava,
      yw.map(java.lang.Double.valueOf(_)).asJava,
      "out(w, in=" + (1.0 / wscan_in) + ")")
  val inurl = Chart.createChartUrl(
    xin.map(java.lang.Double.valueOf(_)).asJava,
    yin.map(java.lang.Double.valueOf(_)).asJava,
    "out(in, w="+(1.0/inscan_w)+")")
}