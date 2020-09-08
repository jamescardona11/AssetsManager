package co.kodevincere.assetsmanager


data class ConfigFileValues
constructor(//9
        var assetsFolder: String? = "assets",
        var folderOutput: String? = "constants",
        var ignoreFontsConfig: Boolean? = true,
        var postfixAssets: String? = "", //you can choose: folder_name, extension_name, empty value or provide personal name
        var pubspecStrategy: String? = "folder", //you can choose: folder or files
        var pubspecUpdateFonts: Boolean? = true,
        var nameOfAssetsClass: String? = "Assets",
        var nameOfAssetsFile: String? = "assets_manager",
        var nameOfFontsClass: String? = "Fonts",
        var nameOfFontsFile: String? = "assets_manager",
        var fontConfigNull: Boolean = true
)

data class ConfigFileKey constructor(
        var assetsFolder: String = "assets_folder",
        var defaultFolder: String = "folder_output",
        var ignoreFonts: String = "ignore_fonts_config",
        var postfixAssets: String = "post_fix",
        var pubspecStrategy: String? = "pubspec_strategy",
        var pubspecUpdateFonts: String? = "update_fonts_pubspec",
        var nameOfAssetsClass: String = "name_assets_class",
        var nameOfAssetsFile: String = "name_assets_file",
        var nameOfFontsClass: String = "name_fonts_class",
        var nameOfFontsFile: String = "name_fonts_file",

)

enum class PostfixStrategy(val strategy: String) {
    EXTENSION("extension_name"),
    FOLDER("folder_name"),

}

enum class PubspecStrategy(val strategy: String) {
    FOLDER("folder"),
    FILES("files"),

}