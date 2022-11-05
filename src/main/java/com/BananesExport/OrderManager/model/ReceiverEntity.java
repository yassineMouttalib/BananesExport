package com.BananesExport.OrderManager.model;


import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReceiverEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "receiver_id", nullable = false)
    private Long receiver_id;
    @Column(nullable = false)
    private String nom;
    @Column(nullable = false)
    private String adresse;
    @Column(nullable = false)
    private String code_postal;
    @Column(nullable = false)
    private String ville;
    @Column(nullable = false)
    private String pays;
}
