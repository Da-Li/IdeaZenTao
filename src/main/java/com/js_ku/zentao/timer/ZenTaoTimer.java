package com.js_ku.zentao.timer;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.util.IconLoader;
import com.js_ku.zentao.IdeaZenTao;
import com.js_ku.zentao.api.ZenTaoApi;
import com.js_ku.zentao.api.model.ZenTaoConstant;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by da-li on 2018/2/26.
 */
public class ZenTaoTimer {

	private static long ONE_SECOND = 1000;

	private volatile long value;

	private volatile long creationValue;

	private volatile boolean paused = !IdeaZenTao.isEnabled();

	private volatile boolean finished;

	private Timer timer;



	public ZenTaoTimer() {

	}

	public void startPause(AnActionEvent e){
		if (!paused){
			TimerTask task = new TimerTask() {
				@Override
				public void run() {
					Integer bugSize = ZenTaoApi.getBugs();

					e.getPresentation().setText(String.format(ZenTaoConstant.ZEN_TAO_BUG_POINT,bugSize));
					e.getPresentation().setIcon(IconLoader.getIcon("/icons/number_"+bugSize+".png"));

				}};
			timer = new Timer();
			timer.schedule(task, ONE_SECOND, 1000);
		} else {
			timer.cancel();
		}
		paused = !paused;
	}


	public void reset(){
		paused = true;
		value = creationValue;
		if (timer != null) timer.cancel();
	}



}

