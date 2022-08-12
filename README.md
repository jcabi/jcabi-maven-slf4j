<img alt="logo" src="http://img.jcabi.com/logo-square.svg" width="64px" height="64px" />

[![EO principles respected here](https://www.elegantobjects.org/badge.svg)](https://www.elegantobjects.org)
[![Managed by Zerocracy](https://www.0crat.com/badge/C3RUBL5H9.svg)](https://www.0crat.com/p/C3RUBL5H9)
[![DevOps By Rultor.com](http://www.rultor.com/b/jcabi/jcabi-maven-slf4j)](http://www.rultor.com/p/jcabi/jcabi-maven-slf4j)

[![mvn](https://github.com/jcabi/jcabi-maven-slf4j/actions/workflows/mvn.yml/badge.svg)](https://github.com/jcabi/jcabi-maven-slf4j/actions/workflows/mvn.yml)
[![PDD status](http://www.0pdd.com/svg?name=jcabi/jcabi-maven-slf4j)](http://www.0pdd.com/p?name=jcabi/jcabi-maven-slf4j)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.jcabi/jcabi-maven-slf4j/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.jcabi/jcabi-maven-slf4j)
[![Javadoc](https://javadoc.io/badge/com.jcabi/jcabi-maven-slf4j.svg)](http://www.javadoc.io/doc/com.jcabi/jcabi-maven-slf4j)
[![codecov](https://codecov.io/gh/jcabi/jcabi-maven-slf4j/branch/master/graph/badge.svg)](https://codecov.io/gh/jcabi/jcabi-maven-slf4j)

More details are here: [slf4j.jcabi.com](https://slf4j.jcabi.com/index.html)

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

Versions:

  * jcabi-maven-slf4j 0.11 is for slf4j-api 1.6
  * jcabi-maven-slf4j 0.12 is for slf4j-api 2.0

## How to contribute?

Fork the repository, make changes, submit a pull request.
We promise to review your changes same day and apply to
the `master` branch, if they look correct.

Please run Maven build before submitting a pull request:

```
$ mvn clean install -Pqulice
```
