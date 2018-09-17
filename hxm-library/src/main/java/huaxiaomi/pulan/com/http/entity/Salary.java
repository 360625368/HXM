package huaxiaomi.pulan.com.http.entity;

/**
 * Description:薪资查询实体类
 * -
 *
 * Author：chasen
 * Date： 2018/9/11 11:12
 */
public class Salary {

    private String uuid;
    private String mail_name;
    private String month;
    private String fixed_salary;
    private String bonus_base;
    private String achievements_nums;
    private String other_withdrawing;
    private String contribution_deductible;
    private String personal_social;
    private String company_social;

    public String getUuid() {
        return uuid;
    }

    public String getMail_name() {
        return mail_name;
    }

    public String getMonth() {
        return month;
    }

    public String getFixed_salary() {
        return fixed_salary;
    }

    public String getBonus_base() {
        return bonus_base;
    }

    public String getAchievements_nums() {
        return achievements_nums;
    }

    public String getOther_withdrawing() {
        return other_withdrawing;
    }

    public String getContribution_deductible() {
        return contribution_deductible;
    }

    public String getPersonal_social() {
        return personal_social;
    }

    public String getCompany_social() {
        return company_social;
    }
}
