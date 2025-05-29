1. Проект: Продвинутая работа с Hibernate ORM
   Расширенный проект для изучения сложных возможностей Hibernate ORM.
   Включает работу с отношениями между сущностями, кэшированием, критериями запросов и оптимизацией производительности.

2. Собрать и запустить
   mvn clean package
   docker run --name mysql-movie -e MYSQL_ROOT_PASSWORD=mysql -e MYSQL_DATABASE=movie -p 3306:3306 -d mysql:8.0
   Инициализировать базу с помощью dumpForHibernate2.sql
   java -jar target/project-hibernate-2-1.0-SNAPSHOT-jar-with-dependencies.jar
   
3. Технические особенности
   Реализованы маппинги: OneToMany, ManyToMany,OneToOne 
   Использован второй уровень кэша Hibernate 
   Настроена работа с Criteria API и HQL запросами

4. Функциональность
   Работа с связанными сущностями и каскадными операциями 
   Ленивая и жадная загрузка данных 
   Оптимизация запросов и N+1 проблема 
   Пакетная обработка данных 
   Кэширование на уровне сессии и второго уровня

