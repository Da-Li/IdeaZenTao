package com.js_ku.zentao.component;

import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.ui.StripeTable;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import javax.swing.table.TableModel;

/**
 * Created by da-li on 2018/2/22.
 */
public class ZenTaoConfigurable implements Configurable {
	@Nls
	@Override
	public String getDisplayName() {
		return "ZenTao Config";
	}

	@Nullable
	@Override
	public JComponent createComponent() {
		return null;
	}

	@Override
	public boolean isModified() {
		return false;
	}

	@Override
	public void apply() throws ConfigurationException {

	}

	private static final class ZenTaoSettingsPanel extends JPanel {

	}
	private static final class ZenTaoConflictsTable extends StripeTable {

		public ZenTaoConflictsTable(TableModel model) {
			super(model);

		}
	}
}
