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

package com.github.rockjam.itgm7.scala.adt

// мы можем в 20 строк реализовать ADT для JSON-а
// причем реализация будет очень прямолинейной
// Берем описание формата с json.org, и идем последовательно

// A value can be:
// * a string in double quotes,
// * or a number,
// * or true or false
// * or null,
// * or an object (unordered set of name/value pairs)
// * or an array (an ordered collection of values)
// These structures can be nested.
sealed trait JSON                             // A value can be:
case class JsString(s: String) extends JSON   // * a string in double quotes,
case class JsNumber(d: Double) extends JSON   // * or a number
case class JsBoolean(b: Boolean) extends JSON // * or true or false
case object JsNull extends JSON               // * or null
case class JsObject(pairs: Map[String, JSON]) // * or an object
  extends JSON
case class JsArray(values: List[JSON])        // * or an array
  extends JSON

object JsObject {
  def apply(pairs: (String, JSON)*): JsObject = JsObject(pairs.toMap)
}

object JSON {

  def write: JSON => String = {
    case JsString(s)     => "\"" + s + "\""
    case JsNumber(n)     => n.toString
    case JsBoolean(b)    => b.toString
    case JsNull          => "null"
    case JsArray(values) => (values map write) mkString (start = "[", sep = ", ", end = "]")
    case JsObject(pairs) =>
      (pairs map {
        case (key, value) =>
          "\"" + key + "\"" + ": " + write(value)
      }) mkString (start = "{", sep = ", ", end = "}")
  }

  val jsPhone = JsObject(
    "phone" -> JsString("79006375977"),
    "title" -> JsString("Mobile")
  )

  val jsEmail = JsObject(
    "email" -> JsString("vash_typhoon@mail.ru"),
    "title" -> JsString("Email")
  )

  val jsPerson = JsObject(
    "name" -> JsString("Nick"),
    "age" -> JsNumber(23.00),
    "is_sleeping" -> JsBoolean(false),
    "contacts" -> JsArray(List(jsPhone, jsEmail))
  )

  val person: String = write(jsPerson)

  // на выходе получаем вполне валидный такой JSON
  /*
  {
      "name": "Nick",
      "age": 23.0,
      "is_sleepng": false,
      "contacts": [
          {
              "phone": "79006375977",
              "title": "Mobile"
          },
          {
              "email": "vash_typhoon@mail.ru",
              "title": "Email"
          }
      ]
  }
   */
}
