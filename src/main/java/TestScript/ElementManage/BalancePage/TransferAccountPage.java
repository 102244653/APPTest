package TestScript.ElementManage.BalancePage;

import org.openqa.selenium.By;

public class TransferAccountPage {

    //转账记录界面标题
    public static final By listtitle=By.id("com.seele.tigerwallet:id/middle_title");

    //转账界面返回按钮
    public static final By listback=By.id("com.seele.tigerwallet:id/btn_back");

    //资产持有份额
    public static final By selfaccount=By.xpath("//android.widget.TextView[@text='持有金额']/../android.widget.TextView[2]");

    //资产现金价值
    public static final By selfvalue=By.xpath("//android.widget.TextView[@resource-id='token_sign_tv']/../android.widget.TextView[3]");

    //转账按钮
    public static final By transfer=By.id("com.seele.tigerwallet:id/transfer_token_tv");

    //收款按钮
    public static final By recive=By.id("com.seele.tigerwallet:id/receivables_token_tv");

    //转账记录，按索引排序
    public static final By transferlist=By.xpath("//android.support.v7.widget.RecyclerView[@resource-id='recyclerView']/android.widget.RelativeLayout[*]");

    //每条转账记录的金额,*--第几条记录
    public static final String transfertvalue="//android.support.v7.widget.RecyclerView[@resource-id='recyclerView']/android.widget.RelativeLayout[*]" +
            "/android.widget.LinearLayout[2]/android.widget.LinearLayout/android.widget.TextView[1]";

    //每条转账记录的币种,*--第几条记录
    public static final String transfertunit="//android.support.v7.widget.RecyclerView[@resource-id='recyclerView']/android.widget.RelativeLayout[*]" +
            "/android.widget.LinearLayout[2]/android.widget.LinearLayout/android.widget.TextView[2]";



    //返回
    public static final By transferback=By.id("com.seele.tigerwallet:id/title_back_img");

    //当前余额
    public static final By nowaccount=By.id("com.seele.tigerwallet:id/tv_token_value");

    //转账地址
    public static final By transferadress=By.id("com.seele.tigerwallet:id/et_address");

    //联系人
    public static final By linkman=By.id("com.seele.tigerwallet:id/iv_contact");

    //扫码
    public static final By scan=By.id("com.seele.tigerwallet:id/right_img");

    //转账数量
    public static final By transferamount=By.id("com.seele.tigerwallet:id/et_token_value");

    //备注信息
    public static final By transfernote=By.id("com.seele.tigerwallet:id/et_token_content");

    //调节矿工费
    public static final By selectmoney=By.id("com.seele.tigerwallet:id/seekbar_gas");

    //默认矿工费
    public static final By defultmoney=By.id("com.seele.tigerwallet:id/tv_seekbar_gas_value");

    //下一步按钮
    public static final By transferbtn=By.id("com.seele.tigerwallet:id/btn_start_transfer");


}
