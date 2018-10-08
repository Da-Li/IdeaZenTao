package com.js_ku.zentao;


import com.intellij.openapi.actionSystem.ActionManager;
import com.intellij.openapi.components.ApplicationComponent;
import com.js_ku.zentao.api.ZenTaoApi;
import org.jetbrains.annotations.NotNull;

public class IdeaZenTao implements ApplicationComponent {

    @NotNull
    @Override
    public String getComponentName() {
        return "ZZZZTAO";
    }

    @Override
    public void disposeComponent() {

    }

    private static final String DEFAUL_TTHEME = "/red/";

    private static boolean enabled = true;

    private static boolean login = false;

    public static boolean isLogin(){
        return login;
    }

    public static boolean isEnabled(){
        return enabled;
    }

    public static void setlogin(boolean login){
        IdeaZenTao.login = login;
    }

    public static void setEnabled(boolean enabled){
        IdeaZenTao.enabled = enabled;
    }

    public static String getTheme(){

        return DEFAUL_TTHEME;
    }

    @Override
    public void initComponent() {
        if (!ZenTaoApi.login()){
            ActionManager.getInstance().getAction("showBugNumberAction").setDefaultIcon(true);
        }

    }


}
