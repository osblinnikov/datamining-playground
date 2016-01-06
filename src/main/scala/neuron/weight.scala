package neuron

/**
 * Created by oleg on 07.11.15.
 */
class weight(period: Int) extends neuron(period, _=>0f) {
  require(period > 0, "period must be greater than zero")
  override def tick() = {
    super.input(true)
    super.tick()
  }
}
