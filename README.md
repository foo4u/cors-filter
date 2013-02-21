# CORS Filter

Provides a [cross-origin resource sharing] [1] servlet filter compatible with Java 1.4+ JVMs.

This filter is intended to be simple and require no external runtime dependencies besides 
the J2EE servlet API.  It is by no means the most feature rich CORS servlet filter 
implementation but instead focuses on simplicity, minimal code and support for the 
legacy JDK 1.4.

## Usage

Add the filter and at least one filter mapping to your `web.xml` file.

```
<filter id="CorsFilter">
  <filter-name>CorsFilter</filter-name>
  <filter-class>net.rossillo.cors.filter.SimpleCorsFilter</filter-class>
</filter>
<filter-mapping>
  <filter-name>CorsFilter</filter-name>
  <url-pattern>/service/*</url-pattern>
</filter-mapping>
```

By default, only HTTP `GET` requests are permitted by the filter.  To add support for other
HTTP request methods, declare them as a comma-delimited string via an init parameter.

For example, to support `GET`, `POST` and `PUT` requests, specify them as the value for the
`methods` configuration parameter.

```
<filter id="CorsFilter">
  <filter-name>CorsFilter</filter-name>
  <filter-class>net.rossillo.cors.filter.SimpleCorsFilter</filter-class>
  <init-param>
    <param-name>methods</param-name>
    <param-value>GET, POST, PUT</param-value>
  </init-param>
</filter>
```

## Downloading Artifacts

### Dependencies

```
<dependency>
  <groupId>net.rossillo.cors</groupId>
  <artifactId>cors-filter</artifactId>
  <version>1.0.0.BUILD-SNAPSHOT</version>
</dependency>
```

### Repositories

```
<repository>
  <id>releases</id>
  <url>http://repo.rossillo.net/nexus/content/repositories/releases</url>
</repository>
  
<repository>
  <id>milestones</id>
  <url>http://repo.rossillo.net/nexus/content/repositories/milestones</url>
</repository>
  
<snapshotRepository>
  <id>snapshots</id>
  <url>http://repo.rossillo.net/nexus/content/repositories/snapshots</url>
</snapshotRepository>  
```



[1]: http://en.wikipedia.org/wiki/Cross-origin_resource_sharing "Wikipedia"

