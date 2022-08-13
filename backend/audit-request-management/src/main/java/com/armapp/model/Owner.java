package com.armapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * @author DibyaPrakashOjha
 */

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Owner {
    @Id
    private Integer ownerId;
    private String ownerName;
    private String ownerUserId;
    @OneToMany(mappedBy="owner", cascade={CascadeType.ALL})
    @JsonIgnore
    private Set<Category> category;
    private String createdBy;
    private LocalDateTime createdAt;
    private String updatedBy;
    private LocalDateTime updatedAt;
    private boolean isDeleted;
}
