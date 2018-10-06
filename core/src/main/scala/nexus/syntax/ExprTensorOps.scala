package nexus.syntax

import nexus._
import nexus.ops._
import nexus.tensor._

/**
 * @author Tongfei Chen
 */
trait ExprRealTensorMixin {

  implicit class ExprRealTensorOps[T[_], R, A](val x: Expr[T[A]])(implicit T: IsRealTensorK[T, R]) {

    def +(y: Expr[T[A]]): Expr[T[A]] = Add(x, y)
    def -(y: Expr[T[A]]): Expr[T[A]] = Sub(x, y)

    def |*|(y: Expr[T[A]]): Expr[T[A]] = Mul(x, y)
    def ⊙(y: Expr[T[A]]): Expr[T[A]] = Mul(x, y)

    def |/|(y: Expr[T[A]]): Expr[T[A]] = Div(x, y)

  }

}
