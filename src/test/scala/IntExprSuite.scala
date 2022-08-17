package com.cherednychan.pmtswd_lab1

import munit.FunSuite
import IntOpCode.*
import IntExpr.*

class IntExprSuite extends FunSuite {
  test("eval 1") {
    val expected = 0
    val actual = eval(Lit(0))
    assertEquals(actual, expected)
  }
  test("eval 2") {
    val expected = 5
    val actual = eval(Op(Add, Lit(2), Lit(3)))
    assertEquals(actual, expected)
  }
  test("eval 3") {
    val expected = 6
    val actual = eval(Op(Mul, Lit(2), Lit(3)))
    assertEquals(actual, expected)
  }
  test("eval 4") {
    val expected = 7
    val actual = eval(Op(Add, Lit(1), Op(Mul, Lit(2), Lit(3))))
    assertEquals(actual, expected)
  }
  test("eval 5") {
    val expected = 7
    val actual = eval(Op(Add, Op(Mul, Lit(2), Lit(3)), Lit(1)))
    assertEquals(actual, expected)
  }
  test("eval 6") {
    val expected = 14
    val actual = eval(Op(Mul, Lit(2), Op(Add, Lit(3), Lit(4))))
    assertEquals(actual, expected)
  }
  test("eval 7") {
    val expected = 14
    val actual = eval(Op(Mul, Op(Add, Lit(3), Lit(4)), Lit(2)))
    assertEquals(actual, expected)
  }
  test("map 1") {
    val expected = Lit(1)
    val actual = map(Lit(0), x => x + 1)
    assertEquals(actual, expected)
  }
  test("map 2") {
    val expected = Op(Add, Lit(3), Lit(4))
    val actual = map(Op(Add, Lit(2), Lit(3)), x => x + 1)
    assertEquals(actual, expected)
  }
  test("map 3") {
    val expected = Op(Mul, Lit(3), Lit(4))
    val actual = map(Op(Mul, Lit(2), Lit(3)), x => x + 1)
    assertEquals(actual, expected)
  }
  test("map 4") {
    val expected = Op(Add, Lit(2), Op(Mul, Lit(3), Lit(4)))
    val actual = map(Op(Add, Lit(1), Op(Mul, Lit(2), Lit(3))), x => x + 1)
    assertEquals(actual, expected)
  }
  test("map 5") {
    val expected = Op(Add, Op(Mul, Lit(3), Lit(4)), Lit(2))
    val actual = map(Op(Add, Op(Mul, Lit(2), Lit(3)), Lit(1)), x => x + 1)
    assertEquals(actual, expected)
  }
  test("map 6") {
    val expected = Op(Mul, Lit(3), Op(Add, Lit(4), Lit(5)))
    val actual = map(Op(Mul, Lit(2), Op(Add, Lit(3), Lit(4))), x => x + 1)
    assertEquals(actual, expected)
  }
  test("map 7") {
    val expected = Op(Mul, Op(Add, Lit(4), Lit(5)), Lit(3))
    val actual = map(Op(Mul, Op(Add, Lit(3), Lit(4)), Lit(2)), x => x + 1)
    assertEquals(actual, expected)
  }
  test("to_string 1") {
    val expected = "0"
    val actual = to_string(Lit(0))
    assertEquals(actual, expected)
  }
  test("to_string 2") {
    val expected = "(2 + 3)"
    val actual = to_string(Op(Add, Lit(2), Lit(3)))
    assertEquals(actual, expected)
  }
  test("to_string 3") {
    val expected = "(2 * 3)"
    val actual = to_string(Op(Mul, Lit(2), Lit(3)))
    assertEquals(actual, expected)
  }
  test("to_string 4") {
    val expected = "(1 + (2 * 3))"
    val actual = to_string(Op(Add, Lit(1), Op(Mul, Lit(2), Lit(3))))
    assertEquals(actual, expected)
  }
  test("to_string 5") {
    val expected = "((2 * 3) + 1)"
    val actual = to_string(Op(Add, Op(Mul, Lit(2), Lit(3)), Lit(1)))
    assertEquals(actual, expected)
  }
  test("to_string 6") {
    val expected = "(2 * (3 + 4))"
    val actual = to_string(Op(Mul, Lit(2), Op(Add, Lit(3), Lit(4))))
    assertEquals(actual, expected)
  }
  test("to_string 7") {
    val expected = "((3 + 4) * 2)"
    val actual = to_string(Op(Mul, Op(Add, Lit(3), Lit(4)), Lit(2)))
    assertEquals(actual, expected)
  }
  test("stackDepth 1") {
    val expected = 1
    val actual = stackDepth(Lit(0))
    assertEquals(actual, expected)
  }
  test("stackDepth 2") {
    val expected = 2
    val actual = stackDepth(Op(Add, Lit(2), Lit(3)))
    assertEquals(actual, expected)
  }
  test("stackDepth 3") {
    val expected = 2
    val actual = stackDepth(Op(Mul, Lit(2), Lit(3)))
    assertEquals(actual, expected)
  }
  test("stackDepth 4") {
    val expected = 3
    val actual = stackDepth(Op(Add, Lit(1), Op(Mul, Lit(2), Lit(3))))
    assertEquals(actual, expected)
  }
  test("stackDepth 5") {
    val expected = 3
    val actual = stackDepth(Op(Add, Op(Mul, Lit(2), Lit(3)), Lit(1)))
    assertEquals(actual, expected)
  }
  test("stackDepth 6") {
    val expected = 3
    val actual = stackDepth(Op(Mul, Lit(2), Op(Add, Lit(3), Lit(4))))
    assertEquals(actual, expected)
  }
  test("stackDepth 7") {
    val expected = 3
    val actual = stackDepth(Op(Mul, Op(Add, Lit(3), Lit(4)), Lit(2)))
    assertEquals(actual, expected)
  }
  test("stackDepth 8") {
    val expected = 5
    val actual = stackDepth(
      Op(Add,
        Op(Add,
          Op(Add,
            Op(Add,
              Lit(1),
              Lit(2)),
            Lit(3)),
          Lit(4)),
        Lit(5)
      )
    )
    assertEquals(actual, expected)
  }
}
