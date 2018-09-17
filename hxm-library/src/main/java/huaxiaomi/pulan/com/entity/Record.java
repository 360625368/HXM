package huaxiaomi.pulan.com.entity;

import java.util.List;

/**
 * Description:百度语音转文字实体类
 * -
 *
 * Author：chasen
 * Date： 2018/9/12 13:37
 */
public class Record {

    private List<String> results_recognition;
    private OriginRuslt origin_ruslt;
    private String error;
    private String best_result;
    private String result_type;

    public List<String> getResults_recognition() {
        return results_recognition;
    }

    public OriginRuslt getOrigin_ruslt() {
        return origin_ruslt;
    }

    public String getError() {
        return error;
    }

    public String getBest_result() {
        return best_result;
    }

    public String getResult_type() {
        return result_type;
    }

    public class OriginRuslt{
        private String corpus_no;
        private String err_no;
        private Result result;
        private String sn;

        public String getCorpus_no() {
            return corpus_no;
        }

        public String getErr_no() {
            return err_no;
        }

        public Result getResult() {
            return result;
        }

        public String getSn() {
            return sn;
        }
    }

    public class Result{
        private List<String> word;

        public List<String> getWord() {
            return word;
        }
    }
}
