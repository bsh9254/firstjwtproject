package com.sparta.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto<T> {
    private boolean success;
    private T data;
    private UserResponseDto.Error error;

    public static <T> UserResponseDto<T> success(T data) {
        return new UserResponseDto<>(true, data, null);
    }

    public static <T> UserResponseDto<T> fail(String code, String message) {
        return new UserResponseDto<>(false, null, new Error(code, message));
    }

    @Getter
    @AllArgsConstructor
    static class Error {
        private String code;
        private String message;
    }

}
