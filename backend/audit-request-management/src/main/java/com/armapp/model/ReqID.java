package com.armapp.model;


import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

/**
 * @author DibyaPrakashOjha
 */

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ReqID {

    @Id
    @GenericGenerator(name = "sequence_id", strategy = "com.armapp.model.StringPrefixedSequenceIdGenerator")
    @GeneratedValue(generator = "sequence_id")
    private Integer id;

    private LocalDateTime createdAt;

}
