package huaxiaomi.pulan.com.mvp.p;

import huaxiaomi.pulan.com.R;
import huaxiaomi.pulan.com.mvp.MvpFactory;
import huaxiaomi.pulan.com.mvp.i.IMainModel;
import huaxiaomi.pulan.com.mvp.i.IMainPresent;
import huaxiaomi.pulan.com.mvp.v.IMainView;

/**
 * Description:
 * -
 *
 * Author：chasen
 * Date： 2018/9/4 17:02
 */
public class MainPresenter extends BasePresenter<IMainView> implements IMainPresent {

    private IMainModel mainModel;

    public MainPresenter() {
        mainModel = MvpFactory.buildeM(IMainModel.class);
    }

    @Override
    public void getDailyMenus() {
        getProxyView().initDialogMenus(
                mainModel.getMenusAndData(
                        getProxyView().getContext(),
                        R.array.hxm_menus_daily_check,
                        R.array.hxm_menus_daily_check_data));
    }

    @Override
    public void getSettingMenus() {
        getProxyView().initDialogMenus(
                mainModel.getMenus(
                        getProxyView().getContext(),
                        R.array.hxm_menus_setting));
    }

    @Override
    public void getSkillMenus() {
        getProxyView().initDialogMenus(
                mainModel.getMenus(
                        getProxyView().getContext(),
                        R.array.hxm_menus_skill));
    }
}
