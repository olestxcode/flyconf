# flyconf
Lightweight and powerful annotation-based configuration library

### Usage Guide:
First of all, you need to create a new `FlyconfInstance`.
`FlyconfInstance` is a specifically configured container for your configurations.
You can use different Flyconf instances if various configurations use different parsers. It allows you to register custom parsers separately.

Creating a new Flyconf instance:  
`FlyconfInstance instance = Flyconf.newInstance();`

`FlyconfInstance` methods:
* `<T> T load(PropertyMapLoader loader, Class<T> into);`
* `<T> FlyconfInstance registerCustomParser(Class<T> type, Function<String, T> parserFunction);`
* `FlyconfInstance setDefaultConvention(Convention.ConventionAdapter adapter);`
* `Convention.ConventionAdapter getConventionAdapter();`

### Configuration Loading
Let's create a simple .properties configuration interface:
```
@Configuration
public interface MyConfig {
    String greeting();
}
```
#### config.properties:
```
greeting=Hello World!
```

```
File myPropsFile = ...;
MyConfig config = instance.load(new PropertiesConfigurationLoader(myPropsFile), MyConfig.class);
System.out.println(config.greeting());

Output:
Hello World!
```

### Creating advanced configurations
```
@Configuration
public interface MyAdvancedConfig {
    @Mandatory
    String greeting();
    
    @DefaultValue("Welcome!")
    String title();
    
    Optional<String> subtitle();
    
    @MultiValue(delimiter = "|")
    List<String> description();
    
    @Property("send-message-in")
    Duration sendMessageAfter();
}
```

### Nested configuration sections
```
@Configuration
public interface JdbcConfig {
    @Mandatory
    String host();
    
    @DefaultValue("3306")
    int port();
    
    String username();
    
    String password();
}
```

```
@Configuration
public interface MainConfig {

    String applicationName();
    
    JdbcConfig jdbc();
}
```

As you can see, our main configuration has a separate JDBC configuration section.

### Property Conventions
You can configure your `FlyconfInstance` or a separate configuration root to adapt Java `camelCase` convention into any other using `ConventionAdapters`.
There are a few adapters:
* `kebab-case` implemented by `KebabCaseAdapter`
* `SCREAMING_SNAKE_CASE` implemented by `ScreamingSnakeCaseAdapter`
* `snake_case` implemented by `SnakeCaseAdapter`

To set a specific adapter on `FlyconfInstance` level, use:
`instance#setDefaultConvention` method.
You can also set it for specific configuration:
```
@Configuration
@Convention(adapter = KebabCaseAdapter.class)
public interface MyKebabConfig {
}
```