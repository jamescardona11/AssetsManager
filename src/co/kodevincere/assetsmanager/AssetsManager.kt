package co.kodevincere.assetsmanager

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent

class AssetsManager : AnAction() {
    override fun actionPerformed(e: AnActionEvent) {
        println("Updating assets manager...")
    }
}