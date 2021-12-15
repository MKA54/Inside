Тестовое задание.

1) Скопируйте ссылку на репозторий и сделайте клон в своей среде разработки.
2) В корневой папке находится файл inside.sql, он написан на языке MySql, для визуального проектирования базы данных я использовал MySql WorkBench.
3) Вставьте файл inside.sql в инструмент MySql WorkBench (или другой) и выполните код написанный в файле для создания базы данных с зполнеными полями.
4) Конфигурация подключения к базе данных находитя в файле src.main.resources.application.properties:
   - db.url:jdbc:mysql://localhost:3306/inside?serverTimezone=UTC где localhost:3306 порт базы данных, зависит от настроек пользователя
   - db.username:root имя пользователя для входа в базу данных
   - db.password:1234 пароль для входа в базу данных
5) Файл запуска приложения находится в src.main.test.inside.InsideApplication
6) Для передачи данных в приложение я использовал Advanced REST client (адреса в классе в src.main.test.inside.userController.UserController)
7) Для вызова метода authorization используюется POST запрос по адресу http://localhost:8080/inside/authorization, localhost:8080 порт зависит от настроек системы пользователя, в качестве параметра Header передаем content-type:application/json, в Body (пример)
   {
   name:"Andre",
   password:"123"
   }
   Нажимаем отправить и в случае правильного исполнения запроса полчучаем:
   {
   token:"........."
   }
8) Для вызова метода addMessage используюется POST запрос по адресу http://localhost:8080/inside/addMessage, в качестве параметра Header передаем content-type:application/json, добавляем authorization: Bearer ....(полученный токен), в Body (пример)
   {
   name:"Andre",
   message:"Hello World"
   }
   Нажимаем отправить и в случае правильного исполнения запроса полчучаем: true
9) Для вызова метода addMessage используюется POST запрос по адресу http://localhost:8080/inside/getMessageHistory, в качестве параметра Header передаем content-type:application/json, добавляем authorization: Bearer ....(полученный токен), в Body (пример)
   {
   name:"Andre",
   message:"history 5"
   }
   Нажимаем отправить и в случае правильного исполнения запроса полчучаем: массив из 5 последних сообщений.
10) Файлы тестирования распологаются в inside.src.test.java.test.inside.service
11) MessageServiceTest тестирует класс src.main.java.test.inside.service.MessageServiceTest
12) TokenServiceTest тестирует класс src.main.java.test.inside.service.TokenService
13) UserServiceTest тестирует класс src.main.java.test.inside.service.UserService
14) В корне присутствует файл Plant!!!