# Hibernate 5 to 7 Migration Guide (MySQL)

If you’re ready to move up to Hibernate 7, you’ll be happy to know the transition is **smooth and straightforward**. Most of the updates are small adjustments carried over from the 6.x series (such as Jakarta package names and updated dialects), along with a few refinements in Hibernate 7.  

## Important Notes

- All of the **core Hibernate concepts and course content work seamlessly in Hibernate 7**.  
- There are **no disruptive changes** ... just a handful of refinements to keep everything aligned with the latest Hibernate and Jakarta standards.  
- You’ll continue using the same familiar APIs and mappings; this migration simply **modernizes the libraries** so your project runs on the latest version with ease.  

Below is a step-by-step guide I created to help you migrate your project to Hibernate 7.  

---

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

* Update `connection.driver_class` to use modern MySQL JDBC driver
* Updae `connection.url` to drop additional parameters
* Prefer `org.hibernate.dialect.MySQLDialect` (or omit to let Hibernate auto-detect).

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

    <!-- Auto create the tables -->
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

---

By following these steps, you will successfully migrate your project to Hibernate 7. :smile:

---

References - From Hibernate ORM Website:
* [Hibernate 6.0 Migration Guide](https://docs.jboss.org/hibernate/orm/6.0/migration-guide/migration-guide.html)
* [Hibernate 7.0 Migration Guide](https://docs.jboss.org/hibernate/orm/7.0/migration-guide/migration-guide.html)
* [Hibernate 7.1 Migration Guide](https://docs.jboss.org/hibernate/orm/7.1/migration-guide/migration-guide.html) 