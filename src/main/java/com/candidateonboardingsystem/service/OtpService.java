package com.candidateonboardingsystem.service;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.Duration;
import java.util.Random;

@Service
public class OtpService {
    private final RedisTemplate<String, String> redisTemplate;

    public OtpService(final RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public String generateOtp(String email) {
        StringBuilder otp = new StringBuilder();
        Random random = new SecureRandom();
        for (int i = 0; i < 6; i++) {
            otp.append(random.nextInt(10));
        }
        redisTemplate.opsForValue().set(email, otp.toString(), Duration.ofMinutes(10));
        return otp.toString();
    }

    public String getOtp(String email) {
        return redisTemplate.opsForValue().get(email);
    }

    public boolean validateOtp(String email, String otp) {
        String storedOtp = getOtp(email);
        return otp.equals(storedOtp);
    }

    public void deleteOtp(String email) {
        redisTemplate.delete(email);
    }
}


