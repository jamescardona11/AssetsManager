package co.kodevincere.assetsmanager

data class ConfigFile
constructor(
        var defaultFolder: String = "/constants",
        var ignoreFonts: Boolean = true,
        var nameOfAssetsClass: String = "Assets",
        var nameOfAssetsFile: String = "assets_manager",
        var mergeFontsInAssetsClass: Boolean = false,
        var nameOfFontsClass: String = "Fonts",
        var nameOfFontsFile: String = "assets_manager",
        var mergeInSingleFile: Boolean = true,
)

data class ConfigFileNamed constructor(
        var defaultFolder: String = "folder",
        var ignoreFonts: String = "ignore-fonts",
        var nameOfAssetsClass: String = "name-assets-class",
        var nameOfAssetsFile: String = "name-assets-file",
        var mergeFontsInAssetsClass: String = "merge-fonts",
        var nameOfFontsClass: String = "name-fonts-class",
        var nameOfFontsFile: String = "name-fonts-file",
        var mergeInSingleFile: String = "single-file",
)