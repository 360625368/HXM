package huaxiaomi.pulan.com.mvp.i;

import android.content.Context;

import java.util.List;

import huaxiaomi.pulan.com.dialog.NormalDialog;

/**
 * Description:
 * -
 *
 * Author：chasen
 * Date： 2018/9/4 16:59
 */
public interface IMainModel extends IBaseModel {

    List<NormalDialog.Item> getMenus(Context context, int arrayRes);

    List<NormalDialog.Item> getMenusAndData(Context context, int arrayRes, int dataRes);
}
