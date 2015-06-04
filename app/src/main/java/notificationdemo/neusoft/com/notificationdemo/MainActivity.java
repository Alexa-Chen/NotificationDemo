package notificationdemo.neusoft.com.notificationdemo;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class MainActivity extends Activity implements View.OnClickListener {
    NotificationManager manager;      //  通知控制类 显示消息
    int notification_ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        manager =(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        findViewById(R.id.btn_send).setOnClickListener(this);
        findViewById(R.id.btn_cancle).setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_send:
                sendNotification();
                break;
            case R.id.btn_cancle:
                manager.cancel(notification_ID);
                break;
        }
    }
    /*
         构造notification并发送到通知栏
     */
    private  void sendNotification(){
        Intent intent = new Intent(this,MainActivity.class);
        PendingIntent pintent = PendingIntent.getActivity(this,0,intent,0);   //1 上下文 ,请求码(0)， intent  flag(0)
        Notification.Builder builder = new Notification.Builder(this);
        builder.setSmallIcon(R.mipmap.ic_launcher);// 设置图标
        builder.setTicker("hello"); // 手机状态栏的提示
        builder.setWhen(System.currentTimeMillis());//设置时间
        builder.setContentTitle("通知栏通知");// 设置标题
        builder.setContentText("我来自NotificationDemo");//设置通知内容；
        builder.setContentIntent(pintent);  // 设置点击后的意图
        //builder.setDefaults(Notification.DEFAULT_SOUND); //设置提示声音
        //builder.setDefaults(Notification.DEFAULT_LIGHTS); // 设置指示灯
        //builder.setDefaults(Notification.DEFAULT_VIBRATE);//设置震动
        builder.setDefaults(Notification.DEFAULT_ALL); //  三种效果都有
        Notification notification = builder.build();  // 4.1 以上
        builder.getNotification();//4.1以下
        manager.notify(notification_ID,notification);    //id  : 对应当前 notification 的id   发送通知的通知栏
    }


}
