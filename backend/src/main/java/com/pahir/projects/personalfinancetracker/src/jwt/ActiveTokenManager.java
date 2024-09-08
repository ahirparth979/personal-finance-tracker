package com.pahir.projects.personalfinancetracker.src.jwt;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class ActiveTokenManager {
    private final Map<String, String> activeTokens = new ConcurrentHashMap<>();

    public void addToken(String username, String token) {
        activeTokens.put(username, token);
    }

    public void removeToken(String username) {
        activeTokens.remove(username);
    }

    public String getToken(String username) {
        return activeTokens.get(username);
    }

    public boolean isTokenActive(String username, String token) {
        return token.equals(activeTokens.get(username));
    }
}
