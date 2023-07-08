package com.workspace.blog.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SmsRequest {
	private String toPhoneNumber;
    private String message;
//    private String otp;
}
