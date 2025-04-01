package org.example.mealisallyouneed.config;

import io.github.cdimascio.dotenv.Dotenv;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class MyBatisConfig {
    public static final SqlSessionFactory sqlSessionFactory;

    static {
        Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();

        Properties properties = new Properties();
        properties.setProperty("DB_DRIVER", dotenv.get("DB_DRIVER"));
        properties.setProperty("DB_URL", dotenv.get("DB_URL"));
        properties.setProperty("DB_USERNAME", dotenv.get("DB_USERNAME"));
        properties.setProperty("DB_PASSWORD", dotenv.get("DB_PASSWORD"));

        String resource = "mybatis-config.xml";
        try(InputStream inputStream = MyBatisConfig.class.getClassLoader().getResourceAsStream(resource)) {
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream, properties);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static SqlSessionFactory getSqlSessionFactory() {
        return sqlSessionFactory;
    }
}
