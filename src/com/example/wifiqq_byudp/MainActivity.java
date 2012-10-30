package com.example.wifiqq_byudp;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.example.wifiqq_byudp.UDPServer.DataRecvListener;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 主窗体
 */
public class MainActivity extends Activity {
	/**消息内容编辑框*/
	private EditText etContent=null;
	/**消息发送按键*/
	private Button btnSend=null;
	/**消息链表*/
	private List<MsgInfo> msgList = new ArrayList<MsgInfo>();
	/**消息处理器*/
	private Handler handler;
	//////////////////////////一些标识/////////////////////////////
	private static final String KEY_WHAT = "what";
	private static final String KEY_STR = "str";
	private static final String KEY_MSGINFO = "msgInfo";
	private static final int MSG_RECVMSG = 1;
	public static final String KEY_RC_NAME = "name";
	////////////////////////////////////////////////////////////////
	
	/**udp客户端*/
	private UDPClient client;
	/**消息显示列表控件*/
	private ListView listview;
	/**数控适配器*/
	private MsginfoAdapter adapter;
	/**标题文本框*/
	private TextView tvTitle;
	/**持久化存储工具*/
	private MySP mySP;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//设置显示的界面文件
		setContentView(R.layout.activity_main);
		//隐藏输入法
		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		//新建udp客户端
		client=new UDPClient();
		//获取消息内容编辑框
		etContent=(EditText)findViewById(R.id.et_content);
		//获取发送按钮
		btnSend=(Button)findViewById(R.id.btn_send);
		//获取标题文件框
		tvTitle = (TextView) findViewById(R.id.tv_title);
		//新建持久化保存工具
		mySP = new MySP(this);
		//设置标题文本框显示内容，如果有用户名就显示用户名，没有就显示程序名
		tvTitle.setText(mySP.get(KEY_RC_NAME, getString(R.string.app_name)));
		
		//获取消息列表控件
		listview = (ListView) findViewById(R.id.listView1);
		//新建消息显示适配器
		adapter = new MsginfoAdapter(this);
		//设置消息适配器的数据源
		adapter.setData(msgList);
		//设置消息显示列表的数据适配器
		listview.setAdapter(adapter);
		//设置滚动时候的缓冲色
		listview.setCacheColorHint(0);
		
		//开启服务器
		ExecutorService exec=Executors.newCachedThreadPool();
		UDPServer server=new UDPServer(new DataRecvListener() {
			//接收到消息
			public void onRecv(MsgInfo info) {
				sendMsgToHandler(info);
			}
		});
		exec.execute(server);
		
		//发送消息
		btnSend.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				client.send(mySP.get(KEY_RC_NAME, getString(R.string.app_name))+":\n"+etContent.getText().toString());
				etContent.setText("");
			}
		});
		
		//修改用户名
		tvTitle.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				setMyName();
			}
		});
		
		this.initHandler();
	}
	
	/**
	 * 弹出修改用户名窗口
	 */
	private void setMyName(){
		final EditText myName = new EditText(this);
		new AlertDialog.Builder(this)
		.setTitle("请输入你的昵称：")
		.setView(myName)
		.setNegativeButton("确定", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				if(myName.getText().toString().length()>1){
					mySP.put(KEY_RC_NAME, myName.getText().toString());
					tvTitle.setText(myName.getText().toString());
					dialog.dismiss();
				}
				else{
					Toast.makeText(MainActivity.this, "用户名不能为空！", Toast.LENGTH_SHORT).show();
				}
			}
		})
		.show();
	}
	
	/**
	 * @param info
	 * 发送消息到消息处理器中
	 */
	private void sendMsgToHandler(MsgInfo info){
		Message msg = new Message();
		Bundle b = new Bundle();
		b.putInt(KEY_WHAT, MSG_RECVMSG);
		b.putSerializable(KEY_MSGINFO, info);
		msg.setData(b);
		handler.sendMessage(msg);
	}

	/**
	 * 初始化消息处理器
	 */
	private void initHandler(){
		handler = new Handler(){
			@Override
            public void handleMessage(Message msg) {
	            switch(msg.getData().getInt(KEY_WHAT)){
	            case MSG_RECVMSG:
	            	msgList.add((MsgInfo) msg.getData().getSerializable(KEY_MSGINFO));
	            	adapter.notifyDataSetChanged();
	            	listview.setSelection(msgList.size()-1);
	            	break;
	            }
            }
		};
	}
}
