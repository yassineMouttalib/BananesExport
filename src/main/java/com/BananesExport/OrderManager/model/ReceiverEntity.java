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
    private String nom;
    private String adresse;
    private String code_postal;
    private String ville;
    private String pays;
}
