package com.mydoctor.vo;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class IhealthData {
   private String accessToken;
   private String user_open_id;
   private String username;
}