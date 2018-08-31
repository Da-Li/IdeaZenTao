package com.js_ku.zentao;


import com.intellij.openapi.actionSystem.ActionManager;
import com.intellij.openapi.components.ApplicationComponent;
import com.js_ku.zentao.api.ZenTaoApi;

public class IdeaZenTao implements ApplicationComponent {


    private static final String DEFAUL_TTHEME = "red";

    private static Boolean enabled = true;

    public static Boolean isEnabled(){
        return enabled;
    }

    public static void setEnabled(Boolean enabled){
        IdeaZenTao.enabled = enabled;
    }

    public static String getTheme(){

        return DEFAUL_TTHEME;
    }

    @Override
    public void initComponent() {
        System.out.println("init plugin");
        ActionManager.getInstance().getAction("showBugNumberAction").setDefaultIcon(true);
        if (!ZenTaoApi.login()){
            ActionManager.getInstance().getAction("showBugNumberAction").setDefaultIcon(true);
        }
    }


}
