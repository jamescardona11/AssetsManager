package co.kodevincere.assetsmanager

class AssetsFiles(name: String, val dir: String, path: String, postfix: String?, folderName: String ?) {
    val filename: String = name
    val outputName: String
    val path: String

    init {
        this.path = formatFolder(path, folderName)
        this.outputName = formatName(name, postfix)
    }

    private fun formatName(name: String, postfix: String?) : String{
        val finalName = name.replace("-", "_").split(".")

        var postfixValue = ""

        when {
            postfix.equals(PostfixStrategy.EXTENSION.strategy) -> {
                postfixValue = "_${finalName[1]}"
            }
            postfix.equals(PostfixStrategy.FOLDER.strategy) -> {
                postfixValue = "_$dir"
            }
            postfix!!.isNotEmpty() -> {
                postfixValue = "_$postfix"
            }
        }


        return (finalName[0] + postfixValue).toLowerCase()
    }

    private fun formatFolder(path: String, folderName: String ?): String {
        val finalPath = path.split(folderName!!)
        return "$folderName${finalPath[1]}"
    }

    override fun toString(): String {
        return "AssetsFiles(dir='$dir', path='$path', filename='$filename', outputName='$outputName')"
    }


}