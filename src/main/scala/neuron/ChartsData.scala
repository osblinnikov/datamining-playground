package neuron

import com.github.osblinnikov.charts.XChart
import scala.collection.JavaConverters._
/**
 * Created by oleg on 09.11.15.
 */
class ChartsData {
  def run(): Unit ={
    val wscan_in = 7
    var xw = new scala.collection.mutable.ListBuffer[Double]
    var yw = new scala.collection.mutable.ListBuffer[Double]
    val inscan_w = 50
    var xin = new scala.collection.mutable.ListBuffer[Double]
    var yin = new scala.collection.mutable.ListBuffer[Double]
    val startF = 1/60f
    val endF = 1f
    val leak: Float = 0f
    val threshold: Int = 60
    def wscan() = {
      val in = wscan_in
      var w: Float = startF
      while(w < endF) {
        val net = new network(in, (Math.round(1/w)).toInt, threshold, leak)
        val res: Float = net.run(10000)
        if(res != 0) {
          println((Math.round(1 / w)).toInt + " " + w + " " + 1 / res)
          xw.append((-1.0)*w)
          yw.append(-res)
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
          yin.append(1/res)
        }
        in *= 1.1f
      }
    }
    //  def inScan() = {
    //    var in = startF
    //    var w = inscan_w
    //    while(in < endF ){
    //      val net1 = new network((Math.round(1/in)).toInt, w, threshold, leak)
    //      val res1 : Float = net1.run(10000)
    //      val net2 = new network((Math.round(1/in)).toInt, w/10, threshold, leak)
    //      val res2 : Float = net2.run(10000)
    //      if(res1-res2 != 0 ) {
    //        println((Math.round(1 / in)).toInt + " " + in + " " + (1 / res1 - 1/res2))
    //        xin.append(in)
    //        yin.append(1/res1 - 1/res2)
    //      }
    //      in *= 1.1f
    //    }
    //  }
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
    def doubleToDouble(a: Double): java.lang.Double = {
      java.lang.Double.valueOf(a)
    }
    //  inWScan()
    val wurl = XChart.createChart(
      xw.map(doubleToDouble(_)).asJava,
      yw.map(doubleToDouble(_)).asJava,
      "out(w, in=" + (1.0 / wscan_in) + ",thr="+threshold+",leak="+leak+")")
    val inurl = XChart.createChart(
      xin.map(doubleToDouble(_)).asJava,
      yin.map(doubleToDouble(_)).asJava,
      "out(in, w="+(1.0/inscan_w)+",thr="+threshold+",leak="+leak+")")
  }
}
