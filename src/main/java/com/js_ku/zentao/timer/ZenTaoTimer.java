package com.js_ku.zentao.timer;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.js_ku.zentao.IdeaZenTao;
import com.js_ku.zentao.api.ZenTaoApi;
import com.js_ku.zentao.api.model.ZenTaoConstant;
import icons.IdeaZenTaoIcons;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by da-li on 2018/2/26.
 */
public class ZenTaoTimer {

	private static long ONE_SECOND = 1000;



	private volatile boolean paused = !IdeaZenTao.isEnabled();


	private Timer timer;



	public ZenTaoTimer() {

	}

	public void start(AnActionEvent e){


		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				if (!paused && IdeaZenTao.isLogin()){
					Integer bugSize = ZenTaoApi.getSizeBugs();
					e.getPresentation().setText(String.format(ZenTaoConstant.ZEN_TAO_BUG_POINT,bugSize));
					e.getPresentation().setIcon(IdeaZenTaoIcons.getIconByBugSize(bugSize));
				} else {
					e.getPresentation().setIcon(IdeaZenTaoIcons.NO_LOGIN);
				}

			}};
		timer = new Timer();
		timer.schedule(task, ONE_SECOND, 10000);
	}

}

