package huaxiaomi.pulan.com.mvp.v;

import java.util.List;

import huaxiaomi.pulan.com.entity.DailyMenu;
import huaxiaomi.pulan.com.http.entity.MsgRespond;

/**
 * Description:
 * -
 *
 * Author：chasen
 * Date： 2018/9/7 13:50
 */
public interface IDailyView<T> extends IBaseView {

    void initDailyMenu(List<DailyMenu> dailyMenus);

    void onDailyMeesageResult(MsgRespond<T> result);
}
