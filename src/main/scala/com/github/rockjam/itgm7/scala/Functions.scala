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

object Functions {

  val inc: Int => Int = i => i + 1

  //  val inc = { i: Int => i + 1 }

  def incM(i: Int): Int = i + 1

  val inc1: Int => Int = incM

  inc(1)
  incM(1)
  inc1(1)

  val plusTwo: Int => Int = inc andThen inc

  def plusSix(i: Int): Int = inc(inc(inc(inc(inc(inc(i))))))

  // имея функцию в ФЯП мы можем обращаться с ней как с значением,
  // мы можем возвращать функции как значения
  def repeat[A](f: A => A, repeatTimes: Int): A => A = {
    def aux(f: A => A, acc: A => A, times: Int) =
      if (times == 1) f else repeat(f andThen f, times - 1)
    aux(f, f, repeatTimes)
  }

  val l = List(
    Person("Homer", "Simpson"),
    Person("Marge", "Simpson"),
    Person("Bart", "Simpson"),
    Person("Mr.", "Burns")
  )

  l.sorted(Ordering.by({ p: java.Person => p.name }))
  l.sortBy(_.name)
  l.sortWith((p1, p2) => p1.name < p2.name)

  val registered = List(
    RegisteredPerson("Homer", "Simpson", "homer@gmail.com", "fatguy"),
    RegisteredPerson("Marge", "Simpson", "marge@gmail.com", "marge"),
    RegisteredPerson("Mr.", "Burns", "burns@gmail.com", "scrudge")
  )

  // настолько кратко, что даже можно не выносить в метод, если это используется один раз.
  val withoutSecrets = registered map { p => Person(p.name, p.lastName) }

//  trait Action[A] {
//    /**
//     * Invokes this action.
//     *
//     * @param request the incoming HTTP request
//     * @return the result to be sent to the client
//     */
//    def apply: Request[A] => Future[Result]
//  }
//
//
//  Action { request =>
//    val resp = request.headers mkString ","
//    Future(Result(OK, resp))
//  }

}
