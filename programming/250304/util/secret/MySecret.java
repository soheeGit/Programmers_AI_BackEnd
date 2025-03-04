package util.secret;

import java.util.HashMap;
import java.util.Map;

public class MySecret implements ISecret {
    private MySecret() {}
    private static MySecret instance;
    public static MySecret getSecret() throws NoEnvException {
        if (instance == null) {
            instance = new MySecret();
            for (SecretCategory category : SecretCategory.values()) {
                instance.setSecret(category.key);
            }
        }
        return instance;
    }

    final Map<String, String> map = new HashMap<>();

    @Override
    public void setSecret(String key) throws NoEnvException {String env = System.getenv(key);
        if (env == null) {
            throw new NoEnvException(key);
        }
        map.put(key, System.getenv(key
        ));
    }

    @Override
    public String getSecret(String key) throws NoEnvException {
        if (!map.containsKey(key)) {
            throw new NoEnvException(key);
        }
        return map.get(key);
    }
}

interface ISecret {
    void setSecret(String key) throws NoEnvException;
    String getSecret(String key) throws NoEnvException;
}