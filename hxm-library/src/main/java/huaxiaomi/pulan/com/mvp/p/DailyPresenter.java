package huaxiaomi.pulan.com.mvp.p;

import java.util.List;

import huaxiaomi.pulan.com.http.DefaultNetCallBack;
import huaxiaomi.pulan.com.http.entity.Approval;
import huaxiaomi.pulan.com.http.entity.ApprovalDetail;
import huaxiaomi.pulan.com.http.entity.Attendance;
import huaxiaomi.pulan.com.http.entity.AttendanceResult;
import huaxiaomi.pulan.com.http.entity.Dealt;
import huaxiaomi.pulan.com.http.entity.DealtDetail;
import huaxiaomi.pulan.com.http.entity.Meeting;
import huaxiaomi.pulan.com.http.entity.MsgRespond;
import huaxiaomi.pulan.com.http.entity.Pending;
import huaxiaomi.pulan.com.http.entity.PendingDetail;
import huaxiaomi.pulan.com.http.entity.Salary;
import huaxiaomi.pulan.com.http.entity.Saturation;
import huaxiaomi.pulan.com.http.entity.Schedule;
import huaxiaomi.pulan.com.mvp.MvpFactory;
import huaxiaomi.pulan.com.mvp.i.IDailyModel;
import huaxiaomi.pulan.com.mvp.i.IDailyPresenter;
import huaxiaomi.pulan.com.mvp.v.IDailyView;

/**
 * Description:
 * -
 *
 * Author：chasen
 * Date： 2018/9/7 14:25
 */
public class DailyPresenter extends BasePresenter<IDailyView> implements IDailyPresenter {

    private IDailyModel dailyModel;

    public DailyPresenter() {
        dailyModel = MvpFactory.buildeM(IDailyModel.class);
    }

    @Override
    public void getDailyMenus() {
        getProxyView().initDailyMenu(dailyModel.getDailyMenus());
    }

    @Override
    public void getSalary() {
        getProxyView().showDefaultProgressDialog();
        dailyModel.getSalary(new DefaultNetCallBack<MsgRespond<Salary>>() {
            @Override
            public void onSuccess(MsgRespond<Salary> result) {
                super.onSuccess(result);
                getProxyView().onDailyMeesageResult(result);
            }

            @Override
            public void onFinish() {
                super.onFinish();
                getProxyView().dismissProgressDialog();
            }
        });
    }

    @Override
    public void getAttendance() {
        getProxyView().showDefaultProgressDialog();
        dailyModel.getAttendance(new DefaultNetCallBack<MsgRespond<Attendance>>() {
            @Override
            public void onSuccess(MsgRespond<Attendance> result) {
                super.onSuccess(result);
                getProxyView().onDailyMeesageResult(result);
            }

            @Override
            public void onFinish() {
                super.onFinish();
                getProxyView().dismissProgressDialog();
            }
        });
    }

    @Override
    public void getAttendanceResult(String date) {
        dailyModel.getAttendanceResult(date,new DefaultNetCallBack<MsgRespond<List<AttendanceResult>>>() {
            @Override
            public void onSuccess(MsgRespond<List<AttendanceResult>> result) {
                super.onSuccess(result);
            }
        });
    }

    @Override
    public void getMeeting() {
        getProxyView().showDefaultProgressDialog();
        dailyModel.getMeeting(new DefaultNetCallBack<MsgRespond<List<Meeting>>>() {
            @Override
            public void onSuccess(MsgRespond<List<Meeting>> result) {
                super.onSuccess(result);
                getProxyView().onDailyMeesageResult(result);
            }

            @Override
            public void onFinish() {
                super.onFinish();
                getProxyView().dismissProgressDialog();
            }
        });
    }

    @Override
    public void getSchedule(String date) {
        getProxyView().showDefaultProgressDialog();
        dailyModel.getSchedule(date,new DefaultNetCallBack<MsgRespond<List<Schedule>>>() {
            @Override
            public void onSuccess(MsgRespond<List<Schedule>> result) {
                super.onSuccess(result);
                getProxyView().onDailyMeesageResult(result);
            }

            @Override
            public void onFinish() {
                super.onFinish();
                getProxyView().dismissProgressDialog();
            }
        });
    }

    @Override
    public void getDealt(String date) {
        getProxyView().showDefaultProgressDialog();
        dailyModel.getDealt(date, new DefaultNetCallBack<MsgRespond<List<Dealt>>>() {
            @Override
            public void onSuccess(MsgRespond<List<Dealt>> result) {
                super.onSuccess(result);
                getProxyView().onDailyMeesageResult(result);
            }

            @Override
            public void onFinish() {
                super.onFinish();
                getProxyView().dismissProgressDialog();
            }
        });
    }

    @Override
    public void getDealtDetail(String id) {
        getProxyView().showDefaultProgressDialog();
        dailyModel.getDealtDetail(id, new DefaultNetCallBack<MsgRespond<List<DealtDetail>>>() {
            @Override
            public void onSuccess(MsgRespond<List<DealtDetail>> result) {
                super.onSuccess(result);
                getProxyView().onDailyMeesageResult(result);
            }

            @Override
            public void onFinish() {
                super.onFinish();
                getProxyView().dismissProgressDialog();
            }
        });
    }

    @Override
    public void getPending() {
        getProxyView().showDefaultProgressDialog();
        dailyModel.getPending(new DefaultNetCallBack<MsgRespond<List<Pending>>>() {
            @Override
            public void onSuccess(MsgRespond<List<Pending>> result) {
                super.onSuccess(result);
                getProxyView().onDailyMeesageResult(result);
            }

            @Override
            public void onFinish() {
                super.onFinish();
                getProxyView().dismissProgressDialog();
            }
        });
    }

    @Override
    public void getPendingDetail(String id) {
        getProxyView().showDefaultProgressDialog();
        dailyModel.getPendingDetail(id, new DefaultNetCallBack<MsgRespond<PendingDetail>>() {
            @Override
            public void onSuccess(MsgRespond<PendingDetail> result) {
                super.onSuccess(result);
            }

            @Override
            public void onFinish() {
                super.onFinish();
                getProxyView().dismissProgressDialog();
            }
        });
    }

    @Override
    public void getWorkingSaturation() {
        getProxyView().showDefaultProgressDialog();
        dailyModel.getWorkingSaturation(new DefaultNetCallBack<MsgRespond<Saturation>>() {
            @Override
            public void onSuccess(MsgRespond<Saturation> result) {
                super.onSuccess(result);
                getProxyView().onDailyMeesageResult(result);
            }

            @Override
            public void onFinish() {
                super.onFinish();
                getProxyView().dismissProgressDialog();
            }
        });
    }

    @Override
    public void getApproval(String date) {
        getProxyView().showDefaultProgressDialog();
        dailyModel.getApproval(date, new DefaultNetCallBack<MsgRespond<List<Approval>>>() {
            @Override
            public void onSuccess(MsgRespond<List<Approval>> result) {
                super.onSuccess(result);
                getProxyView().onDailyMeesageResult(result);
            }

            @Override
            public void onFinish() {
                super.onFinish();
                getProxyView().dismissProgressDialog();
            }
        });
    }

    @Override
    public void getApprovalDetail(String id) {
        getProxyView().showDefaultProgressDialog();
        dailyModel.getApprovalDetail(id, new DefaultNetCallBack<MsgRespond<List<ApprovalDetail>>>() {
            @Override
            public void onSuccess(MsgRespond<List<ApprovalDetail>> result) {
                super.onSuccess(result);
                getProxyView().onDailyMeesageResult(result);
            }

            @Override
            public void onFinish() {
                super.onFinish();
                getProxyView().dismissProgressDialog();
            }
        });
    }

    @Override
    public void getWorkTask() {
        getProxyView().showDefaultProgressDialog();
        dailyModel.getWorkTask(new DefaultNetCallBack<MsgRespond>() {
            @Override
            public void onSuccess(MsgRespond result) {
                super.onSuccess(result);
                getProxyView().onDailyMeesageResult(result);
            }

            @Override
            public void onFinish() {
                super.onFinish();
                getProxyView().dismissProgressDialog();
            }
        });
    }
}
