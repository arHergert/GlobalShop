package webeng.data.h2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import webeng.data.UserDAO;
import webeng.transferobjects.User;

public class H2UserDAO implements UserDAO {

	Connection connection;
	
	public H2UserDAO() {
		connection = H2DAOFactory.getConnection();
		
	}
	
	@Override
	public void addUser(User newUser) {
		
		String statement = "INSERT INTO User (id, name, email, passwort, sessionId) VALUES (?,?,?,?,?)";
		
		try {
			PreparedStatement stmt = connection.prepareStatement(statement);
			stmt.setInt(1, newUser.getID());
			stmt.setString(2, newUser.getName());
			stmt.setString(3, newUser.getEmail());
			stmt.setString(4, newUser.getPassword());
			stmt.setString(5, newUser.getSessionID());
			stmt.execute();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public User getUser(User userReference) {

		String statement = "SELECT id, name, email, passwort, sessionid FROM User WHERE ";
		String name = null, email = null, passwort = null, sessionId = null;
		int id = 0;
		
		if(userReference.getID() != 0) {
			statement += "id=?;";
		} else if(userReference.getEmail() != null) {
			statement += "email=?;";
		} else {
			return null;
		}
		
		try {
			PreparedStatement stmt = connection.prepareStatement(statement);
			if(statement.contains("WHERE id="))
				stmt.setInt(1, userReference.getID());
			else if(statement.contains("WHERE email="))
				stmt.setString(1, userReference.getEmail());
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				name = rs.getString("name");
				email = rs.getString("email");
				passwort = rs.getString("passwort");
				id = rs.getInt("id");
				sessionId = rs.getString("sessionid");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return new User(id, name, email, passwort, sessionId);
		
	}

	public List<User> findUsers() {
		
		String statement = "SELECT * FROM User;";
		List<User> list = new ArrayList<User>();
		String name = null, email = null, passwort = null, sessionId= null;
		int id = 0;
		
		try {
			PreparedStatement stmt = connection.prepareStatement(statement);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				name = rs.getString("name");
				email = rs.getString("email");
				passwort = rs.getString("passwort");
				id = rs.getInt("id");
				sessionId = rs.getString("sessionid");
				list.add(new User(id, name, email, passwort, sessionId));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	public boolean isEmailRegistered(User user) {
		
		String statement = "SELECT COUNT(1) AS anzahl FROM User WHERE email='" + user.getEmail() + "';";
		
		try {
			PreparedStatement stmt = connection.prepareStatement(statement);
			ResultSet result = stmt.executeQuery();
			while(result.next()) {
				if(result.getInt("anzahl") == 1) {
					return true;
				}
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return false;
		
	}

	@Override
	public void updateUser(User user) {
		
		String statement = "UPDATE User SET Name = ?, Email = ?, Sessionid = ? WHERE ID = " + user.getID();
		
		try {
			PreparedStatement stmt = connection.prepareStatement(statement);
			stmt.setString(1, user.getName());
			stmt.setString(2, user.getEmail());
			stmt.setString(3, user.getSessionID());
			stmt.execute();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void deleteUser(User user) {
		
		String statement = "DELETE FROM User WHERE ID = " + user.getID();
		
		try {
			PreparedStatement stmt = connection.prepareStatement(statement);
			stmt.execute();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public List<User> findAllUsers() {
		String statement = "SELECT * FROM USER;";
		List<User> list = new ArrayList<>();
		String name, email, password, sessionid;
		int id;
		
		try {
			PreparedStatement stmt = connection.prepareStatement(statement);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				name = rs.getString("name");
				id = rs.getInt("userid");
				email = rs.getString("email");
				password = rs.getString("password");
				sessionid = rs.getString("sessionid");
				list.add(new User(id,name,email,password,sessionid));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		 
		return list;
	}

	@Override
	public User getUser(int id) {
		String statement = "SELECT * FROM User WHERE UserID = " + id;
		String name = null, email = null, password = null, sessionId = null;
		
		try {
			PreparedStatement stmt = connection.prepareStatement(statement);
			ResultSet rs = stmt.executeQuery();
			name = rs.getString("name");
			email = rs.getString("email");
			password = rs.getString("password");
			sessionId = rs.getString("sessionid");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return new User(id,name,email,password,sessionId);
	}

}
