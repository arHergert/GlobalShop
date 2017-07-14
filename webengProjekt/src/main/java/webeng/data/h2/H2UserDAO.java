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
		
		String statement = "INSERT INTO User VALUES (default,?,?,?,?)";
		
		try {
			PreparedStatement stmt = connection.prepareStatement(statement);
			stmt.setString(1, newUser.getName());
			stmt.setString(2, newUser.getEmail());
			stmt.setString(3, newUser.getPassword());
			stmt.setString(4, newUser.getSessionID());
			stmt.execute();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public User getUser(String email) {
		
		String statement = "SELECT userid, name, email, passwort, sessionid FROM User WHERE Email = " + email;
		String name = null, eMail = null, passwort = null, sessionId = null;
		int id = 0;
		
		try {
			PreparedStatement stmt = connection.prepareStatement(statement);
			ResultSet rs = stmt.executeQuery();
			name = rs.getString("name");
			eMail = rs.getString("email");
			passwort = rs.getString("passwort");
			id = rs.getInt("userid");
			sessionId = rs.getString("sessionid");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return new User(id, name, email, passwort, sessionId);
		
	}
	
	@Override
	public User getUser(User userReference) {

		String statement = "SELECT userid, name, email, passwort, sessionid FROM User WHERE ";
		String name = null, email = null, passwort = null, sessionId = null;
		int id = 0;
		
		if(userReference.getID() != 0) {
			statement += "userid=?;";
		} else if(userReference.getEmail() != null) {
			statement += "email=?;";
		} else {
			return null;
		}
		
		try {
			PreparedStatement stmt = connection.prepareStatement(statement);
			if(statement.contains("WHERE userid="))
				stmt.setInt(1, userReference.getID());
			else if(statement.contains("WHERE email="))
				stmt.setString(1, userReference.getEmail());
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				name = rs.getString("name");
				email = rs.getString("email");
				passwort = rs.getString("passwort");
				id = rs.getInt("userid");
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
				id = rs.getInt("userid");
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
	public void updateSessionId(User user) {
		String statement = "UPDATE User SET Sessionid = ? WHERE Email = '" + user.getEmail() + "'";
		
		try {
			PreparedStatement stmt = connection.prepareStatement(statement);
			stmt.setString(1, user.getSessionID());
			stmt.execute();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void updateUser(User user) {
		
		String statement = "UPDATE User SET Name = ?, Email = ?, Passwort = ? WHERE Userid = " + user.getID();
		
		try {
			PreparedStatement stmt = connection.prepareStatement(statement);
			stmt.setString(1, user.getName());
			stmt.setString(2, user.getEmail());
			stmt.setString(3, user.getPassword());
			stmt.execute();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void deleteUser(User user) {
		
		String statement = "DELETE FROM User WHERE Userid = " + user.getID();
		
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
				password = rs.getString("passwort");
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
			password = rs.getString("passwort");
			sessionId = rs.getString("sessionid");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return new User(id,name,email,password,sessionId);
	}

}
