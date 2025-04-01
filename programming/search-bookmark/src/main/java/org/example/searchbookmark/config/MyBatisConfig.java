package org.example.searchbookmark.config;

import io.github.cdimascio.dotenv.Dotenv;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Logger;


public class MyBatisConfig {
    private static final SqlSessionFactory sqlSessionFactory;
    private static final Logger logger = Logger.getLogger(MyBatisConfig.class.getName());

    static {
        // dotenv 로드
        Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();

        // 환경변수
        Properties properties = new Properties();
        properties.setProperty("DB_DRIVER", dotenv.get("DB_DRIVER"));
        properties.setProperty("DB_URL", dotenv.get("DB_URL"));
        properties.setProperty("DB_USERNAME", dotenv.get("DB_USERNAME"));
        properties.setProperty("DB_PASSWORD", dotenv.get("DB_PASSWORD"));
        logger.info("MyBatisConfig: " + properties);

        // mybatis-config.xml 파일 로드
        String resource = "mybatis-config.xml";
        // 아니야... 등록을 해야 찾아줘... 속지마...
        try (InputStream inputStream = MyBatisConfig.class.getClassLoader().getResourceAsStream(resource)) {
            logger.info("%s".formatted(inputStream));
            logger.info(String.valueOf(MyBatisConfig.class.getClassLoader().getResource(resource)));

            // 연결...
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream, properties);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        logger.info("config 완료");
    }

    // 일종의 싱글톤으로 봐야하는데...
    public static SqlSessionFactory getSqlSessionFactory() {
        return sqlSessionFactory;
    }
}