import stainless.annotation._

object RecursiveOpaque {

  @opaque @inline
  def f(n: BigInt): BigInt = {
    if (n > 0) f(n-1)
    else BigInt(0)
  }.ensuring(_ == 0)

}
