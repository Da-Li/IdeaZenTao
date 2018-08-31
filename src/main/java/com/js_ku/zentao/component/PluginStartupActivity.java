package com.js_ku.zentao.component;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.startup.StartupActivity;
import org.jetbrains.annotations.NotNull;

public class PluginStartupActivity implements StartupActivity {

    @Override
    public void runActivity(@NotNull Project project) {
        System.out.println("init project");

    }
}
