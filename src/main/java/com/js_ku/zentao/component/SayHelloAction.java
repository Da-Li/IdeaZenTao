package com.js_ku.zentao.component;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.application.Application;
import com.intellij.openapi.application.ApplicationManager;
import com.js_ku.zentao.timer.ZenTaoTimer;

/**
 * Created by da-li on 2018/2/8.
 */
public class SayHelloAction extends AnAction {

	@Override
	public void actionPerformed(AnActionEvent e) {
		Application application = ApplicationManager.getApplication();
		MyComponent myComponent = application.getComponent(MyComponent.class);
		myComponent.sayHello();
		new ZenTaoTimer().startPause(e);
	}

	@Override
	public void setDefaultIcon(boolean isDefaultIconSet) {
		super.setDefaultIcon(isDefaultIconSet);

	}

	@Override
	public void update(AnActionEvent e) {
		super.update(e);
	}
}
