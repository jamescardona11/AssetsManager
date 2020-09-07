package co.kodevincere.assetsmanager

import com.google.gson.Gson
import org.yaml.snakeyaml.Yaml
import java.io.File
import java.io.InputStream

class FileManager {

    private val fileConfigName = "assets_manager_config.yaml"

    fun createDefaultConfigFile(path: String) {
        println("Creating assets manager default config...")
        //Create a default file
        val file = File("$path/$fileConfigName")

        //Write default values for file
        val named = ConfigFileKey()

        val configFile = ConfigFileValues()
        file.bufferedWriter().use { out ->
            out.write("name: AssetsManager Config\n")
            out.write("description: A Config to create a better experience with plugin\n")

            out.newLine()
            out.newLine()
            out.write("# Input assets directory\n")
            out.write("${named.assetsFolder}: ${configFile.assetsFolder}\n")

            out.newLine()
            out.write("# Output config\n")
            out.write("${named.defaultFolder}: ${configFile.defaultFolder}\n")
            out.write("${named.ignoreFonts}: ${configFile.ignoreFonts}\n")
            out.write("${named.postfixAssets}: ${configFile.postfixAssets}\n")

            out.newLine()
            out.write("# Output assets config\n")
            out.write("${named.nameOfAssetsClass}: ${configFile.nameOfAssetsClass}\n")
            out.write("${named.nameOfAssetsFile}: ${configFile.nameOfAssetsFile}\n")

            out.newLine()
            out.write("# Output fonts config\n")
            out.write("#${named.nameOfFontsClass}: ${configFile.nameOfFontsClass}\n")
            out.write("#${named.nameOfFontsFile}: ${configFile.nameOfFontsFile}\n")
            out.write("#${named.mergeFontsInAssetsClass}: ${configFile.mergeFontsInAssetsClass}\n")
        }
    }

    fun readConfigFile(path: String): ConfigFileValues {
        println("Reading assets manager config...")

        //Open a default file
        val file = File("$path/$fileConfigName")
        val yaml = Yaml()
        val inputStream: InputStream = file.inputStream()
        val obj = yaml.load<Map<String, Any>>(inputStream)


        val namedFile = ConfigFileKey()
        val defaultValues = ConfigFileValues()
        return ConfigFileValues(
                assetsFolder = obj[namedFile.assetsFolder] as String? ?: defaultValues.assetsFolder,
                defaultFolder = obj[namedFile.defaultFolder] as String? ?: defaultValues.defaultFolder,
                ignoreFonts = obj[namedFile.ignoreFonts] as Boolean? ?: defaultValues.ignoreFonts,
                postfixAssets = obj[namedFile.postfixAssets] as String? ?: defaultValues.postfixAssets,
                nameOfAssetsClass = obj[namedFile.nameOfAssetsClass] as String? ?: defaultValues.nameOfAssetsClass,
                nameOfAssetsFile = obj[namedFile.nameOfAssetsFile] as String? ?: defaultValues.nameOfAssetsFile,
                mergeFontsInAssetsClass = obj[namedFile.mergeFontsInAssetsClass] as Boolean?
                        ?: defaultValues.mergeFontsInAssetsClass,
                nameOfFontsClass = obj[namedFile.nameOfFontsClass] as String? ?: defaultValues.nameOfFontsClass,
                nameOfFontsFile = obj[namedFile.nameOfFontsFile] as String? ?: defaultValues.nameOfFontsFile,
        )

    }

    fun readAssets(path: String, configFile: ConfigFileValues = ConfigFileValues()) {
        val list: ArrayList<AssetsFiles> = ArrayList()

        var folderName = ""
        File("$path/${configFile.assetsFolder}").walk().forEach {
            if(it.name.contains(".")) {
                list.add(AssetsFiles(it.name, folderName, it.path, configFile.postfixAssets, configFile.assetsFolder))
            }else{
                folderName = it.name
            }
        }

        for (s in list) {
            println(s.toString())
        }
    }


}