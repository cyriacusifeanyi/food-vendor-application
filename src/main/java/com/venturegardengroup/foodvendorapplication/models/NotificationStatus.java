package com.venturegardengroup.foodvendorapplication.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "notification_statuses")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class NotificationStatus extends AbstractPersistable<Integer> {

    @NotEmpty
    @Column(name = "name")
    private String name;
    @OneToMany(mappedBy = "notificationStatusId")
    @JsonIgnore
    private List<Notification> notifications = new ArrayList<>();

}
