package huaxiaomi.pulan.com.dialog;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.PermissionUtils;

/**
 * Description:
 * -
 *
 * Author：chasen
 * Date： 2018/9/12 15:17
 */
public class DialogHelper {

    public static void showRationaleDialog(final PermissionUtils.OnRationaleListener.ShouldRequest shouldRequest) {
        Activity topActivity = ActivityUtils.getTopActivity();
        if (topActivity == null) return;
        new AlertDialog.Builder(topActivity)
                .setTitle(android.R.string.dialog_alert_title)
                .setMessage("请允许权限，否则程序无法正常运行")
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        shouldRequest.again(true);
                    }
                })
                .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        shouldRequest.again(false);
                    }
                })
                .setCancelable(false)
                .create()
                .show();

    }

    public static void showOpenAppSettingDialog() {
        Activity topActivity = ActivityUtils.getTopActivity();
        if (topActivity == null) return;
        new AlertDialog.Builder(topActivity)
                .setTitle(android.R.string.dialog_alert_title)
                .setMessage("请允许权限，否则程序无法正常运行")
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        PermissionUtils.launchAppDetailsSettings();
                    }
                })
                .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setCancelable(false)
                .create()
                .show();
    }
//
//    public static void showAdaptScreenDialog() {
//        Activity topActivity = ActivityUtils.getTopActivity();
//        if (topActivity == null) return;
//        new AlertDialog.Builder(topActivity)
//                .setTitle(android.R.string.dialog_alert_title)
//                .setMessage("Message!")
//                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//
//                    }
//                })
//                .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//
//                    }
//                })
//                .setCancelable(false)
//                .create()
//                .show();
//    }
//
//    public static void showKeyboardDialog() {
//        Activity topActivity = ActivityUtils.getTopActivity();
//        if (topActivity == null) return;
//        final View dialogView = LayoutInflater.from(topActivity).inflate(R.layout.dialog_keyboard, null);
//        final EditText etInput = dialogView.findViewById(R.id.et_input);
//        final AlertDialog dialog = new AlertDialog.Builder(topActivity).setView(dialogView).create();
//        dialog.setCanceledOnTouchOutside(false);
//        View.OnClickListener listener = new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                switch (v.getId()) {
//                    case R.id.btn_hide_soft_input:
//                        KeyboardUtils.hideSoftInput(etInput);
//                        break;
//                    case R.id.btn_show_soft_input:
//                        KeyboardUtils.showSoftInput(etInput);
//                        break;
//                    case R.id.btn_toggle_soft_input:
//                        KeyboardUtils.toggleSoftInput();
//                        break;
//                    case R.id.btn_close_dialog:
//                        KeyboardUtils.hideSoftInput(etInput);
//                        dialog.dismiss();
//                        break;
//                }
//            }
//        };
//        dialogView.findViewById(R.id.btn_hide_soft_input).setOnClickListener(listener);
//        dialogView.findViewById(R.id.btn_show_soft_input).setOnClickListener(listener);
//        dialogView.findViewById(R.id.btn_toggle_soft_input).setOnClickListener(listener);
//        dialogView.findViewById(R.id.btn_close_dialog).setOnClickListener(listener);
//        dialog.show();
//    }
}
