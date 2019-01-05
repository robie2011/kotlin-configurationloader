# Configuration Loader
Simple configuration-class deserializer from Properties-File. 

Note: 
  * configuration classname must end with suffix 'Configuration' 
  * configuration class must have a default constructor
  * configuration file must be named according to pattern: `[ConfigurationClassnameWithoutConfigurationSuffix].properties` (see example below)

## build configuration

```groovy
repositories {
    // ...
    maven { url "https://jitpack.io" }
}

dependencies {
    // ...
    compile "com.github.robie2011:kotlin-configurationloader:master-SNAPSHOT"
}
```

## usage example

configuration file

```properties
# content of: src/main/resources/Person.properties
name = Bob Marley
age = 39
```

application code

```kotlin
import ch.rajakone.ConfigurationLoader.ConfigurationLoader

object Starter {
    
    @JvmStatic
    fun main(args: Array<String>) {
        println("hello from kotlin")

        val config = ConfigurationLoader().load<PersonConfiguration>()
        println(config)
    }
}

data class PersonConfiguration (
    val name: String = "",
    val age: Int = 0
)
```