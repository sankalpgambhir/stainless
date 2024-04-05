// To run from within sbt:
// > project stainless-dotty
// > run ../../inductiveExamples/fibonacci.scala --solvers=horn-z3 --vc-cache=false
// solver can be changed to horn-eld for eldarica, or smt-z3 / smt-cvc5 / princess for non-Horn solvers

import stainless.lang.*

def fib(n: BigInt): BigInt = {
    require(n >= 0)
    decreases(n)
    if n <= 1 then
        BigInt(1)
    else
        fib(n - 1) + fib(n - 2)
} ensuring(_ != -1)

