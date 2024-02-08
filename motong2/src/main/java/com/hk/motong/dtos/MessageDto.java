package com.hk.motong.dtos;

public class MessageDto {

   private String type;
   private String userId;
   private String roomNo;
   private String msg;
   
   public MessageDto() {
      super();
      // TODO Auto-generated constructor stub
   }

   public MessageDto(String type, String userId, String roomNo, String msg) {
      super();
      this.type = type;
      this.userId = userId;
      this.roomNo = roomNo;
      this.msg = msg;
   }

   @Override
   public String toString() {
      return "MessageDto [type=" + type + ", userId=" + userId + ", roomNo=" + roomNo + ", msg=" + msg + "]";
   }

   public String getType() {
      return type;
   }

   public void setType(String type) {
      this.type = type;
   }

   public String getUserId() {
      return userId;
   }

   public void setUserId(String userId) {
      this.userId = userId;
   }

   public String getRoomNo() {
      return roomNo;
   }

   public void setRoomNo(String roomNo) {
      this.roomNo = roomNo;
   }

   public String getMsg() {
      return msg;
   }

   public void setMsg(String msg) {
      this.msg = msg;
   }
   
   
   
}