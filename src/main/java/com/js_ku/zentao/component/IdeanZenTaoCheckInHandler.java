package com.js_ku.zentao.component;

import com.intellij.openapi.Disposable;
import com.intellij.openapi.vcs.checkin.CheckinHandler;
import com.intellij.openapi.vcs.ui.RefreshableOnComponent;
import com.intellij.ui.NonFocusableCheckBox;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;

public class IdeanZenTaoCheckInHandler extends CheckinHandler {


    @Nullable
    @Override
    public RefreshableOnComponent getAfterCheckinConfigurationPanel(Disposable parentDisposable) {

        return new MyRefreshableOnComponent(new NonFocusableCheckBox("Fix ZenTao Bug"));
    }


    private class MyRefreshableOnComponent implements RefreshableOnComponent {
        private final JCheckBox checkBox;

        private MyRefreshableOnComponent(JCheckBox checkBox) {
            this.checkBox = checkBox;
        }

        @Override
        public JComponent getComponent() {
            JPanel panel = new JPanel(new BorderLayout());
            panel.add(checkBox);
            return panel;
        }

        @Override
        public void refresh() {
            // nothing to do
        }

        @Override
        public void saveState() {
//            PropertiesComponent.getInstance(project).setValue(ACTIVATED_OPTION_NAME, Boolean.toString(checkBox.isSelected()));
        }

        @Override
        public void restoreState() {
//            PropertiesComponent props = PropertiesComponent.getInstance(project);
//            checkBox.setSelected(props.getBoolean(ACTIVATED_OPTION_NAME, globalSettings.isAutoTrigger()));
        }
    }
}
