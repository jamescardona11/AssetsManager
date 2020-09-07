package co.kodevincere.assetsmanager


data class ConfigFileValues
constructor(//9
        var assetsFolder: String? = "assets",
        var defaultFolder: String? = "lib/constants",
        var ignoreFonts: Boolean? = true,
        var postfixAssets: String? = "", //you can choose: folder_name, extension_name, empty value or provide personal name
        var nameOfAssetsClass: String? = "Assets",
        var nameOfAssetsFile: String? = "assets_manager",
        var nameOfFontsClass: String? = "Fonts",
        var nameOfFontsFile: String? = "assets_manager",
        var mergeFontsInAssetsClass: Boolean? = false,
)

data class ConfigFileKey constructor(
        var assetsFolder: String = "assets_folder",
        var defaultFolder: String = "folder_output",
        var ignoreFonts: String = "ignore_fonts",
        var postfixAssets: String = "post_fix",
        var nameOfAssetsClass: String = "name_assets_class",
        var nameOfAssetsFile: String = "name_assets_file",
        var nameOfFontsClass: String = "name_fonts_class",
        var nameOfFontsFile: String = "name_fonts_file",
        var mergeFontsInAssetsClass: String = "merge_fonts",
)

enum class PostfixStrategy(val strategy: String) {
    EXTENSION("extension_name"),
    FOLDER("folder_name"),

}