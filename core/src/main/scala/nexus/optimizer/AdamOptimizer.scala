package nexus.optimizer

import nexus._
import nexus.exec._
import nexus.op._

/**
 * The Adaptive Moment Estimation ('''Adam''') optimizer.
 *
 * Reference:
 * D P Kingma, J Ba (2015): Adam: A Method for Stochastic Optimization. ICLR.
 * [[https://arxiv.org/pdf/1412.6980.pdf]]
 * @author Tongfei Chen
 * @since 0.1.0
 */
class AdamOptimizer(α: => Double = 0.001, β1: Double = 0.9, β2: Double = 0.999, ε: Double = 1e-8) extends FirstOrderOptimizer {

  case class AdamHistory[X](var m: X, var v: X)

  val history = new ExprMap[AdamHistory]()


  def updateParam[X](p: Param[X], g: X) = {

    implicit val ops = p.gradOps
    import ops._

    if (history contains p) {

      val h = history(p)
      h.m = (h.m :* β1) + (g :* (1 - β1))
      h.v = (h.v :* β2) + ((g |*| g) :* (1 - β2))
      val m̂ = h.m :/ (1 - Math.pow(β1, t))
      val v̂ = h.v :/ (1 - Math.pow(β2, t))
      p -= (m̂ |/| sqrt(v̂) +# ε) :* α

    }
    else history(p) = AdamHistory(zeroBy(g), zeroBy(g))
  }

}