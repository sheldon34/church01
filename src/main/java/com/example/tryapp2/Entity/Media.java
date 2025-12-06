package com.example.tryapp2.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table (name="Media")
public class Media {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
 private Long id ;
    private String mediaName;
    private String mediaThumbnailUrl;
 private String mediaUrl;
 private String description;
// private String type;
}
