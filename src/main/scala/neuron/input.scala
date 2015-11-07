package neuron

/**
 * Created by oleg on 07.11.15.
 */
class input(period: Int) extends neuron(period, 0) {
  require(period > 0, "period must be greater than zero")
  override def tick() = {
    super.input(true)
    super.tick()
  }
}
