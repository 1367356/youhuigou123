package data.pojo;

public class RecParameter {

	String callback;
	String kind;  //总分类
	String cate;   // 下面分类
	String uid;    //商品id
	String page;   //页码
	String text;   //查询文本

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getKind() {
		return kind;
	}
	public void setKind(String kind) {
		this.kind = kind;
	}
	public String getCate() {
		return cate;
	}
	public void setCate(String cate) {
		this.cate = cate;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}
	public String getCallback() {
		return callback;
	}
	public void setCallback(String callback) {
		this.callback = callback;
	}
	@Override
	public String toString() {
		return "RecParameter [callback=" + callback + ", kind=" + kind + ", cate=" + cate + ", uid=" + uid + ", page="
				+ page + "]";
	}
}
