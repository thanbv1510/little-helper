# little-helper
> - is a Java core library for Java applications inspired by Spring Boot
> - make application initialization and configuration easier

### Note
- Minimum JDK version: 1.8
- Support:
    + Logging: Sfl4j and Log4j2
    + configuration: [DOT]yaml or [DOT]properties.

## Installation
- Add dependency to pom.xml:
```xml
<dependency>
    <groupId>dev.thanbv1510</groupId>
    <artifactId>little-helper</artifactId>
    <version>1.0</version>
</dependency>
```
## Usage Guides
For full detail guides & example, see the Wiki page.
## Quick Guides
### In Main class:
- you need using `@LittleHelper` annotation
```java
@LittleHelper
public class AppTest {
    public static void main(String[] args) {
        LittleHelperApplication.run(AppTest.class, args);
    }
}
```

```java
@Init
public void init() throws Exception {
    // code here
}
```

```java
@ShutdownHook
public void preShutdown() throws Exception {
    // code here
}
```