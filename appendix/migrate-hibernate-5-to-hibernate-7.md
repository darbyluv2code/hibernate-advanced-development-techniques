# Hibernate 5 to 7.2 Migration Guide (MySQL)

If you're ready to move up to Hibernate 7.2, you'll be happy to know the transition is **smooth and straightforward**. Most of the updates are small adjustments carried over from the 6.x and 7.x series (such as Jakarta package names and updated dialects), along with a few refinements in Hibernate 7.2.  

## Important Notes

- All of the **core Hibernate concepts and course content work seamlessly in Hibernate 7.2**.  
- There are **no disruptive changes** ... just a handful of refinements to keep everything aligned with the latest Hibernate and Jakarta standards.  
- You'll continue using the same familiar APIs and mappings; this migration simply **modernizes the libraries** so your project runs on the latest version with ease.  
- **Hibernate 7.2 is the latest stable release** as of December 2025.

Below is a step-by-step guide I created to help you migrate your project to Hibernate 7.2.  

---

## Software Versions
- Java: 17+
- Hibernate ORM: 7.2.x (e.g., 7.2.0.Final)
- MySQL Connector/J: 9.x

---

## 1) Update Maven coordinates and Java level

**What to change**

* **Java 17+ is required**
* Move to the new **groupId**: `org.hibernate.orm`
* Use a current **7.2.x** (example below pins `7.2.0.Final`)
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
    <version>7.2.0.Final</version>
  </dependency>

  <dependency>
    <groupId>com.mysql</groupId>
    <artifactId>mysql-connector-j</artifactId>
    <version>9.4.0</version>
  </dependency>
</dependencies>
```

You can safely remove other legacy dependencies such as: `jaxb-api`, `jaxb-core`, `jaxb-impl`, `javax.activation`. 

Your final pom.xml should look like this:

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
      <version>7.2.0.Final</version>
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
* Update `connection.url` to drop additional parameters
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
* [ ] Version updated to **7.2.0.Final**

---

## What's New in Hibernate 7.2?

Hibernate 7.2 includes several improvements while maintaining full backward compatibility with 7.1:

- **Pessimistic Locking Improvements** - Enhanced locking strategies and options
- **@EmbeddedTable Support** - New annotation for embedded table mappings
- **FindMultipleOptions** - Improved API for finding multiple entities
- **Vector Support for SQL Server** - Native vector type support for SQL Server databases
- **Performance Optimizations** - Various internal improvements for better query performance
- **Minor API Deprecations** - `Session.contains(String, Object)` deprecated in favor of `contains(Object)`

**For typical course usage and learning scenarios, these changes are transparent ... your existing code will work without modification.**

---

By following these steps, you will successfully migrate your project to Hibernate 7.2. :smile:

---

## Version History

- **Hibernate 7.2.0.Final** (Latest Stable) - December 12, 2025
- **Hibernate 7.1.0.Final** - August 8, 2025
- **Hibernate 7.0.0.Final** - May 19, 2025 - Major release with Jakarta Persistence 3.2, Apache License 2.0
- **Hibernate 6.0.0.Final** - March 31, 2022 - Major redesign of query system and type handling
- **Hibernate 5.6.2.Final** - December 8, 2021 

---

References - From Hibernate ORM Website:
* [Hibernate 6.0 Migration Guide](https://docs.jboss.org/hibernate/orm/6.0/migration-guide/migration-guide.html)
* [Hibernate 7.0 Migration Guide](https://docs.jboss.org/hibernate/orm/7.0/migration-guide/migration-guide.html)
* [Hibernate 7.1 Migration Guide](https://docs.jboss.org/hibernate/orm/7.1/migration-guide/migration-guide.html)
* [Hibernate 7.2 Migration Guide](https://docs.hibernate.org/orm/7.2/migration-guide/)
* [Hibernate ORM Releases](https://hibernate.org/orm/releases/)
