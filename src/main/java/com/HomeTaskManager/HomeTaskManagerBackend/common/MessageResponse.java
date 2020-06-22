package com.HomeTaskManager.HomeTaskManagerBackend.common;

import org.springframework.http.ResponseEntity;

import java.util.Collections;

public class MessageResponse
{
    public static ResponseEntity<Object> createSet(String key, String value) {
        return ResponseEntity.ok(Collections.singletonMap(key, value));
    }
}
