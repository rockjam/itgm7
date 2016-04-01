/*
 * Copyright 2016 Nikolay Tatarinov
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.rockjam.itgm7.scala


object FibMemo {

import scala.collection.mutable

def fib: BigInt => BigInt = {
  val memo = mutable.Map.empty[BigInt, BigInt]

  def aux: BigInt => BigInt = { n =>
    if (n <= 1) n
    else {
      memo.get(n) match {
        case None =>
          val a = n - 1
          val b = n - 2
          val r = aux(a)
          val s = aux(b)
          memo += (a -> r)
          memo += (b -> s)
          r + s
        case Some(v) => v
      }
    }
  }
  aux
}

def time[T](f: => T): (T, Long) = {
  val before = System.currentTimeMillis
  val result = f
  val took = System.currentTimeMillis - before
  (result, took)
}

  println(time(FibMemo.fib(1000))._2)

  println(time(FibMemo.fib(4000))._2)
  println(time(FibMemo.fib(4000)))
}
