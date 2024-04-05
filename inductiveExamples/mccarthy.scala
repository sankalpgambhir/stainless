// To run from within sbt:
// > project stainless-dotty
// > run ../../inductiveExamples/mccarthy.scala --solvers=horn-z3 --vc-cache=false --check-measures=no
// automatic measure inference fails here, and is disabled with --check-measures=no
// solver can be changed to horn-eld for eldarica, or smt-z3 / smt-cvc5 / princess for non-Horn solvers

import stainless.lang.*

def m(n: BigInt): BigInt = {
  if (n > 100) n - 10
  else m(m(n + 11))
} ensuring(_ != 50)
