package com.js_ku.zentao.component;

import com.intellij.ide.util.PropertiesComponent;
import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.options.ShowSettingsUtil;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.text.StringUtil;
import com.js_ku.zentao.api.ZenTaoApi;
import com.js_ku.zentao.api.model.ZenTaoConstant;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;


/**
 * Created by da-li on 2018/2/13.
 */
public class IdeaZenTaoConfigurable implements Configurable {

	private JPanel rootPanel;
	private JTextField zentaoAccount;
	private JTextField zentaoPsd;
	private JTextField zentaoUrl;
	private JButton loginBtn;
	private boolean isLogin;
	private PropertiesComponent prop = PropertiesComponent.getInstance();

	@Nullable
	@Override
	public String getHelpTopic() {
		return null;
	}

	@Nls
	@Override
	public String getDisplayName() {
		return "ZenTao Config";
	}

	@Nullable
	@Override
	public JComponent createComponent() {
		loginBtn.addActionListener(e -> {
			if (ZenTaoApi.login()){
				isLogin = true;
			} else {
				notice("请检查输入项");
			}
		});
		return rootPanel;
	}

	@Override
	public boolean isModified() {

		boolean allHaveContent = StringUtil.isNotEmpty(zentaoUrl.getText()) &&
		StringUtil.isNotEmpty(zentaoAccount.getText()) &&
		StringUtil.isNotEmpty(zentaoPsd.getText());

		loginBtn.setEnabled(allHaveContent);

		if (allHaveContent){
			prop.setValue(ZenTaoConstant.ZEN_TAO_URL,zentaoUrl.getText());
			prop.setValue(ZenTaoConstant.ZEN_TAO_ACCOUNT,zentaoAccount.getText());
			prop.setValue(ZenTaoConstant.ZEN_TAO_PSD,zentaoPsd.getText());
		}
		return !isLogin;
	}

	@Override
	public void apply() throws ConfigurationException {

		if (ZenTaoApi.login()){
			isLogin = true;
			prop.setValue(ZenTaoConstant.ZEN_TAO_URL,zentaoUrl.getText());
			prop.setValue(ZenTaoConstant.ZEN_TAO_ACCOUNT,zentaoAccount.getText());
			prop.setValue(ZenTaoConstant.ZEN_TAO_PSD,zentaoPsd.getText());
		} else {
			notice("请检查输入项");
		}

	}

	@Override
	public void disposeUIResources() {

	}
	public static void show(@NotNull final Project project) {
		ShowSettingsUtil.getInstance().showSettingsDialog(project,  IdeaZenTaoConfigurable.class);
	}
	@Override
	public void reset() {
		zentaoUrl.setText(prop.getValue(ZenTaoConstant.ZEN_TAO_URL));
		zentaoAccount.setText(prop.getValue(ZenTaoConstant.ZEN_TAO_ACCOUNT));
		zentaoPsd.setText(prop.getValue(ZenTaoConstant.ZEN_TAO_PSD));
	}

	public void notice(String message) {

		JPanel panel = new JPanel();
		panel.add(new JLabel(message));

		JOptionPane jop = new JOptionPane();
		JDialog dialog = jop.createDialog("提示");
		dialog.setSize(100, 200);
		dialog.setContentPane(panel);
		dialog.setVisible(true);

	}




}
