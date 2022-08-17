package com.cherednychan.pmtswd_lab1

enum IntOpCode:
  case Add, Mul

enum IntExpr:
  case Lit(n: Int)
  case Op(opCode: IntOpCode, l: IntExpr, r: IntExpr)

import IntOpCode.*
import IntExpr.*

def eval(e: IntExpr): Int =
  e match
    case Lit(n) => n
    case Op(opCode, l, r) =>
      opCode match
        case Add => eval(l) + eval(r)
        case Mul => eval(l) * eval(r)

def map(e: IntExpr, f: Int => Int): IntExpr =
  e match
    case Lit(n) => Lit(f(n))
    case Op(opCode, l, r) => Op(opCode, map(l, f), map(r, f))

def to_string(e: IntExpr): String =
  e match
    case Lit(n) => n.toString
    case Op(opCode, l, r) =>
      opCode match
        case Add => s"(${to_string(l)} + ${to_string(r)})"
        case Mul => s"(${to_string(l)} * ${to_string(r)})"

def stackDepth(e: IntExpr): Int =
  e match
    case Lit(_) => 1
    case Op(_, l, r) => 1 + Math.max(stackDepth(l), stackDepth(r))

@main def run(): Unit =
  println("Hello")
