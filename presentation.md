class: center, middle, inverse
# ООП ⇒ ФП
### с увлекательными примерами 👍

---
class: center, middle, inverse
## Введение

---
layout: true
## Введение
### А как у других?

---

## • Много boilerplate
--

## • Специфика языка/runtime
--

## • Социальные аспекты...
--

## • Экосистема!

???
Стоит отметить, что функциональное программирование - это прежде всего стиль программирования, а не программирование на строго определенном языке, который считается функциональным. Программировать в функциональном стиле можно на python, Java, ruby. НО! 

* вероятнее всего придется написать много boilerplate кода/использовать чуждые языковые конструкции
* Приемы фп могут плохо работать в рантайме вашего языка.
* на вас могут странно смотреть люди. 
	- Это не шутка. Больше всего это относится к тому, когда вы работаете в команде. Нередко есть определенные конвенции, которых придерживаются команды, чтобы было проще работать вместе, читать код друг друга. Чаще всего это диктуется самим языком, тем что принято в сообществе, как сложилось.
* Мир вокруг вас будет не функциональным( 
	- Не стоит забывать, что кроме языка самого по себе существует экосистема, которая создавалась годами. Библиотеки на вашем языке не будут написаны в функциональном стиле, и вероятнее всего вам нужно будет как-то приспосабливаться к этому
	
Также сейчас во многих языках программирования добавляют функциональные возможности. И зачастую возникает вопрос, зачем переходить на какой-то другой язык, если лямбды и у нас есть. Для меня решающим наверное является социальный аспект и экосистема. Вряд ли все программируют на этом языке в функциональном стиле, вряд ли все велосипеды уже написаны. И еще один момент - лучше использовать тот язык, в который принципы ФП закладывались изначально, а не появились в результате дополнения спецификации и эволюции.

---
class: center, middle, inverse

![java-versions](https://raw.githubusercontent.com/rockjam/itgm7/master/assets/java-versions.png)

???
Ну и к слову о социальных аспектах и экосистеме.

---
layout: true
## Введение
### Что такое ФП?

---

# • Функции высшего порядке (Higher order functions)

--

# • Неизменяемые структуры данных (Immutable/Persistent data srtuctures)

--

# • Чистые функции (Pure functions)

--

# • Выражения, а не на контрольные структуры

???
Давайте для начала рассмотрим, что такое функциональное программирование, и какими свойствами должен обладать язык, чтобы его можно было назвать функциональным.

В узком понимании ФЯП - это язык который разрешает только использование чистых функций, и не разрешает side effect-ов (про side эффекты мы поговорим позже). Но такой язык был бы не очен практичным, поскольку к side-эффектам 
можно отнести: взаимодействия с ФС, БД, внешними сервисами, рисование прямоугольников на экране.

В более широком смысле от ФЯП ожидаются следующие свойства:
HOF - вы можете обращаться с функциями как значениями. Одна из крутейших вещей!
Persistent DS - данные не модифицируются, а делаются копии значений.
Pure function - чистая ф-ция - это ф-ция без side эффекта. Side-effect - это действие, которое изменяет состояние вне функции. ФС, БД, мутабельная переменная, значение которой изменяется - все это side effect-ы

Наиболее заметная разница между ООП стилем и ФП в том, что программа в ФП  описывает трансформацию данных, в то время как программа в ООП описывает изменения состояния и взаимодействия между объектами. Это различие очень сильно влияет на стиль написания кода, как вы увидите дальше. 

---

class: center, middle, inverse
# Pure functions

---

class: center, middle, inverse
# f: A ⇒ B

???
Функцию f с параметром типа A и значением типа B называют чистой, если для каждого значения типа A существует ровно одно значение типа B, при этом оно зависит только от значения типа А. Внутренние процессы, или состояние не влияют на вычисляемый результат.

---
class: center, middle, inverse

# a + b
# length: String ⇒ Int

???
Ссылочная прозрачность(Referencial transarency)
Так как чистая функция всегда возвращает один и тот же результат, 
то вызов функции с определенными агрументами можно заменить на результат вычисления этой функции.
Так, если программа состоит из чистых функций, то эту операцию можно применить ко всей программе, 
А когда мы это делаем, то это называется моделью замены.
Вообще эта модель хороша для понимания программы. В голове легче представлять.
Длина строки "привет" будет равна 5, а 2 в 5 степени всегда будет равно 32, не важно в каком месте программы это встречается. Не нужно думать о контексте, и о том какое текущее состояние объекта.

В ООП такой подход не сработает, поскольку чаще всего мы в ходе программы используем объекты с изменяемым состоянием, за которыми нужно постоянно следить. Так, зачастую приходится уходить на несколько уровней глубже, чтобы понять как изменится состояние объекта. Это ухудшает читаемость, да и менять такой код гораздо сложнее, это как тянуть нитку из клубка.(Можно сюда фото с клубком ниток)

Далее я предлагаю рассмотреть очень небольшой пример, в котором мы переведем программу из ОО стиля с side effect-ами в ФП стиль с чистыми функциями.

---
layout: true
# Pure functions 

---

```
def add(a: Int, b: Int) = a + b

add(2,3) = 2 + 3 = 5 

"привет".length = 5
```

---

```java
public class CoffeeShop {
    public Coffee buyCoffee(CreditCard cc) {
        Coffee cup = new Coffee();
        cc.charge(cup.getPrice());
        return cup;
    }
}
```

???
Давайте еще раз посмотрим на Java код нашего Coffee шопа.
Как мы будем тестировать такой код? Мы точно не хотим чтобы деньги снимались с реальной карты, поэтому скорее всего нужно будет сделать mock-объект кредитной карты. CreditCard  скорее всего станет интерфесом, и для тестов мы напишем специальную кредитную карту, которая сможет делать ассерты. Довольно странно, правда? 

--
```scala
class CoffeeShop {
  def buyCoffee(cc: CreditCard): (Coffee, Charge) = {
    val coffee = Coffee()
    (coffee, Charge(coffee.price, cc))
  }
}
```

???
В чем различие этих двух кусков кода? Да, они написаны на разных языках, и во втором меньше букв.
Во втором случае мы возвращаем Оплату как значение. 
Это может показаться странным, и может показаться что это совсем не улучшение, потому что теперь мы возвращаем два значения вместо одного. Но в дальнейшем вы увидите плюсы этого изменения.
Зачастую решение на функциональных языках значительно короче чем в ОО, но это не главное. Главное что при этом зачастую улучшается переиспользование кода, его становится легче тестировать, его проще выделять в модули. О таком коде проще рассуждать.

Еще один вопрос. Можно ли переиспользовать buyCoffee для того чтобы купить 5 чашек кофе по одной карте, не делая 5 различных платежей? Нет. Нужно будет повторять логику, написанную в методе buyCoffee.

---

```java
public class Cafe {
    public Coffee buyCoffee(CreditCard cc) {
        Coffee cup = new Coffee();
        cc.charge(cup.getPrice());
        return cup;
    }
    public List<Coffee> buyCoffees(Integer coffeeCount, CreditCard cc) {
        BigDecimal sum = BigDecimal.ZERO;
        final List<Coffee> cups = new ArrayList<>();
        for (int i = 0; i < coffeeCount; i++) {
            Coffee cup = new Coffee();
            cups.add(cup);
            sum = sum.add(cup.getPrice());
        }
        cc.charge(sum);
        return cups;
    }
}
```

???
Например так. Как видите, переиспользовать код не получается. Конечно можно сделать так:

---

```java
public class Cafe {
*    public Coffee buyCoffee(CreditCard cc) {
*        return buyCoffees(1, cc).get(0);
*	}
    public List<Coffee> buyCoffees(Integer coffeeCount, CreditCard cc) {
        BigDecimal sum = BigDecimal.ZERO;
        final List<Coffee> cups = new ArrayList<>();
        for (int i = 0; i < coffeeCount; i++) {
            Coffee cup = new Coffee();
            cups.add(cup);
            sum = sum.add(cup.getPrice());
        }
        cc.charge(sum);
        return cups;
    }
}
```

???
Можно сделать так. Но это выглядит очень неестественно. Покупка одной чашки кофе это частный случай покупки нескольких чашек кофе?
Как бы мы сделали это в scala? А очень просто:

--
```scala
class CoffeeShop {
  def buyCoffee(cc: CreditCard): (Coffee, Charge) = {
    val coffee = Coffee()
    (coffee, Charge(coffee.price, cc))
  }
*  def buyCoffees(coffeeCount: Int, cc: CreditCard): List[(Coffee, Charge)] =
*    List.fill(coffeeCount)(buyCoffee(cc))
}
```

---

```scala
class CoffeeShop {
  def buyCoffee(cc: CreditCard): (Coffee, Charge) = {
    val coffee = Coffee()
    (coffee, Charge(coffee.price, cc))
  }
  def buyCoffees(coffeeCount: Int, cc: CreditCard): List[(Coffee, Charge)] =
    List.fill(coffeeCount)(buyCoffee(cc))
}

val cafe = new CoffeeShop
val myCreditCard = new CreditCard("1234 5678 9123 4567")

val (coffee, charge) = cafe.buyCoffee(myCreditCard)

private val total:List[(Coffee, Charge)] = cafe.buyCoffees(5, myCreditCard)

val coffees: List[Coffee] = total map (_._1)
val totalCharge: Charge = total map (_._2) reduce ((f, s) => Charge.combine(f, s))
```

???
В результате рефакторинга мы убрали явный side effect, теперь мы явно возвращаем платежи по карте.
Но рано или поздно эти платежи нужно будет исполнить, а значит side effect все таки будет. 
Это на самом деле так, но с этим можно мириться, потому что side эффект вынесен за рамки бизнес логики(ядра нашей программы). А поскольку ядро программы лишено сайд эффектов, то очень просто написать тесты на этот код. 

---

### Doodle
.right[![Doodle-image](https://raw.githubusercontent.com/rockjam/itgm7/master/assets/doodle.png)]

```scala
val target = 
  (Circle(20) fillColor Color.red lineColor Color.red) on 
  (Circle(50) fillColor Color.blue lineColor Color.blue) on 
  (Circle(70) fillColor Color.red lineColor Color.red)

val stand = 
  (Rectangle(10, 80) 
    above Rectangle(40, 10)) fillColor Color.brown lineColor Color.brown

val ground = 
  Rectangle(200, 60)  fillColor Color.green  lineColor Color.green

val image = target above stand above ground

image.draw
```

???
Все построения это immutable модель, side effect производится только в последней строке - это непосредственно отрисовка(интерпретация) модели рисунка.
Если заглянуть в метод  draw, то мы увидим. 

---

```scala
def draw(implicit interpreter: Interpreter, canvas: Canvas): Unit =
  interpreter.draw(image, canvas)

trait Interpreter {
  /** Draw an Image centered on the Canvas. */
  def draw(image: Image, canvas: Canvas): Unit = {
    val bb = image.boundingBox
    draw(image, canvas, bb.width.ceil.toInt, bb.height.ceil.toInt, bb.center)
  }
}
```

???
implicit параметры "магически" прокидываются оттуда где вызывается метод.
Мы видим что метод draw просто вызывает метод draw у Interpreter, который выполняет side effect. 
Очень легко понять по сигнатуре функции что это side effecting функция. Возвращается Unit, аналог void. Разница с void в том, что void - это ничего, а Unit это значение. Интерпретатор - это довольно часто используемый паттерн.

```scala
def draw(image: Image, canvas: Canvas): Unit
```

---

```scala
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
```
--
```scala
// очень наивная benchmark функция
def time[T](f: => T): (T, Long) = {
  val before = System.currentTimeMillis
  val result = f
  val took = System.currentTimeMillis - before
  (result, took)
}

println(time(fib(1000))._2)
println(time(fib(1000))._2)
```

???
Еще один пример, когда использование side-effect-ов оправдано - мемоизация. 
Мемоизация - сохранение результатов чистой функции, с целью увеличения производительности.
Чистая функция возвращает одинаковый результат на одинаковые параметры. По сути все равно какой результат вы получили - закешированный, или тот что был только что вычислен. 
В этом случае мутабельность безопасна, поскольку мы ограничиваем только внутри самой функции.
Таким образом говоря о side effect-ах, в ФП главное не то, что side effect-ов нет в принцие, а то, что они изолированы и лучше контролируются.


---
layout:false
class: center, middle, inverse

# Immutable data structures
???
В ФЯП мы не изменяем переменные, и не мутируем структуры данных. 
Например операция конкатенации двух списков возвращает новый список, оставив неизменными два исходных списка.
Но не значит ли это, что у нас в итоге будет множество копий, хранящихся в памяти? На самом деле нет, поскольку копирование происходит эффективно. Копируется минимальная часть, остальное переиспользуется. Мы можем переиспользовать данные, поскольку знаем, что они не изменятся со временем. И это очень большой плюс, который дает предсказуемость программе, и позволяет экономить ресурсы, используя эффективное копирование. 
Такие структуры данных называются в ФЯП неизменяемыми/персистентными/функциональными.

---
layout: true
# Immutable data structures
### Lists
---


.pull-left[
```scala
val xs = List(0, 1, 2)
val ys = List(3, 4, 5)
```
]
.pull-right[
![lists](https://raw.githubusercontent.com/rockjam/itgm7/master/assets/lists.png)
]

???
Возьмем для примера два односвязных списка, и произведем операцию конкатенации. Результат будет выглядеть следующим образом. Видно что список ys не был изменен, и остался прежним. xs же был скопирован в начало списка ys. В результате получился список zs. 

---

.pull-left[
```scala
val zs = xs ++ ys // => List(0, 1, 2, 3, 4, 5)
```
]
.pull-right[
![concat-lists](https://raw.githubusercontent.com/rockjam/itgm7/master/assets/combine-lists.png)
]

---
layout: true
# Immutable data structures
### ADT
---

???
В функциональных языках есть такая вещь, как алгебраические типы данных.
Мы уже видели ADT на примере списка с двумя конструкторами - для пары и пустого списка.
В scala ADT обычно выражается с помощью sealed trait-а и case class/case object, которые его .
Вот описание логического типа через ADT в scala
--

```scala
sealed trait Bool
case object True extends Bool
case object False extends Bool
```

---
layout: true
# Immutable data structures
### JSON
---

A value can be:

--

* a string in double quotes

--

* or a number

--

* or true or false

--

* or null

--

* or an object (unordered set of name/value pairs)

--

* or an array (an ordered collection of values)

These structures can be nested.

???
JSON это всем известный формат обмена, использующийся повсеместно в интернете.
В ФЯП его очень легко реализовать и использовать с помошью ADT.
Реализация ооочень прямолинейная. Берем описание формата с json.org, и идем последовательно

---

```scala
sealed trait JSON                             // A value can be:
case class JsString(s: String) extends JSON   // * a string in double quotes,
case class JsNumber(d: Double) extends JSON   // * or a number
case class JsBoolean(b: Boolean) extends JSON // * or true or false
case object JsNull extends JSON               // * or null
case class JsObject(pairs: Map[String, JSON]) // * or an object
  extends JSON
case class JsArray(values: List[JSON])        // * or an array 
  extends JSON
```

---

```scala
// немного сахара
object JsObject {
  def apply(pairs: (String, JSON)*): JsObject = JsObject(pairs.toMap)
}

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
```

---

```scala
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
```

---

```json
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
```


---
class: center, middle, inverse
layout:false
# Expressions

---
layout: true
# Expressions

---

```java
public interface PersonDAO {
    public Person getByEmail(String email);
}

public class Example {
    private final PersonDAO dao;

    public Example(PersonDAO dao) {
        this.dao = dao;
    }

    public String extractName() {
        Person found = dao.getByEmail("vash_typhoon@mail.ru");
        ???
        return found.getName() + " " + found.getLastName();
    }
}
```

???
Предположим у нас есть интерфейс, который описывает получение пользователя по email-у. И есть объект который инкапсулирует объект реализующий этот интерфейс. В методе example мы хотим получить пользователя. Смотрим на сигнатуру метода, вызываем getByEmail, и ждем пока он взорвется с NullPointerException. Кто-нибудь скажет, что это проблема программиста, который не сделал null check. Но тут не соглашусь. Проблема в том, что по сигнатуре метода, не понятно что вернет метод. Он должен вернуть результат, но может вернуть null, или null object, или кинуть исключение. Проще говоря, чтобы понять как этот метод работает, нужно знать его реализацию. Более того, я скажу что такой код сложнее поддерживать. Например сейчас по факту метод getByEmail всегда возвращает Person. Но это может измениться со временем, при изменении кода. Компилятор не сообщит нам о необходимости проверки на null, и исключение вывалится в runtime, а не во время компиляции.

---


```scala
val found = Some(Person("name", "lastName"))
val found: Option[Person] = None
```

???
Null safety проблема хорошо решается в функциональных языках введением специального Option/Maybe, который имеет 2 состояния: 
  - Some - когда данные есть
  - None - когда данных нет

---


```java
public interface PersonDAO {
    public Person getByEmail(String email);
}

public class Example {
    private final PersonDAO dao;

    public Example(PersonDAO dao) {
        this.dao = dao;
    }

    public String extractName() {
        Person found = dao.getByEmail("vash_typhoon@mail.ru");
        if(found != null) {
            return found.getName() + " " + found.getLastName();
        } else {
            return "No name";
        }
    }
}
```

```scala
trait PersonDAO {
  def getByEmail(email: String): Option[Person]
}

class Example(val dao: PersonDAO) {	
  def extractName(): String = {
    dao.getByEmail("vash_typhoon@mail.ru") map { p => 
      p.name + " " + p.lastName 
    } getOrElse "No name"
  }
}
```

???
Предыдущий пример мы можем переписать с использованием Option. Теперь имея только интерфейс(трейт) мы видим что функция может вернуть, а может не вернуть пользователя. В этом примере мы безопасным способом склеиваем имя и фамилию пользователя, a если пользователь не был найден, то возвращаем "No name". В пример на Java мы добавили проверку на null. Кажется что теперь эти два варианта довольно похожи, но на самом деле есть одна большая разница: в примере на Java мы получаем результат благодаря контрольным структурам, в то время как в примере на scala метод extractName - это одно большое выражение.

---


```scala
object Option {
  def apply[A](x:A): Option[A] = if (x == null) None else Some(x)
  def empty[A]: Option[A] = None
}

sealed trait Option[+A] {
  def isEmpty: Boolean
  def getOrElse[B >: A](default: => B): B
  def map[B](f: A => B): Option[B]
  def flatMap[B](f: A => Option[B]): Option[B]
  def filter(p: A => Boolean): Option[A]
}

final case class Some[A](private val value: A) extends Option[A] {
  def isEmpty: Boolean = false
  def getOrElse[B >: A](default: => B): B = value
  def map[B](f: (A) => B): Option[B] = Option(f(value))
  def flatMap[B](f: (A) => Option[B]): Option[B] = f(value)
  def filter(p: (A) => Boolean): Option[A] = if (p(value)) this else None
}

case object None extends Option[Nothing] {
  def isEmpty: Boolean = true
  def flatMap[B](f: (Nothing) => Option[B]): Option[B] = None
  def filter(p: (Nothing) => Boolean): Option[Nothing] = None
  def getOrElse[B >: Nothing](default: => B): B = default
  def map[B](f: (Nothing) => B): Option[B] = None
}
```

???
Также, тип Option предоставляет множество операций по трансформации и фильтрации данных.
На самом деле реализовать свой Option очень просто, что я и предлагаю сделать. Вся реализация займет не более 30 строк. 

---

```scala
  val firstNumber: Option[Int] = Option(23)
  val secondNumber: Option[Int] = Some(45)

  def isEven(i: Int): Boolean = i % 2 == 0
  def isOdd(i: Int): Boolean = !isEven(i)

  val result =
    firstNumber map { i =>
      i * 3
    } flatMap { i =>
      secondNumber map (j => i + j)
    } filter isOdd
//  > result = Some(114)
//  > result = None

  val result1 = for {
    i <- firstNumber
    j <- secondNumber
  } yield i * 3 + j
```