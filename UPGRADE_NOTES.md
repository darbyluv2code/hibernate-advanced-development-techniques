# Hibernate 7.2 Upgrade Notes

This codebase has been upgraded from Hibernate 5.3.6 to Hibernate 7.2.0.

## What Changed

1. Java Version: Now requires Java 25+ (previously Java 11)
2. Package Names: All javax.persistence.* imports changed to jakarta.persistence.*
3. Maven Dependencies: Updated to Hibernate ORM 7.2.0.Final and MySQL Connector/J 9.5.0
4. JDBC Driver: Standardized to com.mysql.cj.jdbc.Driver (legacy driver removed)
5. MySQL Dialect: Simplified to org.hibernate.dialect.MySQLDialect
6. OrderBy Annotation: Replaced deprecated Hibernate @OrderBy with JPA standard @OrderBy

## What Stayed the Same

- All Hibernate concepts and APIs work identically
- SessionFactory and Session usage unchanged
- Entity annotations function the same way
- All collection mappings work as before
- Inheritance strategies unchanged

## Video Course Alignment

The video lectures show code with javax.persistence.* imports. When following
along, use jakarta.persistence.* instead. All concepts and code logic remain identical.

Additionally, one video shows the deprecated Hibernate-specific @OrderBy annotation:
- Video code: `@org.hibernate.annotations.OrderBy(clause = "file_name")`
- Current code: `@OrderBy("file_name ASC")` with import `jakarta.persistence.OrderBy`
- Both achieve the same result; the updated version is JPA standard and future-proof.

## Requirements

- Java 25 or higher
- Maven 3.6+
- MySQL 9.0+

## Migration Guide

See /appendix/migrate-hibernate-5-to-hibernate-7.md for detailed migration instructions.
