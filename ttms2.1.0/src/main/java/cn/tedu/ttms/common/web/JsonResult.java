package cn.tedu.ttms.common.web;
/**
 * 通过此对象封装控制层返回的json结果
 * 如果不返回json 则不对json记过封装
 * 便于对控制层返回数据进行统一化，友好性管理
 */
public class JsonResult {
	public static final int SUCCESS=1;
	public static final int ERROR=0;
	//服务器的响应状态
	public int state;
	//信息(给用户的提示)
	private String message;
	//具体业务数据
	private Object data;
	
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public JsonResult(){
		this.state=SUCCESS;
		this.message="ok";
	}
	public JsonResult(Object data){
		this();
		this.data=data;		
	}
	public JsonResult(Throwable e){
		this.state=ERROR;
		this.message=e.getMessage();
	}
	
	

}
