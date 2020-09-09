package co.kodevincere.assetsmanager

import java.io.File

class AssetsFiles(file: File, postfix: String?, assetsFolder: String?){
    private val name: String
    private val type: AssetsFileType
    private val parentName: String?
    val outputName: String
    private val extension = file.extension
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

    fun isDirectoryFonts(): Boolean{
        return (name ?: "") == "fonts" || (name ?: "") == "Fonts"
    }

    fun getAssetPrint(): String {
        var slash = ""
        if(isDirectory()) slash = "/"
        return "    - $path$slash"
    }

    enum class AssetsFileType{
        FOLDER,
        FONT,
        ASSET
    }
}