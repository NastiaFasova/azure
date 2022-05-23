package com.epam.demo.models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Table(name="subscriptions")
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @ManyToMany
    private Set<Publication> publications = new HashSet<>();
    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Subscription(User user, Set<Publication> publications) {
        this.user = user;
        this.publications = publications;
    }
}
