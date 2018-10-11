package com.js_ku.zentao.component;

import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.ui.StripeTable;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import javax.swing.table.TableModel;

/**
 * Created by da-li on 2018/2/22.
 */
public class ZenTaoConfigurable implements Configurable {
	@Nullable
	public String getHelpTopic() {
		return null;
	}

	public boolean isModified(@NotNull JTextField textField, @NotNull String value) {
		return false;
	}


	public boolean isModified(@NotNull JToggleButton toggleButton, boolean value) {
		return false;
	}

	@Nls
	public String getDisplayName() {
		return "ZenTao Config";
	}

	@Nullable
	public JComponent createComponent() {
		return null;
	}

	public boolean isModified() {
		return false;
	}

	public void apply() throws ConfigurationException {

	}

	private static final class ZenTaoSettingsPanel extends JPanel {

	}
	private static final class ZenTaoConflictsTable extends StripeTable {

		public ZenTaoConflictsTable(TableModel model) {
			super(model);

		}
	}

	@Override
	public void reset() {

	}

	@Override
	public void disposeUIResources() {

	}
}
