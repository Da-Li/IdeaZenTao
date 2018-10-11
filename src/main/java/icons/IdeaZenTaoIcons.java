package icons;

import com.intellij.openapi.util.IconLoader;
import com.js_ku.zentao.IdeaZenTao;

import javax.swing.*;

public final class IdeaZenTaoIcons {

     public static final String theme = IdeaZenTao.getTheme();
     public static final Icon NO_LOGIN = IconLoader.getIcon(theme +"/unlogin.png");
     public static final Icon WEB = IconLoader.getIcon(theme +"/web.png");
     public static final Icon PAUSED = IconLoader.getIcon(theme +"/paused.png");

     public static Icon getIconByBugSize(int bugSize){

          if (bugSize == 0){
               return IconLoader.getIcon(IdeaZenTao.getTheme()+"nonBug.png");
          }

          if (bugSize > 9){
               return IconLoader.getIcon("");
          }

          return IconLoader.getIcon(IdeaZenTao.getTheme()+"number/number_"+bugSize+".png");
     }


}
