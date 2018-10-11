package com.js_ku.zentao.component;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.js_ku.zentao.api.ZenTaoApi;
import com.js_ku.zentao.timer.ZenTaoTimer;
import icons.IdeaZenTaoIcons;

/**
 * Created by da-li on 2018/2/8.
 */
public class ShowBugNumberAction extends AnAction  {




	@Override
	public void actionPerformed(AnActionEvent e) {
		//未登录 跳转到设置 ，已经登录暂停开始
		if (!ZenTaoApi.login()){
			IdeaZenTaoConfigurable.show(e.getProject());
		} else {
			boolean paused = ZenTaoTimer.paused;
			ZenTaoTimer.paused = !paused;
			if (!ZenTaoTimer.paused){
				ZenTaoTimer.getBugsChangeIcon(e);
			} else {
				ZenTaoTimer.cancelTimer(e);
			}

		}


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

	public static void paused(AnActionEvent e){
		e.getPresentation().setIcon(IdeaZenTaoIcons.PAUSED);
		e.getPresentation().setText("已暂停");
	}


}
