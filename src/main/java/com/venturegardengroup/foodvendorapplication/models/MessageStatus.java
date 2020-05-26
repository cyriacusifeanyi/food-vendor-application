package com.venturegardengroup.foodvendorapplication.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

@EqualsAndHashCode(callSuper = true)
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "message_statuses")
public class  MessageStatus extends AbstractPersistable<Integer> {
//how do i initialize this table with values like "sent", "delivered", "read",
    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "messageStatusId")
    private List<Notification> notifications = new ArrayList<>();

}
