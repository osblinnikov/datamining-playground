package neuron

/**
 * Created by oleg on 07.11.15.
 */
class connection(n1: neuron, n2: neuron, isPositive: Boolean) extends ticker {
  def tick() = {
    if(n1.inFire)
      n2.input(isPositive)
  }
  def inFire = n1.inFire
}
