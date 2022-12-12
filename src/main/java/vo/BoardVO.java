package vo;

import lombok.Data;

@Data //Lombok API setter, getter 자동으로 만들어줌
public class BoardVO {
	private int idx, count;
	//번호, 조회수
	private String title, content, writer, indate;
	//제목, 내용, 작성자, 작성일
}
