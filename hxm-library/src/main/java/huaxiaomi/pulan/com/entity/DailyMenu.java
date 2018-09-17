package huaxiaomi.pulan.com.entity;

import android.support.annotation.DrawableRes;

/**
 * Description:日常查询菜单实体
 * -
 *
 * Author：chasen
 * Date： 2018/9/7 14:07
 */
public class DailyMenu {

    private String title;
    private int iconRes;
    private Type type;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getIconRes() {
        return iconRes;
    }

    public void setIconRes(@DrawableRes int iconRes) {
        this.iconRes = iconRes;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public enum Type{
        SALARY,
        ATTENDANCE,
        MEETING,
        SCHEDULE,
        DEALT,
        PENDING,
        WORKING_SATURATION,
        APPROVAL,
        WORK_TASK
    }
}
