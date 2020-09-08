package co.kodevincere.assetsmanager

import java.io.File

class AssetsFiles(file: File, postfix: String?, assetsFolder: String?){
    val name: String
    val type: AssetsFileType
    val parentName: String?
    val outputName: String
    val extension = file.extension
    val path: String

    init {
        parentName = file.parentFile?.name
        val isFont = file.extension == "ttf" || file.extension == "otf" || (parentName ?: "") == "fonts" || (parentName ?: "") == "Fonts"

        name = file.nameWithoutExtension
        type = when{
            file.isDirectory -> AssetsFileType.FOLDER
            file.isFile && isFont -> AssetsFileType.FONT
            else -> AssetsFileType.ASSET
        }
        outputName = formatName(postfix)
        path = formatPath(file.path, assetsFolder)
    }

    private fun formatName(postfix: String?) : String{
        var postfixValue = ""

        when {
            postfix.equals(PostfixStrategy.EXTENSION.strategy) -> {
                postfixValue = "_$extension"
            }
            postfix.equals(PostfixStrategy.FOLDER.strategy) -> {
                postfixValue = "_${(parentName ?: "")}"
            }
            postfix!!.isNotEmpty() -> {
                postfixValue = "_$postfix"
            }
        }


        return (name + postfixValue).toLowerCase()
    }

    private fun formatPath(path: String, assetsFolder: String?): String {
        val finalPath = path.split(assetsFolder!!)
        return "$assetsFolder${finalPath[1]}"
    }

    fun isFont(): Boolean {
        return type == AssetsFileType.FONT
    }

    fun isAsset(): Boolean {
        return type == AssetsFileType.ASSET
    }

    fun isDirectory(): Boolean {
        return type == AssetsFileType.FOLDER
    }

    enum class AssetsFileType{
        FOLDER,
        FONT,
        ASSET
    }
}