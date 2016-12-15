<img src="http://img.jcabi.com/logo-square.svg" width="64px" height="64px" />

[![Made By Teamed.io](http://img.teamed.io/btn.svg)](http://www.teamed.io)
[![DevOps By Rultor.com](http://www.rultor.com/b/jcabi/jcabi-maven-slf4j)](http://www.rultor.com/p/jcabi/jcabi-maven-slf4j)

[![Build Status](https://travis-ci.org/jcabi/jcabi-maven-slf4j.svg?branch=master)](https://travis-ci.org/jcabi/jcabi-maven-slf4j)
[![PDD status](http://www.0pdd.com/svg?name=jcabi/jcabi-maven-slf4j)](http://www.0pdd.com/p?name=teamed/jcabi/jcabi-maven-slf4j)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.jcabi/jcabi-maven-slf4j/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.jcabi/jcabi-maven-slf4j)
[![Javadoc](https://javadoc-emblem.rhcloud.com/doc/com.jcabi/jcabi-maven-slf4j/badge.svg)](http://www.javadoc.io/doc/com.jcabi/jcabi-maven-slf4j)
[![Dependencies](https://www.versioneye.com/user/projects/561ac397a193340f2f0011c0/badge.svg?style=flat)](https://www.versioneye.com/user/projects/561ac397a193340f2f0011c0)

More details are here: [slf4j.jcabi.com](http://slf4j.jcabi.com/index.html)

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

## Questions?

If you have any questions about the framework, or something doesn't work as expected,
please [submit an issue here](https://github.com/jcabi/jcabi-maven-slf4j/issues/new).

## How to contribute?

Fork the repository, make changes, submit a pull request.
We promise to review your changes same day and apply to
the `master` branch, if they look correct.

Please run Maven build before submitting a pull request:

```
$ mvn clean install -Pqulice
```
