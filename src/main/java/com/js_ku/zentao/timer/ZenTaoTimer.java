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


	private static Timer timer;
	private static TimerTask task;



	public ZenTaoTimer() {

	}

	public static void getBugsChangeIcon(AnActionEvent e){
		if (paused){
			return;
		}
		if(IdeaZenTao.isLogin()){
			Integer bugSize = ZenTaoApi.getSizeBugs();
			e.getPresentation().setText(String.format(ZenTaoConstant.ZEN_TAO_BUG_POINT,bugSize));
			e.getPresentation().setIcon(IdeaZenTaoIcons.getIconByBugSize(bugSize));
		} else {
			e.getPresentation().setIcon(IdeaZenTaoIcons.NO_LOGIN);
		}
	}

	public void start(AnActionEvent e){


		if (paused || timer != null){
			return;
		}

		 task = new TimerTask() {
			@Override
			public void run() {

				getBugsChangeIcon(e);

			}};
		timer = new Timer();
		timer.schedule(task, ONE_SECOND, ONE_SECOND*100);
	}

	public static void cancelTimer(AnActionEvent e){
	    if (timer != null){
            timer.cancel();
			timer = null;
        }
        if (task != null){
			task.cancel();
			task = null;
        }
		ShowBugNumberAction.paused(e);
	}
}

