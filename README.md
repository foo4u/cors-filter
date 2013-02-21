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
  <version>${version}</version>
</dependency>
```

### Repositories

```
<repository>
  <id>releases</id>
  <url>http://repo.rossillo.net/nexus/content/repositories/releases</url>
</repository>

<snapshotRepository>
  <id>snapshots</id>
  <url>http://repo.rossillo.net/nexus/content/repositories/snapshots</url>
</snapshotRepository>  
```

## Building from Source

1. Clone the repository from GitHub
2. Navigate into the cloned repository directory
3. Use [Gradle] [2] to build the distribution

```
$ git clone git://github.com/foo4u/cors-filter.git
$ cd cors-filter
$ $ ./gradlew build
```

## Importing Sources Into Eclipse

1. Generate Eclipse metadata
2. Once complete, import into Eclipse `File -> Import -> Existing projects into workspace`

```
$ ./gradlew eclipse
```

## Issue Tracking

Report bugs and feature requests via [GitHub Issues] [3].

## Contributing

Pull requests welcome.  Please follow these simple guidelines:

1. Fork the repository
2. Always work on topic branches (e.g. ISSUE-123)
3. Follow the "Commit Guidelines" outlined in [Pro Git] [4]
3. When ready to resolve an issue or to collaborate with others, you can push your branch to origin
4. When ready for your contribution to be reviewed for potential inclusion, send a pull request

## License

CORS Filter is licensed under the MIT License.

[1]: http://en.wikipedia.org/wiki/Cross-origin_resource_sharing "Wikipedia"
[2]: http://www.gradle.org "Gradle"
[3]: https://github.com/foo4u/cors-filter/issues "Issues"
[4]: http://git-scm.com/book/ch5-2.html "Pro-Git"
