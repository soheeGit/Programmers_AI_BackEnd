package org.example.why_pstmt.model.dao;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.stereotype.Repository;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.Base64;

@Repository
public class TestUserRepository {
    private final Connection connection;
    private final Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();
    // 해싱
    private final MessageDigest digest;

    public TestUserRepository() throws SQLException, ClassNotFoundException, NoSuchAlgorithmException {
        Class.forName("com.mysql.cj.jdbc.Driver"); // 주의!
        String url = "jdbc:mysql://%s:%s/%s".formatted(
                dotenv.get("DB_HOST"),
                dotenv.get("DB_PORT"),
                dotenv.get("DB_DATABASE")
        );
        String username = dotenv.get("DB_USERNAME");
        String password = dotenv.get("DB_PASSWORD");
        connection = DriverManager.getConnection(url, username, password);

        digest = MessageDigest.getInstance("SHA-256");
    }

    public void createTestUser(String username, String password) throws SQLException {
//        Statement stmt = connection.createStatement();
//        String sql = "INSERT INTO test_user (username, password) VALUES ('%s', '%s')".formatted(username, password);
        // executeUpdate VS executeQuery
//        stmt.executeUpdate(sql);
        String sql = "INSERT INTO test_user (username, password) VALUES (?, ?)";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1, username);
//        pstmt.setString(2, password);
        pstmt.setString(2, hashPassword(password));
        pstmt.executeUpdate();
    }

    public boolean login(String username, String password) throws SQLException {
//        Statement stmt = connection.createStatement();
//        // 패스워드에 ' OR '1'='1 입력 시 뚫림
//        String sql = "SELECT * FROM test_user WHERE username = '%s' and password = '%s'".formatted(username, password);
//        ResultSet rs = stmt.executeQuery(sql);
        String sql = "SELECT * FROM test_user WHERE username = ? AND password = ?";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1, username);
//        pstmt.setString(2, password);
        pstmt.setString(2, hashPassword(password));
        ResultSet rs = pstmt.executeQuery();

        return rs.next(); // 데이터가 존재하는지 여부
    }

    private String hashPassword(String password) {
        byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
//        String hashedPassword = Base64.getEncoder().encodeToString(hash);
//        return hashedPassword;
        return Base64.getEncoder().encodeToString(hash);
    }
}