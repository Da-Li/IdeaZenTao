package com.js_ku.zentao.timer;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.js_ku.zentao.IdeaZenTao;
import com.js_ku.zentao.api.ZenTaoApi;
import com.js_ku.zentao.api.model.ZenTaoConstant;
import com.js_ku.zentao.component.ShowBugNumberAction;
import icons.IdeaZenTaoIcons;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by da-li on 2018/2/26.
 */
public class ZenTaoTimer {

	private static long ONE_SECOND = 1000;



	public static volatile  boolean paused = !IdeaZenTao.isEnabled();


	private Timer timer;
	private TimerTask task;



	public ZenTaoTimer() {

	}

	public void start(AnActionEvent e){


		if (paused){
			return;
		}

		 task = new TimerTask() {
			@Override
			public void run() {
				if (paused){
					cancelTimer(e);

				} else if (IdeaZenTao.isLogin()){
					Integer bugSize = ZenTaoApi.getSizeBugs();
					e.getPresentation().setText(String.format(ZenTaoConstant.ZEN_TAO_BUG_POINT,bugSize));
					e.getPresentation().setIcon(IdeaZenTaoIcons.getIconByBugSize(bugSize));
				} else {
					e.getPresentation().setIcon(IdeaZenTaoIcons.NO_LOGIN);
				}

			}};
		timer = new Timer();
		timer.schedule(task, ONE_SECOND*100, ONE_SECOND);
	}

	public void cancelTimer(AnActionEvent e){
	    if (timer != null){
            timer.cancel();
        }
        if (task != null){
			task.cancel();
        }
		ShowBugNumberAction.paused(e);
	}
}

