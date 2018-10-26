package com.js_ku.zentao.component;

import com.intellij.ide.BrowserUtil;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.SimpleToolWindowPanel;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.js_ku.zentao.api.ZenTaoApi;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by da-li on 2018/2/9.
 */
public class IdeaZenTaoToolWindow implements ToolWindowFactory {

	@Override
	public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {

		JPanel p1 = new JPanel();
		p1.setLayout(new GridLayout(0, 1));
		p1.setForeground (Color.BLUE);




		ZenTaoApi.getBugs().getData().getBugs().forEach(bug->{

			JLabel jLabel = new JLabel(bug.getTitle(),SwingConstants.CENTER);
			JLabel jLabel1 = new JLabel(bug.getTitle(),SwingConstants.CENTER);
			jLabel.addMouseListener(new MouseAdapter()   {

				public void mouseClicked(MouseEvent e)
				{
					if(e.getClickCount() >= 2){
						BrowserUtil.browse(ZenTaoApi.getBugUrlById(bug.getId()));
					}

				}
			});
			p1.add(jLabel);
			p1.add(jLabel1);
		});
		toolWindow.getComponent().add(p1);

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
