package service;

import static util.CommonUtil.*;

import java.util.ArrayList;
import java.util.List;

import model.Message;

public class BulletinBoardService {
	
	public boolean add(Message message) {
		if (message == null) {
			return false;
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append(message.getName());
		sb.append(",");
		sb.append(message.getCreateTime());
		sb.append(",");
		sb.append(message.getContent());
		return writeFile(sb.toString());
	}
	
	public List<Message> findAll() {
		List<Message> resultList = new ArrayList<>();
		List<String> strList = readFile();
		if (strList != null) {
			for (String str : strList) {
				String[] cols = str.split(",");
				Message vo = new Message();
				vo.setName(cols[0]);
				vo.setCreateTime(cols[1]);
				vo.setContent(cols[2]);
				resultList.add(vo);
			}
		}
		return resultList;
	}
}
