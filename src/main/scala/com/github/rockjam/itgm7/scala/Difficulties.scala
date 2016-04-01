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

import akka.actor.ActorSystem
import spray.client.pipelining._
import spray.http._

import scala.concurrent.Future

object Difficulties {

  implicit val system = ActorSystem()
  import system.dispatcher

  val pipeline: HttpRequest => Future[HttpResponse] = sendReceive

  // можно как вариант сделать нашу отправку сообшений.
  // в таком духе
  def sendMessage(): Future[String Either Weather] = ???

  def requestWeatherBad() = {
    val respFu: Future[HttpResponse] = pipeline(Get("http://spray.io/"))

    respFu map { resp =>
      readJson[Weather](resp)

    }
  }

  case class Weather(i: Int, s: String)

  def readJson[T](resp: HttpResponse): Option[T] = ???

  // какие основные типы данных, которые приходится использовать?
  // Future, Option, Xor.

}