package com.learncode.blog.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ImageResponse {

	    private String imageName;
	    private String message;
	    private boolean success;
	    private int status;

}
