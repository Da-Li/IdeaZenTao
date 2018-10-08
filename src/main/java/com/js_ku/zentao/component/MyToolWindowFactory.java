package com.js_ku.zentao.component;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.SimpleToolWindowPanel;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import org.jetbrains.annotations.NotNull;

/**
 * Created by da-li on 2018/2/9.
 */
public class MyToolWindowFactory implements ToolWindowFactory {

	@Override
	public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {

		toolWindow.setTitle("aaaabb");

	}






	public void init(ToolWindow window) {
		window.setShowStripeButton(true);

	}

	public boolean shouldBeAvailable(@NotNull Project project) {

		return false;
	}

	public boolean isDoNotActivateOnStart() {
		return false;
	}

	public class ToolWindowPanel extends SimpleToolWindowPanel {

		public ToolWindowPanel(boolean vertical) {
			super(vertical);
		}

		public ToolWindowPanel(boolean vertical, boolean borderless) {
			super(vertical, borderless);
		}
	}



}
