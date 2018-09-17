package huaxiaomi.pulan.com.mvp.v;

import java.util.List;

import huaxiaomi.pulan.com.dialog.NormalDialog;

/**
 * Description:
 * -
 *
 * Author：chasen
 * Date： 2018/9/4 16:53
 */
public interface IMainView extends IBaseView {

    void initDialogMenus(List<NormalDialog.Item> menus);

}
