package com.notrika.regular_trafic.Database;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
 public class TBL_Violation {
 @NonNull
 @PrimaryKey(autoGenerate = true)
private Long id;
 private int server_id;
 private String imgurl;
 private String title;
 private String discription;


 public TBL_Violation(int server_id, String imgurl, String title, String discription) {
  this.server_id = server_id;
  this.imgurl = imgurl;
  this.title = title;
  this.discription = discription;
 }

 @NonNull
 public Long getId() {
  return id;
 }

 public void setId(@NonNull Long id) {
  this.id = id;
 }

 public int getServer_id() {
  return server_id;
 }

 public void setServer_id(int server_id) {
  this.server_id = server_id;
 }

 public String getImgurl() {
  return imgurl;
 }

 public void setImgurl(String imgurl) {
  this.imgurl = imgurl;
 }

 public String getTitle() {
  return title;
 }

 public void setTitle(String title) {
  this.title = title;
 }

 public String getDiscription() {
  return discription;
 }

 public void setDiscription(String discription) {
  this.discription = discription;
 }


}