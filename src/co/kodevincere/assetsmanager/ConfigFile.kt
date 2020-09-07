package co.kodevincere.assetsmanager


data class ConfigFileValues
constructor(
        var assetsFolder: String? = "assets",
        var defaultFolder: String? = "lib/constants",
        var ignoreFonts: Boolean? = true,
        var nameOfAssetsClass: String? = "Assets",
        var nameOfAssetsFile: String? = "assets_manager",
        var mergeFontsInAssetsClass: Boolean? = false,
        var nameOfFontsClass: String? = "Fonts",
        var nameOfFontsFile: String? = "assets_manager",
        var mergeInSingleFile: Boolean? = true,
)

data class ConfigFileKey constructor(
        var assetsFolder: String = "assets-folder",
        var defaultFolder: String = "folder-output",
        var ignoreFonts: String = "ignore-fonts",
        var nameOfAssetsClass: String = "name-assets-class",
        var nameOfAssetsFile: String = "name-assets-file",
        var mergeFontsInAssetsClass: String = "merge-fonts",
        var nameOfFontsClass: String = "name-fonts-class",
        var nameOfFontsFile: String = "name-fonts-file",
        var mergeInSingleFile: String = "single-file",
)

