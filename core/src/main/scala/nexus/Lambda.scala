package nexus

/**
 * @author Tongfei Chen
 */
case class Lambda1[X, Y](x: Input[X], y: Expr[Y]) extends Func1[X, Y] {

  def apply(xʹ: Expr[X]) = y.substitute(x, xʹ)

}


case class Lambda2[X1, X2, Y](x1: Input[X1], x2: Input[X2], y: Expr[Y]) extends Func2[X1, X2, Y] {

  def apply(x1ʹ: Expr[X1], x2ʹ: Expr[X2]) = y.substitute(x1, x1ʹ).substitute(x2, x2ʹ)

}

