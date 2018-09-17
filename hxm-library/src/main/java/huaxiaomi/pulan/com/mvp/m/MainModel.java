package huaxiaomi.pulan.com.mvp.m;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import huaxiaomi.pulan.com.dialog.NormalDialog;
import huaxiaomi.pulan.com.mvp.i.IMainModel;

/**
 * Description:
 * -
 *
 * Author：chasen
 * Date： 2018/9/7 11:28
 */
public class MainModel extends BaseModel implements IMainModel {

    @Override
    public List<NormalDialog.Item> getMenus(Context context, int arrayRes) {
        List<NormalDialog.Item> result = new ArrayList<>();
        String[] temp = context.getResources().getStringArray(arrayRes);
        for(String title : temp){
            NormalDialog.Item item = new NormalDialog.Item();
            item.title = title;
            result.add(item);
        }
        return result;
    }

    @Override
    public List<NormalDialog.Item> getMenusAndData(Context context, int arrayRes, int dataRes) {
        List<NormalDialog.Item> result = getMenus(context,arrayRes);
        String[] temp = context.getResources().getStringArray(dataRes);
        for(int i = 0;i < temp.length;i++){
            NormalDialog.Item item = result.get(i);
            item.data = temp[i];
        }
        return result;
    }
}
