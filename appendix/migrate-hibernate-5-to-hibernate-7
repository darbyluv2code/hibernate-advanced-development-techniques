# Hibernate 5 → 7.1 Migration Guide (MySQL)

Migrating from Hibernate 5.x to 7.1 pulls in the API and package changes introduced in 6.x (Jakarta, dialects, bootstrap) plus 7.x refinements. The steps below modernize your POM, imports, XML, and bootstrap and call out gotchas found in real upgrades.

## Software Versions
- Java: 17+
- Hibernate ORM: 7.1.x (e.g., 7.1.1.Final)
- MySQL Connector/J: 9.x

---

## 1) Update Maven coordinates and Java level

**What to change**

* **Java 17+ is required**
* Move to the new **groupId**: `org.hibernate.orm`
* Use a current **7.1.x** (example below pins `7.1.1.Final`)
* Switch MySQL driver to `com.mysql:mysql-connector-j` (8/9 series)

**Minimal `pom.xml`**

```xml
<properties>
  <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
  <maven.compiler.source>17</maven.compiler.source>
  <maven.compiler.target>17</maven.compiler.target>
</properties>

<dependencies>
  <dependency>
    <groupId>org.hibernate.orm</groupId>
    <artifactId>hibernate-core</artifactId>
    <version>7.1.1.Final</version>
  </dependency>

  <dependency>
    <groupId>com.mysql</groupId>
    <artifactId>mysql-connector-j</artifactId>
    <version>9.4.0</version>
  </dependency>
</dependencies>
```

You can safely remove other legacy dependencies such as: `jaxb-api`, `jaxb-core`, `jaxb-impl`, `javax.activation`. 

You final pom.xml should look like this:

```xml
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>

  <groupId>com.luv2code</groupId>
  <artifactId>solution-code-hibernate-hb-02-map-collections</artifactId>
  <version>1.0</version>
  <packaging>jar</packaging>

  <properties>
    <maven.compiler.source>17</maven.compiler.source>
    <maven.compiler.target>17</maven.compiler.target>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
  </properties>

  <dependencies>
    <dependency>
      <groupId>org.hibernate.orm</groupId>
      <artifactId>hibernate-core</artifactId>
      <version>7.1.1.Final</version>
    </dependency>

    <dependency>
      <groupId>com.mysql</groupId>
      <artifactId>mysql-connector-j</artifactId>
      <version>9.4.0</version>
    </dependency>
  </dependencies>
</project>
```


---

## 2) Replace `javax.persistence` with `jakarta.persistence`

**What to change**

* Project-wide: `javax.persistence.*` → `jakarta.persistence.*`

Your `Student` entity needs the `jakarta` imports.

Apply this to other JPA entities in your project.

---

## 3) Update `hibernate.cfg.xml` (dialect & schema)

**What to change**

* Prefer `org.hibernate.dialect.MySQLDialect` (or omit to let Hibernate auto-detect).
* Keep your DDL strategy. Hibernate’s legacy `hibernate.hbm2ddl.auto` and the JPA standard key both work; pick one style.

**Suggested XML**

```xml
<!DOCTYPE hibernate-configuration PUBLIC
  "-//Hibernate/Hibernate Configuration DTD 3.0//EN"
  "http://www.hibernate.org/dtd/hibernate-configuration-3.0.dtd">

<hibernate-configuration>
  <session-factory>
    <property name="connection.driver_class">com.mysql.cj.jdbc.Driver</property>
    <property name="connection.url">jdbc:mysql://localhost:3306/hb_student_tracker</property>
    <property name="connection.username">hbstudent</property>
    <property name="connection.password">hbstudent</property>

    <!-- JDBC connection pool settings ... using built-in test pool -->
    <property name="connection.pool_size">1</property>

    <!-- Select our SQL dialect -->
    <property name="hibernate.dialect">org.hibernate.dialect.MySQLDialect</property>

    <!-- Echo the SQL to stdout -->
    <property name="show_sql">true</property>

    <!-- Auto create tables -->
    <property name="hibernate.hbm2ddl.auto">create</property>

    <!-- Set the current session context -->
    <property name="current_session_context_class">thread</property>

  </session-factory>
</hibernate-configuration>
```

---

## Quick verification checklist

* [ ] Build runs on **JDK 17+** and compiles after `javax`→`jakarta` imports
* [ ] MySQL driver switched to `com.mysql:mysql-connector-j`
* [ ] Dialect is `org.hibernate.dialect.MySQLDialect` (or omitted for autodetect)
* [ ] `hibernate.cfg.xml` still uses the **3.0 DTD** identifier/URL

---

By following these steps, you will successfully migrate your project to Hibernate 7.1. 