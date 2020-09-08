package co.kodevincere.assetsmanager

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.PlatformDataKeys
import org.yaml.snakeyaml.Yaml
import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.io.InputStream
import java.util.*
import kotlin.collections.ArrayList

const val PUBSPEC_NAME = "pubspec.yaml"

class AssetsManager : AnAction() {
    override fun actionPerformed(e: AnActionEvent) {
        println("Updating assets manager...")
        val project = e.getData(PlatformDataKeys.PROJECT)
        val path = Objects.requireNonNull(project)!!.basePath
        val fileManager = FileManager(path!!)

        if(!fileManager.existsDefaultConfig()) {
            fileManager.createDefaultConfigFile()
        }

        val configFile = fileManager.readConfigFile()
        val assetsFiles = fileManager.readAssets(configFile)

        fileManager.createAssetsOutPut(
            configFile = configFile,
            assetsFiles = assetsFiles
        )

        readAndWritePubspec(
            path = path,
            configFile = configFile,
            assetsFiles = assetsFiles
        )
    }

    private fun readAndWritePubspec(path: String, configFile: ConfigFileValues, assetsFiles: ArrayList<AssetsFiles>){
        println("Updating assets manager...")
        val pubspec = File(path, PUBSPEC_NAME)
        val containsKey = hasAssetsAndFontsKey(pubspec)
        val outPutLines = ArrayList<String>()


        val reader = BufferedReader(FileReader(pubspec))
        var line = reader.readLine()
        while (line != null){
            outPutLines.add(line)

            if(containsKey[0]){ //Contains 'assets' key in pubspec
                if(line.matches(Regex("^ {2}assets:"))){ // start to delete assets
                    line = reader.readLine()
                    while(line.matches(Regex("^ {2,}- .*")) || line.matches(Regex("^\\S*$"))){
                        line = reader.readLine()
                    }
                    //add assets to pubspec
                    outPutLines.addAll(
                        addNewAssetsToPubspec(
                            strategy = configFile.pubspecStrategy!!,
                            assetsFiles = assetsFiles
                        )
                    )
                }
            }else{
                if(line.matches(Regex("^ {2}uses-material-design: true"))){
                    outPutLines.add("  assets:")
                    //add assets to pubspec
                    outPutLines.addAll(
                        addNewAssetsToPubspec(
                            strategy = configFile.pubspecStrategy!!,
                            assetsFiles = assetsFiles
                        )
                    )
                }
            }

            line = reader.readLine()
        }

        pubspec.bufferedWriter().use { out->
            outPutLines.forEach {
                out.write(it)
                out.newLine()
            }
        }

    }


    private fun addNewAssetsToPubspec(strategy: String, assetsFiles: ArrayList<AssetsFiles>) : ArrayList<String>{
        val outPutLines = ArrayList<String>()
        if(strategy == PubspecStrategy.ASSET.strategy){
            assetsFiles.filter { it.isAsset() }.forEach { af->
                outPutLines.add(af.getAssetPrint())
            }
        }else{
            assetsFiles.filter { it.isDirectory() && !it.isDirectoryFonts() }.forEach { af->
                outPutLines.add(af.getAssetPrint())
            }
        }

        return outPutLines

    }

    private fun hasAssetsAndFontsKey(pubspec: File): Array<Boolean>{
        var assets = false
        var fonts = false

        val yaml = Yaml()
        val inputStream: InputStream = pubspec.inputStream()
        val obj = yaml.load<Map<String, Any>>(inputStream)

        if(obj.containsKey("flutter")){
            val flutterParent = obj["flutter"] as Map<*, *>
            assets = flutterParent.containsKey("assets")
            fonts = flutterParent.containsKey("fonts")
        }

        return arrayOf(assets, fonts)
    }
}