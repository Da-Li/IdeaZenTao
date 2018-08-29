package com.js_ku.zentao.component;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.ToggleAction;
import com.intellij.openapi.project.DumbAware;
import com.js_ku.zentao.IdeaZenTao;

/**
 * tools menu 开关
 */
public class ZenTaoPluginToggleAction extends ToggleAction implements DumbAware {


    @Override
    public boolean isSelected(AnActionEvent e) {
        return IdeaZenTao.isEnabled();
    }

    @Override
    public void setSelected(AnActionEvent e, boolean state) {
        IdeaZenTao.setEnabled(state);
    }
    
}
