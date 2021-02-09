package kr.or.ddit.user.model;

import java.text.SimpleDateFormat;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserVo {

	private String userid;
	private String usernm;
	private String pass;
	private Date reg_dt;
	private String alias;
	private String addr1;
	private String addr2;
	private String zipcode;
	private String filename;
	private String realfilename;
	
	public String getReg_dt_fmt() {
		return new SimpleDateFormat("yyyy-MM-dd").format(reg_dt);
	}
}
