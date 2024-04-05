// To run from within sbt:
// > project stainless-dotty
// > run ../../inductiveExamples/recAdd.scala --solvers=horn-z3 --vc-cache=false
// solver can be changed to horn-eld for eldarica, or smt-z3 / smt-cvc5 / princess for non-Horn solvers

import stainless.lang.*

def add(x: BigInt, y: BigInt): BigInt = {
    require(x >= 0 && y >= 0)
    decreases(x)
    if x <= 0 then y else add(x - 1, y + 1)
} ensuring(_ > -10)