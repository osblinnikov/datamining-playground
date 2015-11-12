package neuron

import com.github.osblinnikov.charts.XChart
import scala.collection.JavaConverters._
/**
 * Created by oleg on 09.11.15.
 */
class ChartsData {
  val threshold: Int = 10

  val wscan_in = 3

  val inscan_w = 10

  val startF = 1/60f
  val endF = 1f

  val constLeak = 0.0f
  val expLeak = -0.01f
  val expMult = 0.3f
  def leak(energy: Float): Float = {
    val exp = Math.exp(expMult * energy)
    if(exp != 1.0) {
      (expLeak * exp).toFloat + constLeak
    } else {
      0
    }
  }

  var xw = new scala.collection.mutable.ListBuffer[Double]
  var yw = new scala.collection.mutable.ListBuffer[Double]

  def wScan(in: Int) = {
    var w: Float = startF

    while(w < endF) {
      val net = new network(in, (Math.round(1/w)).toInt, threshold, leak)
      val res: Float = net.run(10000)
      if(res != 0) {
        println((Math.round(1 / w)).toInt + " " + w + " " + 1 / res)
        xw.append(w)
        yw.append(1/res)
      }
      w *= 1.1f
    }
  }

  var xin = new scala.collection.mutable.ListBuffer[Double]
  var yin = new scala.collection.mutable.ListBuffer[Double]

  def inScan(w: Int) = {
    var in = startF

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

  var xthr = new scala.collection.mutable.ListBuffer[Double]
  var ythr = new scala.collection.mutable.ListBuffer[Double]

  def thrScan(w: Int, in: Int) = {
    var thr = 1

    while(thr < 30 ){
      val net = new network(in, w, thr, leak)
      val res : Float = net.run(10000)
      if(res != 0) {
//        println((Math.round(1 / in)).toInt + " " + in + " " + 1 / res)
        xthr.append(thr.toFloat)
        ythr.append(1.0/res)
      }
      thr += 1
    }


  }

  def doubleToDouble(a: Double): java.lang.Double = {
    java.lang.Double.valueOf(a)
  }

  def run(): Unit= {
//    runW()
//    runIn()
    runThr()
  }

  def runW(): Unit = {
    var start = 0.01f
    val step  = 2f
    while(start < 1f) {
      wScan((1/start).toInt)
      start = start*step
    }
    val wurl = XChart.createChart(
      xw.map(doubleToDouble(_)).asJava,
      yw.map(doubleToDouble(_)).asJava,
      "w","out",
      "out(w, thr="+threshold+",leak="+constLeak+"+"+expLeak+"e^"+expMult+"E)")
  }

  def runThr(): Unit = {
    thrScan(inscan_w, wscan_in)
    val wurl = XChart.createChart(
      xthr.map(doubleToDouble(_)).asJava,
      ythr.map(doubleToDouble(_)).asJava,
      "thr","out",
      "out(w="+1.0/inscan_w+",in="+1.0/wscan_in+",leak="+constLeak+"+"+expLeak+"e^"+expMult+"E)")
  }

  def runIn(): Unit = {
    var wstart = 0.01f
    val wstep  = 2f
    while(wstart < 0.5f) {
      inScan((1/wstart).toInt)
      wstart = wstart*wstep
    }
    val inurl = XChart.createChart(
      xin.map(doubleToDouble(_)).asJava,
      yin.map(doubleToDouble(_)).asJava,
      "in","out",
      "out(in, thr="+threshold+",leak="+constLeak+"+"+expLeak+"e^"+expMult+"E)")

  }
}
