package co.kodevincere.assetsmanager

import org.yaml.snakeyaml.Yaml
import java.io.File
import java.io.FileWriter
import java.io.InputStream

private const val FILE_CONFIG_NAME = "assets_manager_config.yaml"
private const val MAC_OS_DS_STORE = ".DS_Store"
private const val defValueDart = "\tstatic const String"
private const val middleValueDart = "= \""
private const val endValueDart = "\";\n"

class FileManager(val path: String) {

    fun createDefaultConfigFile() {
        println("Creating assets manager default config...")
        //Create a default file
        var finalPath = path
        if(getSlashCharacter(finalPath)) finalPath += "/"
        finalPath += FILE_CONFIG_NAME

        val file = File(finalPath)

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
            out.write("${named.defaultFolder}: ${configFile.folderOutput}\n")
            out.write("${named.ignoreFonts}: ${configFile.ignoreFontsConfig}\n")
            out.write("${named.postfixAssets}: ${configFile.postfixAssets}\n")
            out.write("${named.pubspecStrategy}: ${configFile.pubspecStrategy}\n")
            out.write("${named.pubspecUpdateFonts}: ${configFile.pubspecUpdateFonts}\n")

            out.newLine()
            out.write("# Output assets config\n")
            out.write("${named.nameOfAssetsClass}: ${configFile.nameOfAssetsClass}\n")
            out.write("${named.nameOfAssetsFile}: ${configFile.nameOfAssetsFile}\n")

            out.newLine()
            out.write("# Output fonts config\n")
            out.write("#${named.nameOfFontsClass}: ${configFile.nameOfFontsClass}\n")
            out.write("#${named.nameOfFontsFile}: ${configFile.nameOfFontsFile}\n")
        }
    }

    fun readConfigFile(): ConfigFileValues {
        println("Reading assets manager config...")

        //Open a default file
        val file = File("$path/$FILE_CONFIG_NAME")
        val yaml = Yaml()
        val inputStream: InputStream = file.inputStream()
        val obj = yaml.load<Map<String, Any>>(inputStream)



        val namedFile = ConfigFileKey()
        val defaultValues = ConfigFileValues()
        return ConfigFileValues(
                assetsFolder = obj[namedFile.assetsFolder] as String? ?: defaultValues.assetsFolder,
                folderOutput = obj[namedFile.defaultFolder] as String? ?: defaultValues.folderOutput,
                ignoreFontsConfig = obj[namedFile.ignoreFonts] as Boolean? ?: defaultValues.ignoreFontsConfig,
                postfixAssets = obj[namedFile.postfixAssets] as String? ?: defaultValues.postfixAssets,
                pubspecStrategy = obj[namedFile.pubspecStrategy] as String? ?: defaultValues.pubspecStrategy,
                pubspecUpdateFonts = obj[namedFile.pubspecUpdateFonts] as Boolean? ?: defaultValues.pubspecUpdateFonts,
                nameOfAssetsClass = obj[namedFile.nameOfAssetsClass] as String? ?: defaultValues.nameOfAssetsClass,
                nameOfAssetsFile = obj[namedFile.nameOfAssetsFile] as String? ?: defaultValues.nameOfAssetsFile,
                nameOfFontsClass = obj[namedFile.nameOfFontsClass] as String? ?: defaultValues.nameOfFontsClass,
                nameOfFontsFile = obj[namedFile.nameOfFontsFile] as String? ?: defaultValues.nameOfFontsFile,
                fontConfigNull = obj[namedFile.nameOfFontsClass] == null
        )

    }

    fun readAssets(configFile: ConfigFileValues = ConfigFileValues()): ArrayList<AssetsFiles> {
        val finalPath = "$path/${configFile.assetsFolder}"
        val list: ArrayList<AssetsFiles> = ArrayList()
        File(finalPath).walk().forEach {
            if(it.name == MAC_OS_DS_STORE) return@forEach
            list.add(
                    AssetsFiles(
                            file = it,
                            postfix = configFile.postfixAssets,
                            assetsFolder = configFile.assetsFolder,
                    )
            )
        }

        return list
    }

    fun createAssetsOutPut(configFile: ConfigFileValues, assetsFiles: ArrayList<AssetsFiles>) {
        println("Creating output file...")
        // Create a output dir
        var finalPath = checkOrAddLibToPath()
        finalPath = "$finalPath${configFile.folderOutput}"
        val dir = File(finalPath)
        dir.mkdirs()


        // Create a output file
        val assetsDartFile = File("$finalPath/${configFile.nameOfAssetsFile!!}.dart")
        createFileFromZero(assetsDartFile, assetsFiles.filter { it.isAsset() }, configFile.nameOfAssetsClass!!)

        if (!configFile.ignoreFontsConfig!! && !configFile.fontConfigNull) {
            var fontsDartFile: File? = null
            if(configFile.nameOfAssetsFile!! != configFile.nameOfFontsFile){
                fontsDartFile = File("$finalPath/${configFile.nameOfFontsFile!!}.dart")
            }
            appendInFile(fontsDartFile
                    ?: assetsDartFile, assetsFiles.filter { it.isFont() }, configFile.nameOfFontsClass!!)
        }
    }

    private fun checkOrAddLibToPath(): String {
        var finalPath = path
        if (!finalPath.contains("lib")) {
            if (getSlashCharacter(finalPath)) finalPath += "/"
            finalPath += "lib"
        }
        if (getSlashCharacter(finalPath)) finalPath += "/"

        return finalPath
    }

    private fun createFileFromZero(file: File, assetsFiles: List<AssetsFiles>, nameDartClass: String){
        file.bufferedWriter().use { out ->
            out.write("class ${nameDartClass.capitalize()} {\n")
            assetsFiles.forEach {
                out.write("$defValueDart ${it.outputName} $middleValueDart${it.path}$endValueDart")

            }
            out.write("}\n\n")
        }
    }

    private fun appendInFile(file: File, assetsFiles: List<AssetsFiles>, nameDartClass: String){
        val fw = FileWriter(file, true)
        fw.write("class ${nameDartClass.capitalize()} {\n")
        assetsFiles.forEach {
            if (it.isFont())
                fw.write("$defValueDart ${it.outputName} $middleValueDart${it.path}$endValueDart")
        }
        fw.write("}\n\n")
        fw.close()
    }

    private fun getSlashCharacter(path: String): Boolean {
        return path[path.lastIndex] != '/'
    }
}