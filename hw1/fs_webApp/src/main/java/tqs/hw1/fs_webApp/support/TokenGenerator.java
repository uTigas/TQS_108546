package tqs.hw1.fs_webApp.support;

import java.util.UUID;

public class TokenGenerator {
    public static String generateToken() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString().replace("-", "");
    }
}
