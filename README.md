<img src="http://img.jcabi.com/logo-square.png" width="64px" height="64px" />
 
[![Build Status](https://travis-ci.org/jcabi/jcabi-maven-slf4j.svg?branch=master)](https://travis-ci.org/jcabi/jcabi-maven-slf4j)

More details are here: [www.jcabi.com/jcabi-maven-slf4j](http://www.jcabi.com/jcabi-maven-slf4j/index.html)

Maven Log to SLF4J binding is implemented with
`StaticLoggerBinder` singleton. This is how you use it in your Maven plugin:

```java
import com.jcabi.log.Logger;
import org.apache.maven.plugin.AbstractMojo;
import org.slf4j.impl.StaticLoggerBinder;
public class MyMojo extends AbstractMojo {
  @Override
  public void execute() {
    StaticLoggerBinder.getSingleton().setMavenLog(this.getLog());
    // ... later ...
    Logger.info(this, "hello, world!");
    // and you can still use the usual logging mechanism
    this.getLog().info("hello again");
  }
}
```

The `Logger.info()` call will go to Maven Log through SLF4J.

You need just this dependency:

```xml
<dependency>
  <groupId>com.jcabi</groupId>
  <artifactId>jcabi-maven-slf4j</artifactId>
  <version>0.8</version>
</dependency>
```

## Questions?

If you have any questions about the framework, or something doesn't work as expected,
please [submit an issue here](https://github.com/jcabi/jcabi-maven-slf4j/issues/new).
If you want to discuss, please use our [Google Group](https://groups.google.com/forum/#!forum/jcabi).

## How to contribute?

Fork the repository, make changes, submit a pull request.
We promise to review your changes same day and apply to
the `master` branch, if they look correct.

Please run Maven build before submitting a pull request:

```
$ mvn clean install -Pqulice
```
