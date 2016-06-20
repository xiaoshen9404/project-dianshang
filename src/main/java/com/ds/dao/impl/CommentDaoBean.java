package com.ds.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.ds.dao.CommentDao;
import com.ds.domain.Comment;
import com.ds.domain.Page;
import com.mysql.jdbc.Statement;

@Repository
public class CommentDaoBean extends BaseDao<Comment> implements CommentDao{


	
	@Override
	public Comment selectById(int commentId) {
		String sql = "SELECT * FROM tb_comment WHERE comment_id = ?";
		Object[] params=new Object[]{commentId};
		List<Comment> lists= jdbcTemplate.query(sql, params, new BeanPropertyRowMapper<Comment>(Comment.class));
		if(lists.size()==0){
			return null;
		}
		else{
			return lists.get(0);
		}
	}

	@Override
	public List<Comment> selectAll() {
		String sql="SELECT * FROM tb_comment";
		return jdbcTemplate.query(sql, new ResultSetExtractor<List<Comment>>(){

			@Override
			public List<Comment> extractData(ResultSet rs) throws SQLException, DataAccessException {
				List<Comment>comments=new ArrayList<Comment>();
				while(rs.next()){
					Comment comment=new Comment();
					comment.setCommentId(rs.getInt("comment_id"));
					comment.setContent(rs.getString("content"));
					comment.setDate(rs.getTimestamp("publish_time"));
					comment.setPostId(rs.getInt("post_id"));
					comment.setUserId(rs.getInt("user_id"));
					comments.add(comment);
				}
				return comments;
			}
			
		});
	}

	@Override
	public int insert(Comment comment) {
		final String sql="insert into tb_comment(content,publish_time,post_id,user_id) values(?,?,?,?)";
		final Object[] params=new Object[]{
				comment.getContent(),
				comment.getDate(),
				comment.getPostId(),
				comment.getUserId()
		};
		KeyHolder keyHolder=new GeneratedKeyHolder();
		int rc=jdbcTemplate.update(new PreparedStatementCreator(){

			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement st=con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				for(int i=0;i<params.length;i++){
					st.setObject(i+1, params[i]);
				}
				return st;
			}
			
		}, keyHolder);
		if(rc>0){
			comment.setCommentId(keyHolder.getKey().intValue());
		}
		return rc;
	}

	@Override
	public int update(int id, Comment comment) {
		String sql=""
				+ "update tb_comment "
				+ "set "
				+ " content=?, "
				+ " publish_time=?, "
				+ " post_id=?, "
				+ " user_id=?"
				+ " where"
				+ " comment_id=?";
		Object[] params=new Object[]{
				comment.getContent(),
				comment.getDate(),
				comment.getPostId(),
				comment.getUserId(),
				id
		};
		return jdbcTemplate.update(sql, params);
	}

	@Override
	public int delete(int id) {
		String sql="delete from tb_comment where comment_id=?";
		Object[] params=new Object[]{id};
		return jdbcTemplate.update(sql, params);
	}
	private static final String SQL_COUNT = "SELECT count(*) FROM tb_comment";
	private static final String SQL_SELECT = "SELECT * FROM tb_comment";
	@Override
	public Page<Comment> page(int pageNo, int pageSize) {
		return super.pagedQuery(SQL_SELECT, SQL_COUNT, pageNo, pageSize,new Object[]{},  new BeanPropertyRowMapper<Comment>(Comment.class));
	}

	@Override
	public List<Comment> selectAllByPostId(int postId) {
		// TODO Auto-generated method stub
		return null;
	}

}
