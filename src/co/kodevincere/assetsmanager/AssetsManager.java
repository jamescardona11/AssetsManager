package co.kodevincere.assetsmanager;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import org.jetbrains.annotations.NotNull;

public class AssetsManager extends AnAction {
    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {

    }

    @Override
    public boolean isDumbAware() {
        return false;
    }
}
