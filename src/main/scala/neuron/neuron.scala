package neuron

class neuron(threshold: Int, leak: Float) {
  require(threshold > 0, "threshold must be greater than zero")

  private var inputs: Int = 0
  var energy: Float = 0
  private def belowZero = energy < 0
  def inFire = energy >= threshold

  def tick() = {
    if (inFire || belowZero)
      energy = 0
    energy += inputs + leak
    inputs = 0
  }

  def input(positive : Boolean) = {
    inputs += {if(positive) 1 else -1}
  }

}
