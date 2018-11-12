package de.schulen.schulen.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Schulen {
    @Id
    private int schulnr;
    private String name;
    private String anschrift;
    private String stadtteil;
    private String schulform;
    private int anzahl;

}
