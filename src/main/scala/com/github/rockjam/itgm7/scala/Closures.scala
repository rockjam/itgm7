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

import com.github.rockjam.itgm7.java

object Closures {

  val foo = "first foo"
  val bar = "first bar"
  val baz = "first baz"

  def makePrinter(foo: String, bar: String): () => String = {
    () =>
      {
        val foo = "third foo"
        foo + " " + bar + " " + baz
      }
  }

  val closure = makePrinter("second foo", "second bar")

  closure()
//  > third foo second bar first baz

  UseClosure.cl() == closure() //should be true

  val minValue = 5

  val minFilter: Int => Boolean = i => i > minValue

  Vector(9, 2, 3, 4, 1, 3, 2, 9, 4, 0, 6, 0, 2, 7, 9, 3, 6, 8, 1, 1, 6, 5, 5, 8, 9, 8, 7, 6, 1, 3) filter minFilter


}

object UseClosure {
  val foo = "other foo"
  val bar = "other bar"
  val baz = "other baz"

  val cl = Closures.closure
}
