package com.js_ku.zentao.component;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import org.jetbrains.annotations.NotNull;

/**
 * Created by da-li on 2018/2/9.
 */
public class MyToolWindowFactory implements ToolWindowFactory {
	@Override
	public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {
//		project
		toolWindow.setTitle("aaaabb");
//		toolWindow.getComponent().add(new ToolWindowGui().getMainPanel());

//		toolWindow.
	}

	@Override
	public void init(ToolWindow window) {
		window.setTitle("aaa");
		window.setShowStripeButton(true);
//		window.getComponent().add(new ToolWindowGui().getMainPanel());

	}

	@Override
	public boolean shouldBeAvailable(@NotNull Project project) {
		return false;
	}

	@Override
	public boolean isDoNotActivateOnStart() {
		return false;
	}
}
