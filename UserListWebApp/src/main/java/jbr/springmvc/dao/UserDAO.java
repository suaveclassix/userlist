package jbr.springmvc.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import jbr.springmvc.bean.UserObj;

public class UserDAO {
	JdbcTemplate template;

	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}

	public int save(UserObj u) {
		String sql = "insert into USER_LIST(NAME,EMAIL,MOBILE) values('" + u.getNAME() + "', '" + u.getEMAIL() + "', '"+ u.getMOBILE() + "')";
		return template.update(sql);
	}

	public int update(UserObj u) {
		String sql = "update USER_LIST set NAME='" + u.getNAME() + "', EMAIL='" + u.getEMAIL() + "', MOBILE='"
				+ u.getMOBILE() + "' where ID=" + u.getID() + "";
		return template.update(sql);
	}

	public int delete(int id) {
		String sql = "delete from USER_LIST where ID=" + id + "";
		return template.update(sql);
	}

	public UserObj getUserById(int id) {
		String sql = "select * from USER_LIST where ID=?";
		return template.queryForObject(sql, new Object[] { id }, new BeanPropertyRowMapper<UserObj>(UserObj.class));
	}

	public List<UserObj> getUsers() {
		return template.query("select * from USER_LIST", new RowMapper<UserObj>() {
			public UserObj mapRow(ResultSet rs, int row) throws SQLException {
				UserObj u = new UserObj();
				u.setID(String.valueOf(rs.getInt(4)));
				u.setNAME(rs.getString(1));
				u.setEMAIL(rs.getString(2));
				u.setMOBILE(rs.getString(3));
				return u;
			}
		});
	}
}
