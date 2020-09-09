# AssetsManager   <img src="preview/assets_manager_icon.svg" width="32"/>
The easy way to manage your assets Flutter project.

[GitHub](https://github.com/jamescardona11/AssetsManager)

## TABLE OF CONTENT
- [Features](#Features)
- [Getting started](#Getting started)
- [Change notes](#Change notes)
- [Config file](#Config file)
- [Example](#Example)

##Features
- Update assets declaration in pubspec.yaml automatically.
- Generate a file whit assets definition in dart.
- Modify config for output and input data.

##Getting started
* Open your Flutter project.
* You may be create manual your assets config or plugin will create the default file
* Click the plugin in the Toolbar.
* Now you'll see the pubspec.yaml file has been updated and a file whit definition in dart.


##Change notes
####1.0.0 : Implement basic functions.

- Scan asset files under your assets directory, add asset declaration in pubspec.yaml.
- Generate a file in dart, which contains string-type asset definition.

     
##Config file
Default name: **assets_manager_config.yaml**
``` yaml
name: AssetsManager Config
description: A Config to create a better experience with plugin


# Input assets directory
assets_folder: assets

# Output config
folder_output: constants
post_fix: 
pubspec_strategy: folder

# Output assets config
name_assets_class: Assets
name_assets_file: assets_manager

```
Explanation
``` yaml
assets_folder: The directory where you save your assets files, images, videos, etc

folder_output: The directory for output file this folder, must be behid \lib

post_fix: You can use a list of this options for generate at end of var in dart
            - folder_name (Automatic)
            - extension_file (Automatic)
            - empty (Default)
            - provide personal postfix

pubspec_strategy: The 'assets values' in the pubspec generate like one these strategy
            - folder: Only list folder (Default)
            - file: List all files

name_assets_class: Name of class output dart class
name_assets_file: Name of file for output in dart

```


###Output

##File in dart
``` dart
class Assets {
	static const String auth_bottom = "assets/auth/auth_bottom.png";
	static const String forgot_password_header = "assets/auth/forgot_password_header.svg";
	static const String sign_up_header = "assets/auth/sign_up_header.svg";
	static const String auth_top = "assets/auth/auth_top.png";
	static const String login_header = "assets/auth/login_header.svg";
}
```




