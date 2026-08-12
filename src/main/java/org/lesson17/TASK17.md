# Задание 17 - Тайный покупатель

Вы устроились работать **тайным покупателем-тестировщиком** в интернет-магазин **ServeRest** — небольшой онлайн-маркет, где продают всё подряд: от смартфонов до наборов юного химика. Магазин недавно переехал на новую платформу, и владелец переживает — вдруг что-то сломалось?

Ваша задача — прикинуться обычным пользователем, зарегистрироваться, походить по каталогу, добавить товары и проверить, что каждая кнопка на сайте (а точнее, каждый API-эндпоинт) работает как надо. Владелец обещал премию, если найдёте хотя бы одну ошибку 🕵️‍♂️

Работать будем с публичным тренировочным API:  **[https://serverest.dev/](https://serverest.dev/)** — бразильский API-тренажёр специально для обучения тестированию (ничего более подходящего не нашли, так что Boa noite, senhoras e senhores).

📚 Документация эндпоинтов: **[https://serverest.dev/#/](https://serverest.dev/#/)**

* * *

## 🎯 Что нужно сделать

Создайте новый Gradle-проект, добавьте зависимость для RestAssured ([https://mvnrepository.com/artifact/io.rest-assured/rest-assured/3.0.0](https://mvnrepository.com/artifact/io.rest-assured/rest-assured/3.0.0)) в случае ошибок совместимости с Java попробовать другую версию библиотеки.   И добавьте класс `ServeRestTest по пути src/test/ваш_пакет_с_классом)` . Каждое задание — отдельный `@Test` метод внутри одного класса.

* * *

## 📋 Задания

### 🔧 Задание 1. Открываем магазин — настройка (5 мин)

Настройте `baseURI` глобально для всех тестов через `@BeforeAll`:

* `baseURI` = `[https://serverest.dev](https://serverest.dev)`

**Подсказка:**

```java
@BeforeAll
static void setup() {
    RestAssured.baseURI = "https://serverest.dev";
    RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
}
```

Теперь в тестах достаточно писать `.get("/usuarios")` вместо полного URL. Заодно включили умное логирование — вам оно пригодится в задании 7.

> 💡 ServeRest — бразильский API, поэтому эндпоинты и поля на португальском. Не пугайтесь: `usuarios` = пользователи, `produtos` = товары, `nome` = имя, `preco` = цена.

* * *

### 👥 Задание 2. «Кто здесь уже покупал?» — простой GET (8 мин)

Напишите тест `shouldGetAllUsers()`:

1. Отправьте `GET /usuarios` — получить список всех пользователей
2. Проверьте:
* status code = **200**
* Content-Type содержит `application/json`
* в JSON поле `quantidade` (количество) больше 0
* массив `usuarios` не пустой



**Что вы отработаете:** базовый GET, проверка status code, headers, простые Hamcrest matchers.

* * *

### 🔍 Задание 3. «Досье на клиента» — GET с query-параметром (10 мин)

Напишите тест `shouldFindUserByEmail()`:

1. Сначала получите список всех пользователей (`GET /usuarios`) и через `.extract()` вытащите email первого пользователя в переменную:
2. Теперь отправьте второй запрос `GET /usuarios` с query-параметром `email = <тот самый email>`
3. Проверьте:
* status code = 200
* `quantidade` = 1 (нашёлся ровно один пользователь)
* у первого элемента массива `usuarios[0].email` равен искомому

**Что вы отработаете:** query-параметры, `extract()`, простая цепочка из двух запросов, обращение к вложенным полям и элементам массива через JsonPath.

* * *

### 📝 Задание 4. «Открываем новый аккаунт» — POST (10 мин)

Напишите тест `shouldCreateNewUser()`:

1. Отправьте `POST /usuarios` с телом запроса — придумайте нового пользователя:

```json
{
  "nome": "Тайный Покупатель",
  "email": "spy_НОМЕР@qa.com",
  "password": "secret123",
  "administrador": "true"
}
```

⚠️ Email должен быть **уникальным** — если уже занят, API вернёт ошибку 400. Подставьте в `НОМЕР` любое число (например, текущую минуту) или используйте `System.currentTimeMillis()`.

1. Не забудьте `Content-Type: application/json`
2. Тело запроса передайте **обычной Java-строкой** (без DTO!) — либо через text block `"""..."""`, либо конкатенацией
3. Проверьте:
* status code = **201**
* в ответе поле `message` = `"Cadastro realizado com sucesso"` (регистрация выполнена)
* в ответе есть непустое поле `_id` (это id нового пользователя)
4. **Извлеките** `_id` через `extract()` и сохраните — он пригодится в заданиях 5 и 6!

**Подсказка для сохранения id между тестами:**

```java
private static String userId; // поле класса

// внутри теста:
userId = response.extract().path("_id");
```

**Что вы отработаете:** POST, Content-Type, формирование тела, `extract()`, сохранение состояния между тестами.

* * *

### ✏️ Задание 5. «Смена данных клиента» — PUT (8 мин)

Напишите тест `shouldUpdateUser()`:

1. Отправьте `PUT /usuarios/{id}`, используя `userId` из предыдущего теста через `pathParam`
2. Тело: те же поля, но `nome` поменяйте на `"Обновлённый Покупатель"`, а `administrador` — на `"false"`
3. Проверьте:
* status code = **200**
* `message` = `"Registro alterado com sucesso"` (запись успешно изменена)

**Что вы отработаете:** PUT, path-параметры, повторное использование данных из предыдущего теста.

* * *

### 🔐 Задание 6. «Ключ от служебного входа» — авторизация + DELETE (10 мин)

Напишите два теста:

**6.1** `shouldLogin()` — получаем токен:

1. Отправьте `POST /login` с телом:
  ```json
  {
    "email": "<email из задания 4>",
    "password": "secret123"
  }
  ```
2. Проверьте:
* status code = 200
* `message` = `"Login realizado com sucesso"`
* есть непустое поле `authorization`
3. Извлеките значение `authorization` в статическое поле класса — это ваш токен

**6.2** `shouldDeleteUser()` — удаляем пользователя:

1. Отправьте `DELETE /usuarios/{id}`, используя `userId` из задания 4
2. Добавьте заголовок `Authorization` со значением сохранённого токена:
  ```java
  .header("Authorization", token)
  ```
3. Проверьте:
* status code = 200
* `message` = `"Registro excluído com sucesso"` (запись удалена)
4. **Сразу после** удаления отправьте `GET /usuarios/{id}` и убедитесь, что status code = **400** (ServeRest возвращает именно 400, а не 404, с сообщением `"Usuário não encontrado"`)

**Что вы отработаете:** передача токена в заголовке, DELETE, цепочка «удалить → проверить, что удалилось».

* * *

### 🎁 Задание 7. «Каталог товаров» — GET + Hamcrest (10 мин)

Напишите тест `shouldGetAllProducts()`:

1. Отправьте `GET /produtos`
2. Проверьте:
* status code = 200
* `quantidade` больше 0
* **у всех** товаров цена (`preco`) больше 0 — используйте `everyItem`
* **у всех** товаров есть непустое поле `nome`
* в массиве есть хотя бы один товар с определённым именем (посмотрите первый ответ и подставьте реальное имя)



**Что вы отработаете:** обращение к массивам через JsonPath, комбинирование матчеров, `everyItem` и `hasItem`.

* * *

## ⭐ Задание со звёздочкой (бонус). «Первый DTO» (10 мин)

Теперь — маленький шаг к «взрослым» тестам. Мы создадим **один простой DTO-класс** и переделаем задание 4 на его использование.

### Шаг 1. Создайте класс `Usuario`

В пакете `com.lesson.homework.models` создайте класс: (если вспомните пример с аннотациями Lombok из урока, класс получится проще)

```java
public class Usuario {
    private String nome;
    private String email;
    private String password;
    private String administrador;

    // Пустой конструктор — нужен Jackson'у для десериализации
    public Usuario() {}

    // Конструктор для удобного создания в тесте
    public Usuario(String nome, String email, String password, String administrador) {
        this.nome          = nome;
        this.email         = email;
        this.password      = password;
        this.administrador = administrador;
    }

    // Геттеры и сеттеры для всех полей — их использует Jackson.
    // (Можно сгенерировать в IDE: правый клик → Generate → Getter and Setter)
    public String getNome()          { return nome; }
    public void   setNome(String v)  { this.nome = v; }

    public String getEmail()         { return email; }
    public void   setEmail(String v) { this.email = v; }

    public String getPassword()      { return password; }
    public void   setPassword(String v) { this.password = v; }

    public String getAdministrador()      { return administrador; }
    public void   setAdministrador(String v) { this.administrador = v; }
}
```

### Шаг 2. Напишите тест `shouldCreateUserFromDto()`

Повторите задание 4, но вместо строки JSON передайте объект `Usuario`:

```java
@Test
@DisplayName("★ Создание пользователя через DTO (сериализация)")
void shouldCreateUserFromDto() {
.......
}
```

### 🤔 Что произошло?

RestAssured сам «под капотом» вызвал Jackson и превратил объект `Usuario` в JSON-строку. Это называется **сериализация**. Сравните с заданием 4:

| Задание 4 (со строкой) | ⭐ Задание (с DTO) | Задание 4 (со строкой) | ⭐ Задание (с DTO) |
| :-- | :-- | :-- | :-- |
| Экранирование кавычек \" | Обычный Java-код |  |  |
| Опечатка в имени поля — узнаете в рантайме | Опечатка в поле — красное подчёркивание в IDE |  |  |
| Нельзя переиспользовать | Один класс = много тестов |  |  |

**Что вы отработаете:** ваш первый настоящий DTO и понимание, зачем они нужны.

* * *

* * *



🔐 Как работает авторизация в ServeRest

### Общая схема

В ServeRest **нет** статичных «api\_key» или логина/пароля в каждом запросе. Вместо этого используется **JWT-токен**, который живёт **10 минут**. Схема стандартная для современных API:

```
    1. POST /login (email + password)
                    ↓
    2. Сервер отвечает: { "authorization": "Bearer eyJhbGc..." }
                    ↓
    3. Копируем токен и вставляем его в заголовок Authorization
       следующих запросов
                    ↓
    4. Через 10 минут токен «протух» → 401 Unauthorized → логинимся снова
```

### Какие эндпоинты требуют токен

| Эндпоинт | Токен нужен? |
| :-- | :-- |
| GET /usuarios, GET /usuarios/{id} | ❌ не нужен |
| POST /usuarios (регистрация) | ❌ не нужен |
| PUT /usuarios/{id} | ❌ не нужен |
| DELETE /usuarios/{id} | ✅ нужен |
| GET /produtos, GET /produtos/{id} | ❌ не нужен |
| POST /produtos, PUT /produtos/{id}, DELETE /produtos/{id} | ✅ нужен + пользователь должен быть администратором |
| Всё, что связано с /carrinhos (корзины) | ✅ нужен |

> 💡 Именно поэтому в задании 4 мы регистрируем пользователя с `"administrador": "true"` — чтобы в будущих заданиях он мог создавать/удалять товары.

### Шаг 1. Получаем токен через `POST /login`

```java
String token = given()
        .contentType(ContentType.JSON)
        .body("""
            {
              "email": "spy_1730000000@qa.com",
              "password": "secret123"
            }
            """)
    .when()
        .post("/login")
    .then()
        .statusCode(200)
        .body("message", equalTo("Login realizado com sucesso"))
        .extract().path("authorization");
```

### Что приходит в ответе

```json
{
  "message": "Login realizado com sucesso",
  "authorization": "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
}
```

⚠️ **Очень важно:** поле `authorization` уже содержит префикс `"Bearer "`. Не добавляйте его повторно — иначе получите `"Bearer Bearer eyJ..."` и 401 в ответ. Просто используйте значение как есть.

### Шаг 2. Передаём токен в защищённый запрос

Есть три равнозначных способа:

```java
// Вариант 1 — через .header() (самый частый и понятный)
given()
    .header("Authorization", token)
    .pathParam("id", userId)
.when()
    .delete("/usuarios/{id}")
.then()
    .statusCode(200);

// Вариант 2 — через .headers() (если заголовков несколько)
given()
    .headers("Authorization", token,
             "Accept",         "application/json")
    ...

// Вариант 3 — через RestAssured-DSL для JWT (нужно убрать префикс "Bearer ")
given()
    .auth().oauth2(token.replace("Bearer ", ""))
    ...
```

Для этой домашки используйте **вариант 1** — он самый явный.

### Шаг 3. Сохраняем токен между тестами

Так как несколько тестов используют один токен, храним его в статическом поле класса:

```java
public class ServeRestTest {
    private static String userId;   // из задания 4
    private static String token;    // из задания 6.1

    @Test @Order(4)
    void shouldLogin() {
        token = given()
                .contentType(ContentType.JSON)
                .body("...")
            .when().post("/login")
            .then().statusCode(200)
                   .extract().path("authorization");
    }

    @Test @Order(5)
    void shouldDeleteUser() {
        given()
                .header("Authorization", token)  // ← используем сохранённый токен
                .pathParam("id", userId)
            .when().delete("/usuarios/{id}")
            .then().statusCode(200);
    }
}
```

### Типичные ошибки и как их узнать

| Что видите в ответе | Что случилось | Как исправить |
| :-- | :-- | :-- |
| 401 + "Token de acesso ausente..." | Забыли заголовок Authorization | Добавить .header("Authorization", token) |
| 401 + "Token de acesso ... expirado" | Прошло больше 10 минут | Перелогиниться и получить новый токен |
| 403 + "Rota exclusiva para administradores" | Пользователь — не админ | Регистрировать с "administrador": "true" |
| 400 + "Email já está sendo usado" | Такой email уже есть в БД | Использовать System.currentTimeMillis() в email |

### Мини-лайфхак для отладки

Если авторизация «магически» не работает — временно добавьте `.log().all()` в оба запроса (логин и защищённый) и посмотрите, что реально уходит на сервер. В 90% случаев проблема видна невооружённым глазом: не тот заголовок, лишний `Bearer`, или в токене оказалось `null`.

* * *

## 💡 Прочие полезные подсказки

* **База данных ServeRest сбрасывается каждый день** — если завтра запустите тест 5, а пользователя уже нет, просто перезапустите весь класс, чтобы задание 4 создало нового.
* **Уникальный email:** самый простой способ — `"spy_" + System.currentTimeMillis() + "@[qa.com](http://qa.com)"`. Так email каждый раз будет разным.
* **Порядок тестов:** задания 4→5→6 зависят друг от друга. Используйте:
  ```java
  @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
  public class ServeRestTest {
      @Test @Order(1) @DisplayName("...")
      void shouldCreateNewUser() { ... }
      @Test @Order(2) @DisplayName("...")
      void shouldUpdateUser() { ... }
  }
  ```
* **Text block для JSON** (Java 15+):
  ```java
  String body = """
      { "nome": "Тайный Покупатель", "email": "spy@qa.com",
        "password": "secret123", "administrador": "true" }
      """;
  ```
  Если у вас Java 11 — используйте обычную строку с `\"` экранированием.



[Фильтровать данные таблицы](#)[Создать сводную таблицу](#)[Создать диаграмму](#)

[Configure buttons visibility](/users/tfac-settings.action)