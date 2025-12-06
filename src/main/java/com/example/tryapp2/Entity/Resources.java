package com.example.tryapp2.Entity;


import com.amazonaws.services.medialive.model.Thumbnail;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Resources {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
private String resourcesName;
private String resourcesDescription;
private String  ThumbnailUrl;
private String resourcesUrl;
}
