package nexus

import nexus.impl._
import nexus.op._
import nexus.typelevel._

/**
 * @author Tongfei Chen
 */
trait ExprTensorMixin {

  implicit class ExprTensorOps[T[_ <: $$], D, A <: $$](val x: Expr[T[A]])(implicit val env: TypedMathOps[T, D]) {

    def +(y: Expr[T[A]]): Expr[T[A]] = Add(x, y)
    def -(y: Expr[T[A]]): Expr[T[A]] = Sub(x, y)

    def |*|(y: Expr[T[A]]): Expr[T[A]] = EMul(x, y)
    def ⊙(y: Expr[T[A]]): Expr[T[A]] = EMul(x, y)

    def |/|(y: Expr[T[A]]): Expr[T[A]] = EDiv(x, y)

  }

}