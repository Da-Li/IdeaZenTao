package com.js_ku.zentao.component;

import com.intellij.openapi.components.ApplicationComponent;
import com.intellij.openapi.ui.Messages;
import org.jetbrains.annotations.NotNull;

/**
 * Created by da-li on 2018/2/8.
 */
public class MyComponent implements ApplicationComponent {
	@Override
	public void initComponent() {
		
	}

	@Override
	public void disposeComponent() {

	}

	@NotNull
	@Override
	public String getComponentName() {
		return "MyComponent";
	}


	public void sayHello() {
		// Show dialog with message

		Messages.showMessageDialog(

				"Hello Word!",

				"Sample",

				Messages.getInformationIcon()

		);

	}
}
