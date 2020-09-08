package co.kodevincere.assetsmanager

class AssetsFiles(val name: String, val dir: String, path: String, postfix: String?, folderName: String ?, val isFont: Boolean) {
    val outputName: String
    val path: String

    init {
        this.path = formatFolder(path, folderName)
        this.outputName = formatName(postfix)
    }

    private fun formatName(postfix: String?) : String{
        var postfixValue = ""

        when {
            postfix.equals(PostfixStrategy.EXTENSION.strategy) -> {
                postfixValue = "_${path.split(".")[1]}"
            }
            postfix.equals(PostfixStrategy.FOLDER.strategy) -> {
                postfixValue = "_$dir"
            }
            postfix!!.isNotEmpty() -> {
                postfixValue = "_$postfix"
            }
        }


        return (name + postfixValue).toLowerCase()
    }

    private fun formatFolder(path: String, folderName: String ?): String {
        val finalPath = path.split(folderName!!)
        return "$folderName${finalPath[1]}"
    }

    override fun toString(): String {
        return "AssetsFiles(dir='$dir', name='$name', outputName='$outputName', path='$path', isFont=$isFont)"
    }


}