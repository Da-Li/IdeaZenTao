package com.js_ku.zentao.component;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.js_ku.zentao.timer.ZenTaoTimer;

/**
 * Created by da-li on 2018/2/8.
 */
public class ShowBugNumberAction extends AnAction  {




	@Override
	public void actionPerformed(AnActionEvent e) {
		//TODO 跳转到设置 || 设置弹窗 || 开始暂停功能

	}

	@Override
	public void setDefaultIcon(boolean isDefaultIconSet) {

		super.setDefaultIcon(isDefaultIconSet);

	}

	@Override
	public void update(AnActionEvent e) {

		new ZenTaoTimer().start(e);
		super.update(e);
	}


}
