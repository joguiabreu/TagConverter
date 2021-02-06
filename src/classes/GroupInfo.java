package classes;

import java.util.Vector;

public class GroupInfo {
	String groupName;
	Vector<TagInfo> tagInfo;
	
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public Vector<TagInfo> getTagInfo() {
		return tagInfo;
	}
	public void setTagInfo(Vector<TagInfo> tagInfo) {
		this.tagInfo = tagInfo;
	}
	
}
