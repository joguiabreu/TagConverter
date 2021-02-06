package processInfo;

import java.util.Vector;

import javax.swing.JOptionPane;

import classes.GroupColection;
import classes.GroupInfo;
import classes.OPCinfo;
import classes.TagInfo;

public class ProcessOPCinfo {
	
	public static GroupColection Process(Vector<OPCinfo> vecOPCinfo, GroupColection COLECTION) {
		Vector<GroupInfo> vecGroupInfo = new Vector<GroupInfo>();
		GroupInfo groupInfo = new GroupInfo();
		Vector<TagInfo> vecTagInfo = new Vector<TagInfo>();
		TagInfo tagInfo = new TagInfo();
		String groupName = new String();
		
		try {
			for(OPCinfo info : vecOPCinfo) {
				
				tagInfo.setTagName(info.getTagName());
				tagInfo.setAddress(info.getAddress());
				tagInfo.setDataType(info.getDataType());
				tagInfo.setClientAccess(info.getClientAccess());
				tagInfo.setDescription(info.getDescription());
				
				//checking for an empty vector to add the first value
				if (!vecGroupInfo.isEmpty()) {
					//checking if there is 
					for (int i = 0; i < vecGroupInfo.size(); i++) {
						groupInfo = vecGroupInfo.get(i);
						groupName = groupInfo.getGroupName();
						
						if (groupName.equals(info.getGroup())) {
							vecTagInfo = groupInfo.getTagInfo();
							vecTagInfo.add(tagInfo);

							groupInfo.setTagInfo(vecTagInfo);
							
							vecGroupInfo.set(i,groupInfo);
							
							COLECTION.setVecGroupInfo(vecGroupInfo);
							break;
						} else if (i + 1 == vecGroupInfo.size()) {
							groupInfo = new GroupInfo();
							
							vecTagInfo.add(tagInfo);
							
							groupInfo.setGroupName(info.getGroup());
							groupInfo.setTagInfo(vecTagInfo);
							
							vecGroupInfo.add(groupInfo);
							
							COLECTION.setVecGroupInfo(vecGroupInfo);
							break;
						} else {
							continue;
						}
					}
				} else {				
					vecTagInfo.add(tagInfo);
					
					groupInfo.setGroupName(info.getGroup());
					groupInfo.setTagInfo(vecTagInfo);
					
					vecGroupInfo.add(groupInfo);
					
					COLECTION.setVecGroupInfo(vecGroupInfo);
				}
				
				groupInfo = new GroupInfo();
				vecTagInfo = new Vector<TagInfo>();
				info = new OPCinfo();
				tagInfo = new TagInfo();
			}
			
			return COLECTION;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error in ProcessOPCinfo.Process -> " + e);
		}
		
		return COLECTION;	
	}
}
