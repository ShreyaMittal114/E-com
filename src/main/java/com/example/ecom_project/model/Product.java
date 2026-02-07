package com.example.ecom_project.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;
//@Component
@Entity
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     private int id;
     private String name;
     private String description;
     private String brand;
     private BigDecimal price;
     private String category;


    @JsonFormat(shape= JsonFormat.Shape.STRING,pattern="dd-MM-yyyy")
     private Date releaseDate;
     private boolean productAvailable;
     private int stockQuantity;
     private String imageName;
     private String imageType;
 @Lob
 private byte[] imageData;


 public String getImageName() {
  return imageName;
 }

 public void setImageName(String imageName) {
  this.imageName = imageName;
 }

 public String getImageType() {
  return imageType;
 }

 public void setImageType(String imageType) {
  this.imageType = imageType;
 }

 public byte[] getImageData() {
  return imageData;
 }

 public void setImageData(byte[] imageData) {
  this.imageData = imageData;
 }


 public void setId(int id) {
  this.id = id;
 }

 public void setName(String name) {
  this.name = name;
 }

 public void setDesc(String desc) {
  this.description = desc;
 }

 public void setBrand(String brand) {
  this.brand = brand;
 }

 public void setPrice(BigDecimal price) {
  this.price = price;
 }

 public void setCategory(String category) {
  this.category = category;
 }

 public void setReleasedate(Date releasedate) {
  this.releaseDate = releasedate;
 }

 public void setAvailable(boolean available) {
  this.productAvailable = available;
 }

 public void setQuantity(int quantity) {
  this.stockQuantity = quantity;
 }

 public int getId() {
  return id;
 }

 public String getName() {
  return name;
 }

 public String getDesc() {
  return description;
 }

 public String getBrand() {
  return brand;
 }

 public BigDecimal getPrice() {
  return price;
 }

 public String getCategory() {
  return category;
 }

 public Date getReleasedate() {
  return releaseDate;
 }

 public boolean isAvailable() {
  return productAvailable;
 }

 public int getQuantity() {
  return stockQuantity;
 }
}


