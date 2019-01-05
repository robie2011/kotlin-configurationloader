package ch.rajakone.ConfigurationLoader

import com.fasterxml.jackson.databind.exc.InvalidDefinitionException
import com.fasterxml.jackson.dataformat.javaprop.JavaPropsMapper
import com.fasterxml.jackson.module.kotlin.readValue

class ConfigurationLoader {
    inline fun <reified T>getConfigFilename() : String {
        return T::class
                .java.simpleName
                .replace("Configuration", "") + ".properties"

    }

    inline fun <reified T>load(): T {
        val configFilename =  getConfigFilename<T>()
        val mapper = JavaPropsMapper()
        val loader = Thread.currentThread().getContextClassLoader()
        val fileStream = loader.getResourceAsStream(configFilename)
                ?: throw Exception("Can not find configuration file: " + configFilename)
        try {
            return mapper.readValue<T>(fileStream)
        } catch (e: InvalidDefinitionException) {
            if (e.message?.contains("no Creators, like default construct, exist")!!){
                throw Exception("Please create default constructor for: " + e.type)
            } else throw e
        }
    }
}
