package co.kodevincere.assetsmanager

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import org.yaml.snakeyaml.Yaml
import java.io.File
import java.io.InputStream

const val PUBSPEC_NAME = "pubspec.yaml"

class AssetsManager : AnAction() {
    override fun actionPerformed(e: AnActionEvent) {
        println("Updating assets manager...")
    }

    fun readPubspec(path: String){
        println("Updating assets manager...")
        val pubspec = File(path, PUBSPEC_NAME)


        val yaml = Yaml()
        val inputStream: InputStream = pubspec.inputStream()
        val obj = yaml.load<Map<String, Any>>(inputStream)

        if(obj.containsKey("flutter")){
            val assetsParent = obj["flutter"] as Map<*, *>
            if(assetsParent.containsKey("assets")){
                val assetsKey = assetsParent["assets"]

                println(assetsKey)

                println(assetsKey)
            }else{

            }


        }else{
            println("The $PUBSPEC_NAME has errors")
        }

    }
}